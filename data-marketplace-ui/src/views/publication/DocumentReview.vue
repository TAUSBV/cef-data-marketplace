<template>
  <main class="pb-32 overflow-x-hidden">
    <PublicationHeader />
    <section class="flex flex-col sm:w-3/4 mx-auto xs:px-4 p-4 mx-auto relative">
      <Stepper :steps="steps" />
      <div v-if="!isPublished && !isReview && !hasErrors">
        <DocumentNotify :documentId="publicationInternal.uid" />
      </div>
      <DocumentAnalysis
        :document="document"
        :domain="domain"
        :contentType="contentType"
        :pricePerWord="pricePerWord"
        :description="description"
        v-if="showDocumentAnalysis"
      />
      <div v-if="!hasDocument">
        <DocumentAnalysisLoading />
      </div>
      <section v-if="documentReviewStep === 1 && !isPublished">
        <Alert
          v-if="hasErrors"
          :title="publicationInternal.errors[0].description"
          :messages="
            publicationInternal.errors.length > 1 ? publicationInternal.errors.slice(1).map(e => e.description) : []
          "
          type="DANGER"
        />
        <div class="flex flex-col justify-center items-center text-center mt-4">
          <Button v-if="hasErrors" @click="cancel">
            Cancel
          </Button>
          <Button v-else-if="isReview" @click="continueDocumentReview" :isLoading="isLoading">
            Next
          </Button>
        </div>
      </section>
      <DocumentCleaning
        v-else-if="documentReviewStep === 2"
        @setDocumentToClean="setDocumentToClean"
        :isLoading="isLoading"
        :document="document"
      />
      <DocumentAnonymize
        v-else-if="documentReviewStep === 3"
        @setDocumentToAnonymize="setDocumentToAnonymize"
        @getBackToLastStep="getBackToLastStep"
        :isLoading="isLoading"
        :isAnonymizeCheck="anonymize"
        :document="document"
      />
      <DocumentSpecification
        v-else-if="documentReviewStep === 4"
        @setDocumentSpecifications="setDocumentSpecifications"
        @getBackToLastStep="getBackToLastStep"
        :isLoading="isLoading"
      />
      <DocumentPrice
        v-else-if="documentReviewStep === 5"
        :document="document"
        :suggestedPricePerWord="suggestedPricePerWord"
        @setDocumentPrice="setDocumentPrice"
        @getBackToLastStep="getBackToLastStep"
        :isLoading="isLoading"
      />
      <Authentication
        v-else-if="documentReviewStep === 6"
        @setAuthenticationSignIn="setAuthenticationSignIn"
        @setAuthenticationSignUp="setAuthenticationSignUp"
        :isLoading="isLoading"
        :isNewUser="isNewUser"
        :serviceErrors="serviceErrors"
      />
      <DocumentDescription
        v-else-if="documentReviewStep === 7"
        @setDocumentDescription="setDocumentDescription"
        :isLoading="isLoading"
      />
      <section v-else-if="documentReviewStep === 8">
        <div class="text-center">
          <Button @click="publish" :isLoading="isLoading">
            Publish
          </Button>
        </div>
      </section>
      <section v-else-if="documentReviewStep === 9 && !isPublished">
        <div class="text-center">
          <Button :isLoading="true">
            Publish
          </Button>
        </div>
      </section>
      <section v-else-if="(documentReviewStep === 1 || documentReviewStep === 9) && isPublished">
        <div class="w-3/4 shadow py-20 mx-auto text-center">
          <img src="/assets/img/steps-finished.svg" alt="Steps finished" class="inline w-16 h-16 mb-8" />
          <div class="text-34px leading-49 tracking-0.76 font-medium xs:text-2xl xs:tracking-0.44 mb-12">
            Thank you. Your document is ready for sale.
          </div>
          <Button @click="done" :disabled="!isPublished">
            Done
          </Button>
        </div>
      </section>
    </section>
  </main>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button from "@/components/Button.vue";
import Stepper from "@/components/Stepper.vue";
import DocumentAnalysis from "@/views/publication/DocumentAnalysis.vue";
import DocumentAnalysisLoading from "@/views/publication/DocumentAnalysisLoading.vue";
import DocumentSpecification from "@/views/publication/DocumentSpecification.vue";
import DocumentPrice from "@/views/publication/DocumentPrice.vue";
import DocumentDescription from "@/views/publication/DocumentDescription.vue";
import Authentication from "@/views/Authentication.vue";
import DocumentNotify from "@/views/publication/DocumentNotify.vue";
import PublicationHeader from "@/views/publication/PublicationHeader.vue";
import DocumentAnonymize from "@/views/publication/DocumentAnonymize.vue";
import DocumentCleaning from "@/views/publication/DocumentCleaning.vue";
import Alert from "@/components/Alert.vue";

import {
  ContentType,
  Domain,
  Publication,
  PUBLICATION_STATUSES,
  PublicationStatus,
  SignInForm,
  SignUpForm,
  TranslationDocument
} from "@/models/models";

export default Vue.extend({
  props: {
    publication: {
      type: Object as PropType<Publication>,
      required: true
    }
  },
  data() {
    const publication = Publication.parse(this.publication);
    return {
      publicationInternal: publication,
      PUBLICATION_STATUSES: PUBLICATION_STATUSES,
      domain: null,
      contentType: null,
      pricePerWord: publication.document?.pricePerWord,
      documentReviewStep: 1,
      suggestedPricePerWord: null,
      description: "",
      isLoading: false,
      isNewUser: false,
      serviceErrors: null,
      anonymize: false
    };
  },
  mounted() {
    if (
      (this.publicationInternal.status == PublicationStatus.RECEIVED ||
        this.publicationInternal.status == PublicationStatus.ANALYZING ||
        this.publicationInternal.status == PublicationStatus.PUBLISHING) &&
      !this.publicationInternal.hasErrors()
    ) {
      this.checkStatus(this.publicationInternal);
    }
  },
  watch: {
    documentReviewStep() {
      this.isLoading = false;
    }
  },
  computed: {
    steps(): Array<{ name: string; status: string }> {
      const publication = this.publicationInternal;
      const hasErrors = this.publicationInternal.hasErrors();
      const document = this.publicationInternal.document;
      const steps = Array<{ name: string; status: string }>();
      const currentIndex = PUBLICATION_STATUSES.indexOf(publication.status);
      const reviewDone =
        currentIndex > PUBLICATION_STATUSES.indexOf(PublicationStatus.REVIEW) ||
        (document?.pricePerWord !== 0 && document?.domain !== "" && document?.contentType !== "");
      PUBLICATION_STATUSES.forEach((s, i) => {
        if (s === PublicationStatus.RECEIVED) {
          steps.push({ name: s, status: "completed" });
        } else if (s === PublicationStatus.ANALYZING) {
          let status = "";
          if (currentIndex > i) {
            status = "completed";
          } else if (
            publication.status === PublicationStatus.ANALYZING ||
            publication.status === PublicationStatus.RECEIVED
          ) {
            status = hasErrors ? "failed" : "active";
          }
          steps.push({ name: s, status: status });
        } else if (s === PublicationStatus.REVIEW) {
          let status = "";
          if (publication.status === PublicationStatus.REVIEW && !reviewDone) {
            status = hasErrors ? "failed" : "current";
          } else if ((publication.status === PublicationStatus.REVIEW && reviewDone) || currentIndex > i) {
            status = "completed";
          }
          steps.push({ name: s, status: status });
        } else if (s === PublicationStatus.PUBLISHING) {
          let status = "";
          if (
            publication.status === PublicationStatus.PUBLISHING ||
            (publication.status === PublicationStatus.REVIEW && reviewDone)
          ) {
            status = hasErrors ? "failed" : "active";
          } else if (currentIndex > i) {
            status = "completed";
          }
          steps.push({ name: s, status: status });
        } else if (s === PublicationStatus.PUBLISHED) {
          let status = "";
          if (publication.status === PublicationStatus.PUBLISHED) {
            status = "completed";
          }
          steps.push({ name: s, status: status });
        }
      });
      return steps;
    },
    document(): TranslationDocument {
      return this.publicationInternal.document;
    },
    hasErrors(): boolean {
      return this.publicationInternal.hasErrors();
    },
    isLackOfOriginalDataError(): boolean {
      const p = this.publicationInternal;
      return this.hasErrors && p.errors[0].description === "The document does not have enough high quality data.";
    },
    isReview(): boolean {
      return this.publicationInternal.status === PublicationStatus.REVIEW;
    },
    isPublished(): boolean {
      return this.publicationInternal.status === PublicationStatus.PUBLISHED;
    },
    hasDocument(): boolean {
      return this.publicationInternal.document !== null;
    },
    showDocumentAnalysis(): boolean {
      return !this.isPublished && ((!this.hasErrors && this.hasDocument) || this.isLackOfOriginalDataError);
    }
  },
  methods: {
    publish() {
      this.continueDocumentReview();
      this.$store
        .dispatch("document/publishDocument", {
          uid: this.publication.uid,
          domainId: this.domain.id,
          contentTypeId: this.contentType.id,
          price: this.pricePerWord,
          description: this.description,
          anonymize: this.anonymize
        })
        .then(r => {
          this.publicationInternal = Publication.parse(r);
        });
    },
    checkStatus(publication: Publication) {
      this.$store
        .dispatch("document/fetchPublicationStatus", {
          id: publication.uid,
          currentStatus: publication.status
        })
        .then(publication => {
          if (publication.uid) {
            this.publicationInternal = publication;
            const hasErrors = publication.hasErrors();
            if (
              (publication.status !== PublicationStatus.REVIEW || publication.document?.pricePerWord > 0) &&
              publication.status !== PublicationStatus.PUBLISHED &&
              !hasErrors
            ) {
              this.checkStatus(publication);
            }
          } else {
            this.checkStatus(this.publication);
          }
        })
        .catch(() => {
          // FIXME add a max of tentatives condition
          this.checkStatus(this.publicationInternal);
        });
    },
    continueDocumentReview() {
      this.isLoading = true;
      let nextStep = this.documentReviewStep;
      nextStep++;
      if (nextStep == 6 && this.$store.getters["session/isAuthenticated"]) {
        nextStep++;
      }
      this.documentReviewStep = nextStep;
    },
    backDocumentReview() {
      this.isLoading = true;
      let nextStep = this.documentReviewStep;
      nextStep--;
      this.documentReviewStep = nextStep;
    },
    cancel() {
      this.$router.push("/publications");
    },
    setDocumentToPublish(isPublish: string) {
      this.isLoading = true;
      if (isPublish) {
        this.continueDocumentReview();
      } else {
        // TODO: buyBackDocument flow
      }
    },
    getBackToLastStep(isLast: string) {
      this.isLoading = true;
      if (isLast) {
        this.backDocumentReview();
      }
    },
    setDocumentToClean(isClean: string) {
      this.isLoading = true;
      if (isClean) {
        this.continueDocumentReview();
      }
    },
    setDocumentToAnonymize({ isPublish, isAnonymizeCheck }: { isPublish: string; isAnonymizeCheck: boolean }) {
      this.isLoading = true;
      this.anonymize = isAnonymizeCheck;
      if (isPublish) {
        this.continueDocumentReview();
      }
    },
    setDocumentSpecifications({
      domain,
      contentType
    }: {
      domain: PropType<Array<Domain>>;
      contentType: PropType<Array<ContentType>>;
    }) {
      this.isLoading = true;
      this.domain = domain;
      this.contentType = contentType;
      // Get suggested price
      this.$store
        .dispatch("document/fetchSuggestedPricePerWord", {
          id: this.publicationInternal.document?.uid,
          domainId: this.domain.id,
          contentTypeId: this.contentType.id
        })
        .then(suggestedPricePerWord => {
          this.suggestedPricePerWord = suggestedPricePerWord;
          this.continueDocumentReview();
        });
    },
    setDocumentPrice(price: number) {
      this.isLoading = true;
      this.pricePerWord = price;
      this.continueDocumentReview();
    },
    setDocumentDescription(description: string) {
      this.isLoading = true;
      this.description = description;
      this.continueDocumentReview();
    },
    setAuthenticationSignIn(form: SignInForm) {
      this.isLoading = true;
      this.isNewUser = false;
      this.$store
        .dispatch("session/signIn", form)
        .then(() => {
          this.continueDocumentReview();
        })
        .catch(error => {
          this.serviceErrors = error.response.data;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    setAuthenticationSignUp(form: SignUpForm) {
      this.isLoading = true;
      this.$store
        .dispatch("user/signUp", form)
        .then(() => {
          // Switch to sign in form (it is required to sign in after sign up)
          this.isNewUser = true;
        })
        .catch(error => {
          this.serviceErrors = error.response.data;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    done() {
      this.$router.push({ name: "UserDocuments" });
    }
  },
  components: {
    DocumentAnonymize,
    Button,
    Stepper,
    DocumentAnalysis,
    DocumentAnalysisLoading,
    DocumentCleaning,
    DocumentSpecification,
    DocumentPrice,
    DocumentDescription,
    Authentication,
    DocumentNotify,
    PublicationHeader,
    Alert
  }
});
</script>

<style lang="scss"></style>

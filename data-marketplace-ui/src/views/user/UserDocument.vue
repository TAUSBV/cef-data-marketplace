<template>
  <section>
    <form class="col-span-1 lg:col-span-3" v-if="!isFetchingData" novalidate>
      <div class="text-right">
        <router-link to="/users/documents" class="inline-block text-blue-700 font-medium mb-6">
          <img
            src="/assets/img/chevron-right-blue.svg"
            alt="chevron right blue"
            class="inline pl-1 transform rotate-180"
          />
          Back to documents
        </router-link>
      </div>
      <div class="w-full text-center border-b border-gray-200 pb-4">
        <img src="/assets/img/file-outline.svg" alt="File" class="inline mr-3" />
        <span class="font-medium">{{ documentInternal.name }}</span>
      </div>
      <div class="grid grid-cols-3 gap-8 mt-6">
        <div class="col-span-2">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-6">
            <div class="grid grid-cols-1 items-center justify-center">
              <DropDown
                id="domain"
                placeholder="Domain"
                :options="domains"
                :value="domainSelected"
                trackByKey="description"
                :is-required="false"
                @selected="val => selectDomain(val)"
                @closed="() => $v.form.domain.$touch()"
              />
            </div>
            <div class="grid grid-cols-1 items-center justify-center">
              <DropDown
                id="contentType"
                placeholder="Content Type"
                :options="contentTypes"
                :value="contentTypeSelected"
                trackByKey="description"
                :is-required="false"
                @selected="val => selectContentType(val)"
                @closed="() => $v.form.contentType.$touch()"
              />
            </div>
          </div>
          <div class="grid grid-cols-1 gap-8 mb-6">
            <div class="grid grid-cols-1 items-center justify-center">
              <label for="description" class="inline-block align-middle text-gray-800">
                Description
              </label>
              <FormTextarea
                id="description"
                label="Document description"
                placeholder="Type the document description"
                v-model="form.description"
                :rows="10"
                @input="setDocumentDescription"
              />
            </div>
          </div>
        </div>
        <div class="col-span-1">
          <DocumentCardLanguage
            :sourceLanguage="documentInternal.sourceLanguage"
            :targetLanguage="documentInternal.targetLanguage"
            class="bg-blue-200 bg-opacity-75 rounded mb-6 p-4"
          />
          <DocumentStatisticsSegment
            title="Total segments"
            :totalWordCount="documentInternal.currentStatistics.totalUnits"
            :totalSourceWordCount="documentInternal.currentStatistics.totalSourceWordCount"
            :totalTargetWordCount="documentInternal.currentStatistics.totalTargetWordCount"
            :sourceLanguageTag="documentInternal.sourceLanguage.tag"
            :targetLanguageTag="documentInternal.targetLanguage.tag"
            dotColor="transparent"
          />
          <DocumentStatisticsSegment
            title="High-quality segments"
            :totalWordCount="documentInternal.currentStatistics.totalUnitsOriginal"
            :totalSourceWordCount="documentInternal.currentStatistics.totalSourceOriginalWordCount"
            :totalTargetWordCount="documentInternal.currentStatistics.totalTargetOriginalWordCount"
            :sourceLanguageTag="documentInternal.sourceLanguage.tag"
            :targetLanguageTag="documentInternal.targetLanguage.tag"
            dotColor="transparent"
          />
          <DocumentStatisticsSegment
            title="Replica segments"
            :totalWordCount="documentInternal.currentStatistics.totalUnitsReplica"
            :totalSourceWordCount="documentInternal.currentStatistics.totalSourceReplicaWordCount"
            :totalTargetWordCount="documentInternal.currentStatistics.totalTargetReplicaWordCount"
            :sourceLanguageTag="documentInternal.sourceLanguage.tag"
            :targetLanguageTag="documentInternal.targetLanguage.tag"
            dotColor="transparent"
          />
          <DocumentStatisticsSegment
            title="Low-quality segments"
            :totalWordCount="documentInternal.currentStatistics.totalUnitsLowQuality"
            :totalSourceWordCount="documentInternal.currentStatistics.totalSourceLowQualityWordCount"
            :totalTargetWordCount="documentInternal.currentStatistics.totalTargetLowQualityWordCount"
            :sourceLanguageTag="documentInternal.sourceLanguage.tag"
            :targetLanguageTag="documentInternal.targetLanguage.tag"
            dotColor="transparent"
          />
        </div>
      </div>
      <div class="grid grid-cols-2 mb-6">
        <DocumentPriceSuggested
          :document="documentInternal"
          :suggestedPricePerWord="suggestedPricePerWord"
          @setUseSuggestedPrice="setUseSuggestedPrice"
        />
        <DocumentPriceTotal
          :document="documentInternal"
          :suggestedPricePerWord="suggestedPricePerWord"
          :useSuggestedPrice="useSuggestedPrice"
          @onPriceChange="setTotalPrice"
        />
      </div>
      <div class="flex justify-center">
        <Button type="button" @click="updateDocument" :disabled="isSubmitDisabled" :isLoading="isLoading">
          Save
        </Button>
        <div class="ml-8 text-center" v-if="isPublished">
          <Button type="button" @click="downloadDocument" :disabled="isSubmitDisabled">
            Download Cleaned
          </Button>
        </div>
        <div class="ml-8 text-center" v-else>
          You need to publish your document to be able to download the cleaned version of it.
        </div>
        <div class="ml-8 text-center">
          <Button type="button" :disabled="true">
            Unpublish
          </Button>
          <div class="text-sm text-gray-800">(coming soon)</div>
        </div>
      </div>
    </form>
    <div v-else class="col-span-1 lg:col-span-3">
      <UserDocumentLoading />
    </div>
  </section>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";
import Button from "@/components/Button.vue";
import DocumentCardLanguage from "@/components/DocumentCardLanguage.vue";
import DocumentPriceSuggested from "@/components/DocumentPriceSuggested.vue";
import DocumentPriceTotal from "@/components/DocumentPriceTotal.vue";
import DocumentStatisticsSegment from "@/components/DocumentStatisticsSegment.vue";
import DropDown from "@/components/DropDown.vue";
import FormTextarea from "@/components/FormTextarea.vue";
import UserDocumentLoading from "@/components/user/UserDocumentLoading.vue";
import { ContentType, Domain, SuggestedPricePerWord, UserDocument } from "@/models/models";

export default Vue.extend({
  name: "UserDocument",
  props: {
    document: {
      type: Object as PropType<UserDocument>
    }
  },
  data() {
    return {
      documentInternal: this.document,
      form: {
        domain: null,
        contentType: null,
        description: undefined
      },
      isFetchingData: true,
      isLoading: false,
      isSubmitDisabled: false,
      pricePerWord: null,
      suggestedPricePerWord: {} as SuggestedPricePerWord,
      useSuggestedPrice: false,
      domainSelected: "" as any,
      contentTypeSelected: "" as any
    };
  },
  mounted() {
    if (!this.document) {
      this.$store.dispatch("user/fetchDocument", this.$route.params.uid).then(r => {
        this.documentInternal = r;
        this.setInitialValues();
        this.getSuggestedPrice();
      });
    } else {
      this.setInitialValues();
      this.getSuggestedPrice();
    }
    this.$store.dispatch("baseline/getDomains");
    this.$store.dispatch("baseline/getContentTypes");
  },
  computed: {
    domains(): Array<Domain> {
      return this.$store.getters["baseline/domains"];
    },
    contentTypes(): Array<Domain> {
      return this.$store.getters["baseline/contentTypes"];
    },
    isPublished(): boolean {
      return this.documentInternal.status === "PUBLISHED";
    }
  },
  methods: {
    getSuggestedPrice() {
      this.$store
        .dispatch("document/fetchSuggestedPricePerWord", {
          id: this.documentInternal.uid,
          domainId: this.form.domain,
          contentTypeId: this.form.contentType
        })
        .then(suggestedPricePerWord => {
          this.suggestedPricePerWord = suggestedPricePerWord;
        })
        .finally(() => {
          this.isFetchingData = false;
        });
    },
    setInitialValues() {
      // Set domain
      this.form.domain = this.documentInternal.domain.id;
      this.domainSelected = this.documentInternal.domain;
      // Set content type
      this.form.contentType = this.documentInternal.contentType.id;
      this.contentTypeSelected = this.documentInternal.contentType;
      // Set description
      this.form.description = this.documentInternal.description;
      // Set price
      this.pricePerWord = this.documentInternal.pricePerWord;
    },
    selectDomain(domain: Domain) {
      this.domainSelected = domain;
      this.form.domain = domain.id;
      this.getSuggestedPrice();
    },
    selectContentType(contentType: ContentType) {
      this.contentTypeSelected = contentType;
      this.form.contentType = contentType.id;
      this.getSuggestedPrice();
    },
    setDocumentDescription(value: string) {
      this.form.description = value;
    },
    setUseSuggestedPrice() {
      this.useSuggestedPrice = true;
      this.isSubmitDisabled = false;
      this.setPricePerWord(this.suggestedPricePerWord.suggestedPricePerWord);
    },
    setTotalPrice({ price, isInvalid }: { price: number; isInvalid?: boolean }) {
      this.useSuggestedPrice = false;
      this.isSubmitDisabled = isInvalid;
      this.setPricePerWord(price);
    },
    setPricePerWord(price: number) {
      this.pricePerWord = isNaN(price) ? 0 : price;
    },
    updateDocument() {
      this.isLoading = true;
      this.$store
        .dispatch("user/updateDocument", {
          uid: this.documentInternal.uid,
          domainId: this.form.domain,
          contentTypeId: this.form.contentType,
          price: this.pricePerWord,
          description: this.form.description
        })
        .then()
        .finally(() => {
          this.isLoading = false;
        });
    },
    downloadDocument() {
      // window.open("/users/documents/" + this.documentInternal.uid + "/download", "_blank");
      const documentUid = this.documentInternal.uid;
      this.$router.push({ name: "UserDocumentsDownload", params: { documentUid } });
    }
  },
  components: {
    Button,
    DocumentCardLanguage,
    DocumentPriceSuggested,
    DocumentPriceTotal,
    DocumentStatisticsSegment,
    DropDown,
    FormTextarea,
    UserDocumentLoading
  }
});
</script>
<style lang="scss"></style>

<template>
  <section>
    <div class="grid grid-cols-2 md:grid-cols-2" v-if="uploadPercentage === 0">
      <div
        class="flex flex-col xs:col-span-2 sm:col-span-2 md:col-span-2 lg:col-span-1 bg-blue-900 p-8 my-auto xs:py-3 xs:px-0 rounded"
      >
        <DocumentUploadDropzone bgColor="bg-gray-100" @onSubmitDocument="submitDocument" />
      </div>
      <div class="flex flex-col xs:col-span-2 sm:col-span-2 md:col-span-2 lg:col-span-1 my-auto">
        <div class="bg-gray-100 p-4 lg:p-8" style="border-radius: 4px; box-shadow: 0 1px 5px 0 rgba(0, 0, 0, 0.1);">
          <div class="flex overflow-hidden mb-8">
            <div class="float-left" style="min-width: 25px;max-width: 25px;width: 25px;">
              <img src="/assets/img/check-yellow.svg" alt="check" class="w-full" />
            </div>
            <div class="float-left ml-6">
              <div
                class="text-xl leading-30 tracking-5.0 xs:text-base xs:leading-relaxed xs:tracking-wide font-bold mb-2"
              >
                CLEAN
              </div>
              <div class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27">
                Prepare your data with free automatic smart deduplication, language inconsistency detection and
                identification of fluency or adequacy errors.
              </div>
            </div>
          </div>
          <div class="flex overflow-hidden mb-8">
            <div class="float-left" style="min-width: 25px;max-width: 25px;width: 25px;">
              <img src="/assets/img/check-yellow.svg" alt="check" class="w-full" />
            </div>
            <div class="float-left pl-6">
              <div
                class="text-xl leading-30 tracking-5.0 xs:text-base xs:leading-relaxed xs:tracking-wide font-bold mb-2"
              >
                ANONYMIZE
              </div>
              <div class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27">
                Use our free anonymization service to protect privacy by filtering out named entities and personal
                information in your data.
              </div>
            </div>
          </div>
          <div class="flex overflow-hidden mb-8">
            <div class="float-left" style="min-width: 25px;max-width: 25px;width: 25px;">
              <img src="/assets/img/check-yellow.svg" alt="check" class="w-full" />
            </div>
            <div class="float-left ml-6">
              <div
                class="text-xl leading-30 tracking-5.0 xs:text-base xs:leading-relaxed xs:tracking-wide font-bold mb-2"
              >
                PUBLISH
              </div>
              <div class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27">
                Upload to see the clean and anonymized (coming soon) version of your data. Publish the data to get these
                services for free. Uploaded data is not stored unless published.
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-1" v-else>
      <DocumentUploadProgress :percentage="uploadPercentage" />
    </div>
    <Alert v-if="genericErrors.length > 0" class="mt-6" :messages="genericErrors" type="DANGER" />
  </section>
</template>
<script lang="ts">
import Vue from "vue";
import DocumentUploadDropzone from "@/views/publication/DocumentUploadDropzone.vue";
import DocumentUploadProgress from "@/views/publication/DocumentUploadProgress.vue";
import Alert from "@/components/Alert.vue";
export default Vue.extend({
  props: {
    bgColor: {
      type: String
    }
  },
  data() {
    return {
      uploadPercentage: 0,
      genericErrors: []
    };
  },
  methods: {
    submitDocument(document: File) {
      const formData = new FormData();
      formData.append("document", document);
      this.$store
        .dispatch("document/uploadDocument", {
          formData,
          onProgress: this.onUploadProgress
        })
        .then(publication => {
          const publicationId = publication.uid;
          this.$router.push({
            name: "DocumentReview",
            params: { publicationId }
          });
        })
        .catch(error => {
          this.uploadPercentage = 0;
          this.genericErrors.push(error.message);
        });
    },
    onUploadProgress(uploadPercentage: number) {
      this.uploadPercentage = uploadPercentage;
    }
  },
  components: {
    DocumentUploadDropzone,
    DocumentUploadProgress,
    Alert
  }
});
</script>

<template>
  <div>
    <div>
      <div>
        <SearchBar :isMD="true" :hasBorder="false" :disable="isSearchDisable" @onSubmitSample="onSubmitSample" />
      </div>
      <div class="px-4" :class="learnMore ? 'lg:pb-6 md:pb-12' : ''">
        <div class="border-t-2 py-4">
          <form action="/matching-data" @submit.prevent="submitDocument" id="matchyourdata" novalidate>
            <div class="grid grid-cols-2 md:grid-cols-2 lg:gap-x-14 md:gap-y-4 lg:gap-y-0">
              <div class="xs:col-span-2 sm:col-span-2 md:col-span-2 lg:col-span-1 xs:py-3 md:py-0 xs:px-0 rounded">
                <div class="h-full rounded" :class="bgColor">
                  <input
                    name="file"
                    type="file"
                    ref="file"
                    style="display: none"
                    @change="addDocument($event.target.files)"
                  />
                  <div
                    class="  flex items-center justify-center border-2 border-blue-700 align-middle w-full h-full rounded xs:py-10 md:py-10 lg:py-0"
                    :class="[
                      dragging ? 'border-blue-900 text-blue-900' : 'border-blue-700',
                      form.document ? 'border-solid' : 'border-dashed'
                    ]"
                    id="dropzone"
                    @drop.prevent="addDocument($event.dataTransfer.files)"
                    @dragover.prevent="onDragging"
                    @dragexit.prevent="stopDragging"
                  >
                    <div class="w-full">
                      <div class=" flex flex-col items-center justify-center">
                        <div class="relative mb-2 mx-auto" style="width: 50px;">
                          <img src="/assets/img/icons-file-file-upload.svg" alt="TXT File" />
                        </div>
                        <h1
                          v-if="form.document == null"
                          class="text-lg tracking-0.3 font-medium text-center xs:text-base text-gray-900 xs:tracking-0.27"
                        >
                          Upload a document (*.tsv)
                        </h1>
                        <h2
                          v-if="form.document != null"
                          class="w-1/2 mx-auto xs:w-1/4 text-center text-gray-900 text-base font-medium leading-relaxed tracking-0.27"
                        >
                          <div class="overflow-ellipsis overflow-hidden whitespace-nowrap">
                            {{ form.document.name }}
                          </div>
                        </h2>
                        <h2
                          v-if="form.document == null"
                          class="text-center text-gray-900 text-base leading-relaxed tracking-0.27"
                        >
                          Drag file here or
                          <button class="p-1" @click.prevent="$refs.file.click()">
                            <span class="text-blue-700">browse</span>
                          </button>
                        </h2>
                      </div>
                    </div>
                  </div>
                </div>
                <span class="text-sm  tracking-0.23 font-normal text-gray-800 my-1"
                  >Document preparation
                  <a href="/assets/files/sampleTemplate.zip" download class="text-blue-700 cursor-pointer"> template</a>
                  (5-20k segments)</span
                >
              </div>
              <div
                class="flex flex-col xs:col-span-2 sm:col-span-2 md:col-span-2 lg:col-span-1 xs:py-3 xs:pt-8 md:pt-10 lg:pt-0 xs:px-0 rounded text-gray-900"
              >
                <div class="mb-5 mt-1">
                  <h2 class="text-lg tracking-0.3 font-normal text-gray-900">My sample is:</h2>
                </div>
                <div class="flex items-center xs:ml-6 lg:gap-x-8">
                  <label for="monolingual" class="flex items-center space-x-3 pr-5">
                    <input
                      type="radio"
                      id="monolingual"
                      value="monolingual"
                      v-model="form.type"
                      class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                    />
                    <span class="font-normal cursor-pointer text-lg tracking-0.3">Monolingual</span>
                  </label>
                  <label for="bilingual" class="flex items-center space-x-3 pr-5">
                    <input
                      type="radio"
                      id="bilingual"
                      value="bilingual"
                      v-model="form.type"
                      class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                    />
                    <span class="font-normal cursor-pointer text-lg tracking-0.3">Bilingual</span>
                  </label>
                </div>
                <div v-if="form.type === 'monolingual'" class="flex flex-col mx-6 mt-3 ">
                  <label for="sourcelanguage" class="flex items-center space-x-3 pr-5">
                    <input
                      type="radio"
                      id="sourcelanguage"
                      value="source"
                      v-model="form.sampleLanguage"
                      class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                    />
                    <span class="font-normal cursor-pointer text-gray-800 text-base leading-relaxed tracking-0.27"
                      >Source Language</span
                    >
                  </label>

                  <label for="targetlanguage" class="flex items-center space-x-3 pr-5">
                    <input
                      type="radio"
                      id="targetlanguage"
                      value="target"
                      v-model="form.sampleLanguage"
                      class="appearance-none cursor-pointer h-5 w-5 shadow rounded-full border border-gray-300 checked:border-blue-600 checked:bg-blue-300 focus:outline-none"
                    />
                    <span class="font-normal cursor-pointer text-gray-800 text-base leading-relaxed tracking-0.27"
                      >Target Language</span
                    >
                  </label>
                </div>
                <div
                  class="flex lg:flex-col xs:flex-col xs:items-center mt-6 md:flex-row md:gap-x-2 xs:gap-y-2 lg:gap-y-2"
                >
                  <div class=" w-60 dd">
                    <DropDown
                      class="border-1 border-gray-900"
                      id="domain"
                      placeholder="Domain"
                      :options="domains"
                      :value="domainSelected"
                      trackByKey="description"
                      :is-required="false"
                      @selected="val => selectDomain(val)"
                    />
                  </div>
                  <div class=" w-60 email-text">
                    <FormInput
                      type="email"
                      id="email"
                      :value="$v.form.email"
                      v-model="form.email"
                      :placeholder="emailPlaceholder"
                      :isRequired="true"
                      :isInvalid="$v.form.email.$error"
                      @input="val => updateEmail(val)"
                      customClass="font-normal outline-none border border-gray-300 shadow-none"
                    />
                  </div>
                </div>
              </div>
            </div>
          </form>
          <div v-if="!learnMore" :class="!learnMore ? 'relative mt-12' : ''">
            <div class="absolute bottom-0 right-0 flex flex-row items-center text-lg tracking-0.3 font-normal pt-12">
              <span>Learn more about matching data</span>
              <a href="/matching-data">
                <img src="/assets/img/chevron-right-black.svg" class=" ml-2 w-2 h-3 cursor-pointer" />
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="genericErrors.length > 0" class="px-6 xs:px-4 mt-1">
      <Alert v-if="genericErrors.length > 0" :messages="[genericErrors[genericErrors.length - 1]]" type="DANGER" />
    </div>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import { required, email } from "vuelidate/lib/validators";
import { Domain, Language, LanguageSourceTargetCount, User } from "@/models/models";
import FormInput from "@/components/FormInput.vue";
import DropDown from "@/components/DropDown.vue";
import SearchBar from "@/components/search/SearchBar.vue";
import Alert from "@/components/Alert.vue";
export default Vue.extend({
  props: {
    bgColor: {
      type: String
    },
    learnMore: {
      type: Boolean,
      required: false
    }
  },
  data() {
    return {
      form: {
        document: null,
        type: null,
        sampleLanguage: null,
        domainId: null,
        sourceLanguageId: null,
        targetLanguageId: null,
        email: ""
      },
      dragging: false,
      domainSelected: "" as {},
      sourceLanguageSelected: "" as {},
      targetLanguageSelected: "" as {},
      languages: {
        sources: [] as LanguageSourceTargetCount[],
        targets: [] as LanguageSourceTargetCount[]
      },
      genericErrors: [],
      emailPlaceholder: "Email"
    };
  },
  created() {
    this.$store.dispatch("baseline/getDomains");
  },
  validations: {
    form: {
      email: {
        required,
        email
      }
    }
  },
  mounted() {
    if (this.isAuthenticated) {
      this.$store.dispatch("user/fetch").then(User => {
        this.emailPlaceholder = User.details.email;
        this.form.email = User.details.email;
      });
    }
  },
  computed: {
    domains(): Array<Domain> {
      return this.$store.getters["baseline/domains"];
    },
    isAuthenticated(): boolean {
      return this.$store.getters["session/isAuthenticated"];
    },
    isSearchDisable() {
      return this.form.email == "" || this.form.type == null || this.form.document == null;
    }
  },
  methods: {
    selectDomain(domain: Domain) {
      this.domainSelected = domain;
      this.form.domainId = domain.id;
    },
    selectSourceLanguage(sourceLanguage: Language) {
      this.form.sourceLanguageId = sourceLanguage.id;
      this.sourceLanguageSelected = sourceLanguage;
    },
    selectTargetLanguage(sourceLanguage: Language) {
      this.form.targetLanguageId = sourceLanguage.id;
      this.targetLanguageSelected = sourceLanguage;
    },
    addDocument(droppedFiles: Array<File>) {
      this.dragging = false;
      if (droppedFiles) {
        this.form.document = droppedFiles[0];
      }
    },
    removeDocument() {
      this.form.document = null;
    },
    onDragging() {
      this.dragging = true;
    },
    stopDragging() {
      this.dragging = false;
    },
    updateEmail(value: string) {
      this.form.email = value;
      this.$v.form.email.$touch();
    },
    submitDocument() {
      if (
        this.form.email != "" &&
        this.form.type != null &&
        this.form.sourceLanguageId != null &&
        this.form.targetLanguageId != null &&
        this.form.document != null
      ) {
        const formData = new FormData();

        formData.append("file", this.form.document);
        formData.append("type", this.form.type);
        if (this.form.sampleLanguage != null || this.form.type != "bilingual") {
          formData.append("sampleLanguage", this.form.sampleLanguage);
        }
        if (this.form.domainId != null) {
          formData.append("domainId", this.form.domainId.toString());
        }
        formData.append("email", this.form.email);
        formData.append("sourceLanguageId", this.form.sourceLanguageId);
        formData.append("targetLanguageId", this.form.targetLanguageId);

        this.$store
          .dispatch("matchingDataSearch/uploadSample", {
            formData
          })
          .then(response => {
            const requestId = response;
            this.$router.push({
              name: "MDSearchResult",
              params: { requestId }
            });
          })
          .catch(error => {
            this.genericErrors.push(error.response.data);
          });
      } else if (this.form.email == "") {
        this.genericErrors.push("Email is required");
      } else if (this.form.type == null) {
        this.genericErrors.push("Please choose your sample language");
      } else if (this.form.document == null) {
        this.genericErrors.push("Please upload the document");
      } else {
        this.genericErrors.push("Please try again");
      }
    },
    onSubmitSample(sourceLanguage: Language, targetLanguage: Language) {
      this.selectSourceLanguage(sourceLanguage);
      this.selectTargetLanguage(targetLanguage);
      this.submitDocument();
    }
  },
  components: {
    SearchBar,
    FormInput,
    DropDown,
    Alert
  }
});
</script>

<style lang="scss">
.dd {
  .open {
    .multiselect {
      @apply border;
      @apply rounded-t;
      @apply border-gray-300;

      .multiselect-placeholder {
        @apply text-gray-200;
        @apply font-normal;
      }
    }
  }

  .closed {
    .multiselect {
      .multiselect-placeholder {
        @apply text-gray-900;
      }
    }
  }

  /* Multiselect (DropDown Component) styles overrides
 * only for this specific occurrence
 */
  .multiselect {
    position: relative;
    width: 100%;
    height: 3.5rem;
    @apply border;
    @apply font-normal;
    @apply shadow-none;
    @apply rounded;
    @apply pl-4;
    @apply pr-2;
    @apply border-gray-300;

    .multiselect-input {
      @apply text-gray-900;

      .multiselect-single-label {
        @apply flex;
        @apply items-center;
        @apply pl-0;
        @apply font-normal;
        @apply text-lg;
        @apply text-gray-900;
      }
      .multiselect-placeholder {
        @apply flex;
        @apply items-center;
        @apply pl-0;
        @apply font-normal;
        @apply text-lg;
        @apply text-gray-400;
        @apply ml-0;
      }
      @media (max-width: 426px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-lg;
          @apply ml-2;
        }
      }
    }

    .multiselect-options {
      margin-top: 0;
      margin-left: -1px;
      width: calc(100% + 2px);
      @apply rounded-b;
      @apply rounded-t-none;
      @apply shadow-none;
      @apply border;
      @apply pl-4;
      @apply pr-4;
      @apply pt-4;
      @apply pb-4;
      @apply text-lg;
      @apply leading-7;
      @apply tracking-wide;
      @apply text-gray-900;
      @apply border-gray-300;
      @apply border-t-0;

      &::before {
        content: "";
        position: absolute;
        width: 90%;
        left: 50%;
        transform: translateX(-50%);
        top: 0;
        border-top: 1px solid #e4e4eb;
      }

      .multiselect-no-options {
        @apply pl-4;
        @apply pr-4;
        @apply pt-4;
        @apply pb-4;
        @apply bg-yellow-300;
        @apply bg-opacity-30;
        @apply border-gray-300;
        @apply text-gray-900;
      }

      .multiselect-option {
        &.is-pointed {
          @apply rounded;
          @apply bg-opacity-45;
          @apply text-gray-900;
        }

        &.is-selected {
          @apply bg-blue-200;
          @apply bg-opacity-45;
          @apply text-blue-700;
        }
      }
    }

    &.is-single {
      .multiselect-search {
        input {
          padding-left: 0;
          @apply text-gray-900;
          @apply text-lg;
          @apply leading-7;
          @apply tracking-wide;
        }
      }
    }
  }

  @media (max-width: 426px) {
    .multiselect {
      @apply pl-1;
      @apply pr-1;
    }
  }

  .is-single .multiselect-search input {
    padding-left: 0px;
    @apply text-gray-900;
    @apply text-lg;
    @apply leading-7;
    @apply tracking-wide;
  }
  .multiselect-no-options {
    @apply pl-4;
    @apply pr-4;
    @apply pt-4;
    @apply pb-4;
    @apply rounded;
    @apply bg-yellow-300;
    @apply bg-opacity-30;
    @apply border-gray-300;
    @apply text-gray-900;
  }

  /* Scrollbar styling */
  .multiselect-options::-webkit-scrollbar {
    width: 8px;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-track {
    background: #f2f2f580;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-thumb {
    background: #e4e4eb;
    border-radius: 3px;
  }
  .multiselect-options::-webkit-scrollbar-thumb:hover {
    background: #d6d6db;
    border-radius: 3px;
  }
}
.email-text input:not([type="checkbox"]) {
  height: 3.5rem;
  border-radius: 0.25rem;
  @apply text-lg;
}
</style>

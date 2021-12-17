<template>
  <section class="mx-4">
    <div class="mb-8">
      <div class="text-xl leading-30 tracking-0.37 font-medium text-gray-900">Domain and content type</div>
      <div class="text-lg tracking-0.3 font-normal mt-2 text-gray-900">
        Please select the domain and the content type of your dataset for a more accurate price suggestion.
      </div>
    </div>
    <form novalidate>
      <div class="flex flex-row dd">
        <div class="flex w-full justify-center">
          <DropDown
            class="w-64 mr-10"
            id="domain"
            placeholder="Domain"
            :options="domains"
            :value="domainSelected"
            trackByKey="description"
            :is-required="true"
            :is-invalid="$v.form.domain.$error"
            @selected="val => selectDomain(val)"
            @closed="() => $v.form.domain.$touch()"
          />
          <DropDown
            class="w-64"
            id="contentType"
            placeholder="Content Type"
            :options="contentTypes"
            :value="contentTypeSelected"
            trackByKey="description"
            :is-required="true"
            :is-invalid="$v.form.contentType.$error"
            @selected="val => selectContentType(val)"
            @closed="() => $v.form.contentType.$touch()"
          />
        </div>
      </div>
      <div class="mt-20 grid md:grid-cols-3 grid-cols-2">
        <div class="self-center">
          <BackToPreviousPage label="to Anonymization" @getBackToLastStep="backToAnonymization" />
        </div>
        <div class="text-center">
          <Button @click="setDocumentSpecifications" :disabled="isSubmitDisabled" :isLoading="isLoading">
            Next
          </Button>
        </div>
      </div>
    </form>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import { required } from "vuelidate/lib/validators";
import { ContentType, Domain } from "@/models/models";
import Button from "@/components/Button.vue";
import DropDown from "@/components/DropDown.vue";
import BackToPreviousPage from "@/components/BackToPreviousPage.vue";

export default Vue.extend({
  props: {
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  created() {
    this.$store.dispatch("baseline/getDomains");
    this.$store.dispatch("baseline/getContentTypes");
  },
  data() {
    return {
      form: {
        domain: null,
        contentType: null
      },
      domainSelected: "" as {},
      contentTypeSelected: "" as {}
    };
  },
  validations: {
    form: {
      domain: {
        required
      },
      contentType: {
        required
      }
    }
  },
  computed: {
    isSubmitDisabled(): boolean {
      return this.$v.$error || this.$v.$invalid;
    },
    domains(): Array<Domain> {
      return this.$store.getters["baseline/domains"];
    },
    contentTypes(): Array<ContentType> {
      return this.$store.getters["baseline/contentTypes"];
    }
  },
  methods: {
    selectDomain(domain: Domain) {
      this.domainSelected = domain;
      this.form.domain = domain.id;
    },
    selectContentType(contentType: ContentType) {
      this.contentTypeSelected = contentType;
      this.form.contentType = contentType.id;
    },
    setDocumentSpecifications() {
      this.$emit("setDocumentSpecifications", {
        domain: this.domainSelected,
        contentType: this.contentTypeSelected
      });
    },
    backToAnonymization() {
      this.$emit("getBackToLastStep", true);
    }
  },
  components: {
    Button,
    DropDown,
    BackToPreviousPage
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
        @apply text-gray-900;
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
    @apply pl-6;
    @apply pr-2;
    @apply border-gray-300;

    .multiselect-input {
      @apply text-gray-900;

      .multiselect-single-label,
      .multiselect-placeholder {
        @apply flex;
        @apply items-center;
        @apply pl-0;
        @apply leading-7;
        @apply font-normal;
        @apply text-xl;
        @apply text-gray-900;
        @apply tracking-wide;
      }
      @media (max-width: 426px) {
        .multiselect-single-label,
        .multiselect-placeholder {
          @apply text-lg;
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
      @apply pl-2;
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
</style>

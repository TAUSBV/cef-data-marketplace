<template>
  <section>
    <div class="flex items-center border border-blue-700 rounded text-lg mb-8 py-3 px-6">
      <img src="/assets/img/bullhorn-outline.svg" alt="Bullhorn" class="flex-initials mr-6 w-6 h-6" />
      <div class="flex-initials">
        Add a description of your dataset to make it stand out more in the Data Marketplace.
      </div>
    </div>
    <form novalidate>
      <FormTextarea
        label="Document description"
        placeholder="Type the document description"
        @input="updateDocumentDescription"
      />
      <div class="text-center">
        <Button @click="setDocumentDescription" :isLoading="isLoading">
          {{ buttonLabel }}
        </Button>
      </div>
    </form>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import FormTextarea from "@/components/FormTextarea.vue";

export default Vue.extend({
  props: {
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      form: {
        description: null
      }
    };
  },
  computed: {
    buttonLabel(): string {
      const description = this.form.description;
      return description == null || description === "" ? "Skip" : "Next";
    }
  },
  methods: {
    updateDocumentDescription(value: string) {
      this.form.description = value;
    },
    setDocumentDescription() {
      this.$emit("setDocumentDescription", this.form.description);
    }
  },
  components: {
    Button,
    FormTextarea
  }
});
</script>

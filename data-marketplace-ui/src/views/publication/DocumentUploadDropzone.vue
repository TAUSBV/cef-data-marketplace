<template>
  <div>
    <form action="/publications" @submit.prevent="submitDocument" novalidate>
      <input name="file" type="file" ref="file" style="display: none" @change="addDocument($event.target.files)" />
      <div class="flex flex-col justify-center mx-auto rounded" :class="bgColor">
        <div class="flex flex-col justify-center p-6 xs:p-0 border rounded shadow-input">
          <div class="w-full">
            <div
              class="border-2 border-blue-700 align-middle xs:border-0 w-full rounded lg:py-20"
              :class="[
                dragging ? 'border-blue-900 text-blue-900' : 'border-blue-700',
                form.document ? 'border-solid' : 'border-dashed'
              ]"
              id="dropzone"
              @drop.prevent="addDocument($event.dataTransfer.files)"
              @dragover.prevent="onDragging"
              @dragexit.prevent="stopDragging"
            >
              <div class="mx-8">
                <div class="relative mt-8 mb-12 mx-auto" style="width: 70px;">
                  <img src="/assets/img/file-tmx-big-icon.svg" alt="TMX File" />
                  <img
                    class="absolute"
                    style="right: -1.135rem; top: 4.90rem"
                    src="/assets/img/upload.svg"
                    alt="Upload"
                  />
                </div>
                <h1 class="text-xl leading-30 tracking-0.44 text-center xs:text-base xs:tracking-0.27 font-medium">
                  Upload a document (*.TMX)
                </h1>
                <h2
                  class="mt-8 mb-4 xs:text-base sm:text-sm lg:text-lg text-left text-gray-900 text-opacity-50 font-medium"
                >
                  Drag bilingual TMX format file here or
                  <button class="p-1" @click.prevent="$refs.file.click()">
                    <span class="text-blue-700">browse</span>
                  </button>
                </h2>
                <h2
                  v-if="$route.path !== '/publications'"
                  class="mb-8 xs:text-base sm:text-sm lg:text-lg text-left text-gray-900 text-opacity-50 font-medium"
                >
                  Document size up to 1MB.
                  <br />
                  For larger files please click
                  <router-link to="/publications" class="text-blue-700">
                    here.
                  </router-link>
                </h2>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  props: {
    bgColor: {
      type: String
    }
  },
  data() {
    return {
      form: {
        document: null
      },
      dragging: false
    };
  },
  methods: {
    addDocument(droppedFiles: Array<File>) {
      this.dragging = false;
      if (droppedFiles) {
        this.form.document = droppedFiles[0];
        this.submitDocument();
      }
    },
    removeDocument() {
      this.form.document = null;
    },
    submitDocument() {
      this.$emit("onSubmitDocument", this.form.document);
    },
    onDragging() {
      this.dragging = true;
    },
    stopDragging() {
      this.dragging = false;
    }
  }
});
</script>

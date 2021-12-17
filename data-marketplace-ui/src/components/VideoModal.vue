<template>
  <div class="flex flex-col">
    <div class="flex mb-2">
      <button @click="modelAction()">
        <img src="/assets/img/icons-action-play-circle.svg" class="xl:w-20 lg:w-16" />
      </button>
    </div>
    <div v-if="modal" class="viewmodal">
      <div class=" modal-open flex justify-center items-center">
        <div class="relative w-3/4  mx-auto my-10 h-screen">
          <button
            class="absolute right-1 text-white font-bold cursor-pointer"
            style="z-index: 100; "
            @click="modelAction()"
          >
            X
          </button>
          <video controls autoplay :src="'/assets/videos/' + vname" type="video/mp4" class="mx-auto 2xl:min-w-full" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "VideoModal",
  data() {
    return {
      modal: false,
      isPlaying: false
    };
  },
  methods: {
    modelAction() {
      if (this.modal === false) {
        this.modal = true;
        document.body.classList.add("modal-open");
      } else {
        this.modal = false;
        document.body.classList.remove("modal-open");
      }
    }
  },
  props: {
    vname: {
      type: String,
      required: true
    }
  }
});
</script>

<style>
.viewmodal {
  position: fixed;
  z-index: 98;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  transition: opacity 0.2s ease;
}
body.modal-open {
  overflow-y: hidden;
}
</style>

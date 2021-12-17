<template>
  <div>
    <div class="flex items-center justify-between pl-8 toggler cursor-pointer" @click="toggleOpen">
      <span class="text-lg tracking-0.3 pr-2">
        {{ title }}
      </span>
      <span>
        <img
          class="transition duration-200 ease-in-out"
          :class="!open ? 'transform rotate-180' : 'transform rotate-0'"
          src="/assets/img/chevron-down.svg"
        />
      </span>
    </div>
    <div
      class="collapsible scrollable slim-scrollbar xs:static-bar sm:static-bar md:static-bar lg:disappearing-bar"
      :class="!open ? 'open' : ''"
    >
      <div class="content-holder" :class="!open ? 'visible' : ''">
        <slot name="content" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AccordionFilters",
  components: {},
  props: {
    title: {
      type: String
    },
    index: {
      type: Number
    },
    isOpen: {
      /* Pass the prop isOpen (true) if you want the accordion filter to be opened on page render */
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      open: {
        type: Boolean,
        default: false
      }
    };
  },
  methods: {
    toggleOpen: function() {
      this.open = !this.open;
      this.$emit("accordionOpen", this.open);
    }
  },
  created() {
    if (this.$props.isOpen) {
      this.open = !this.open;
    }
  }
};
</script>

<style lang="scss" scoped>
.toggler {
  z-index: 10;
}
.collapsible {
  z-index: 5;
  opacity: 0;
  max-height: 0;
  margin-top: 0;
  margin-bottom: 0;
  transition: all 0.2s ease-in-out;

  &.scrollable {
    @apply break-all;
    max-height: 210px;
  }

  &.open {
    opacity: 1;
    max-height: 500px;
    margin-top: 20px;
    margin-bottom: 8px;
  }

  .content-holder {
    display: none;

    div {
      &:first-of-type {
        @apply mb-1.5;
      }
    }

    &.visible {
      display: block;
    }
  }
}
</style>

<template>
  <ul class="steps">
    <li v-for="step in steps" :key="step.name" :class="step.status" :style="stepsWidth">
      <span class="text-xl leading-30 tracking-5.0 font-normal xs:text-base xs:leading-relaxed xs:tracking-wide">{{
        step.name | cleanString
      }}</span>
    </li>
  </ul>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";

export default Vue.extend({
  name: "Stepper",
  props: {
    steps: {
      type: Array as PropType<Array<{}>>,
      required: true
    }
  },
  computed: {
    stepsWidth() {
      const steps = this.steps as Array<{}>;
      return {
        width: 100 / steps.length + "%"
      };
    }
  }
});
</script>

<style lang="scss">
.steps {
  counter-reset: step;

  & li {
    @apply float-left relative mb-12 text-gray-400 text-opacity-50 text-center text-base uppercase tracking-widest list-none;

    &::before {
      width: 2.25rem;
      height: 2.25rem;
      margin-left: auto;
      margin-right: auto;
      content: counter(step);
      counter-increment: step;
      @apply block text-gray-300 leading-8 bg-white border-2 border-gray-300 text-base tracking-normal rounded-full;
    }

    &::after {
      position: absolute;
      top: 23%;
      left: -40%;
      width: 80%;
      height: 2px;
      content: "";
      z-index: -1;
      @apply bg-gray-300;
    }

    &:first-child::after {
      content: none;
    }

    &.current {
      @apply text-gray-900 tracking-widest;
      &::before {
        @apply text-blue-900 font-medium bg-blue-300 border border-transparent border-2 rounded-full;
      }
      &::after {
        @apply bg-blue-500;
      }
    }

    &.active {
      @apply text-blue-900 tracking-widest;
      &::before {
        content: "";
        @apply border-4 border-blue-900 rounded-full;
        border-right: 0.25em solid transparent;
        -webkit-animation: spinner-border 0.75s linear infinite;
        animation: spinner-border 0.75s linear infinite;
      }
      &::after {
        @apply bg-blue-500;
      }
    }

    &.completed {
      @apply text-gray-900 tracking-widest;
      &::before {
        content: "";
        background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='19' height='15' viewBox='0 0 19 15'%3E%3Cpath fill='%23069' d='M6.736 13.152L.995 7.41c-.345-.345-.345-.904 0-1.25l1.249-1.249c.345-.345.904-.345 1.249 0L7.361 8.78 15.646.495c.345-.345.904-.345 1.249 0l1.25 1.249c.344.345.344.904 0 1.25L7.984 13.151c-.344.345-.904.345-1.249 0z'/%3E%3C/svg%3E%0A")
          no-repeat top 50% left 50% #cfefff;
        @apply text-blue-900 border-blue-500;
      }
      &::after {
        @apply bg-blue-300;
      }
    }

    &.failed {
      @apply text-red-900 tracking-widest;

      &::before {
        @apply font-extrabold text-red-900 border border-red-900;
        content: "\2715";
      }
      &::after {
        @apply bg-red-900;
      }
    }
  }
}

@media (min-width: 790px) and (max-width: 1200px) {
  .steps li span {
    font-size: 18px;
    letter-spacing: 1px;
  }
}
@media (min-width: 600px) and (max-width: 789px) {
  .steps li span {
    font-size: 12px;
    letter-spacing: 1px;
  }
}
@media (max-width: 599px) {
  .steps li span {
    visibility: hidden;
  }
  .steps .current span {
    visibility: visible;
  }
}
@media (min-width: 451px) and (max-width: 599px) {
  .steps .current span {
    margin-left: -10px;
  }
}
@media (max-width: 450px) {
  .steps .current span {
    margin-left: -20px;
  }
}

@keyframes spinner-border {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

@media (max-width: 767px) {
  .steps {
    li {
      font-size: 0.45rem;
    }
  }
}
</style>

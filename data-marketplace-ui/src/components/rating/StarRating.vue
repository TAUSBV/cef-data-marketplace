<template>
  <div class="flex items-center" v-if="hasScore || editMode">
    <span class="hidden sm:block font-medium text-sm tracking-0.23 mr-2" v-if="showScore">
      {{ scoreFloatingPoint }}
    </span>
    <div class="relative" :style="[dynamicWidthStyle]" :class="[dynamicHeightClasses]">
      <!-- Filled Stars -->
      <div class="absolute top-0 left-0 overflow-hidden" :style="{ width: starFillPercentage }">
        <span
          :id="`star-full-${index}`"
          class="text-yellow-800 font-black hover:opacity-75 transition-all star-icon-full"
          :class="[starLargeSizeClasses, starDisabledClasses]"
          v-for="index in reverseOrder(numberOfStars)"
          :key="index"
          @click.prevent="setRating(index)"
        >
          &#9733;
        </span>
      </div>
      <!-- Empty Stars -->
      <div :style="{ transform: flipHorizontally }">
        <span
          :id="`star-empty-${index}`"
          class="text-gray-400 font-black transition-all hover:opacity-75 star-icon-empty"
          :class="[starLargeSizeClasses, starDisabledClasses, selectedIndex === index && 'selected']"
          v-for="index in reverseOrder(numberOfStars)"
          :key="index"
          @click.prevent="setRating(index)"
        >
          &#9733;
        </span>
        <div class="emoji-wrapper w-full overflow-hidden" v-if="showEmojis">
          <div class="emoji flex flex-col items-center">
            <img src="/assets/img/emoji-thinking-face.png" alt="thinking face emoji" class="no-rating flex-shrink-0" />
            <img src="/assets/img/emoji-confounded-face.png" alt="confounded face emoji" class="flex-shrink-0" />
            <img src="/assets/img/emoji-frowning-face.png" alt="frowning face emoji" class="flex-shrink-0" />
            <img src="/assets/img/emoji-neutral-face.png" alt="neutral face emoji" class="flex-shrink-0" />
            <img src="/assets/img/emoji-slightly-smiling-face.png" alt="smiling face emoji" class="flex-shrink-0" />
            <img src="/assets/img/emoji-star-struck-face.png" alt="star struck face emoji" class="flex-shrink-0" />
          </div>
        </div>
      </div>
    </div>
    <span class="hidden sm:block text-sm tracking-0.23 ml-2" v-if="showScore">
      {{ numberOfReviews }} review<span v-if="numberOfReviews > 1">s</span>
    </span>
  </div>
  <span class="text-sm tracking-0.23 text-gray-800" v-else>
    Not enough reviews
  </span>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "StarRating",
  data() {
    return {
      selectedIndex: this.score > 0 ? parseFloat(this.score as string) - 1 : this.score === 0 ? null : this.score
    };
  },
  props: {
    score: {
      type: [String, Number],
      default: 0,
      validator: val => val >= 0
    },
    numberOfReviews: {
      type: [String, Number]
    },
    numberOfStars: {
      type: Number,
      default: 5
    },
    size: {
      type: String,
      default: "sm",
      validator: val => ["lg", "sm"].includes(val)
    },
    editMode: {
      type: Boolean,
      default: false
    },
    showScore: {
      type: Boolean,
      default: () => true
    },
    isDisabled: {
      type: Boolean,
      default: () => true
    },
    showEmojis: {
      type: Boolean,
      default: () => false
    }
  },
  computed: {
    scoreFloatingPoint(): string {
      return parseFloat(this.score as string).toFixed(1);
    },
    starFillPercentage(): string {
      // const percent = (Number(this.score) / this.numberOfStars) * 100;
      const percent = 0;
      return `${percent}%`;
    },
    flipHorizontally(): string {
      // const score = this.score > 0 ? this.score - 1 : this.score;
      const score = 0;
      return !score ? "scaleX(-1)" : "none";
    },
    starDisabledClasses(): string {
      return this.isDisabled ? "pointer-events-none" : "cursor-pointer";
    },
    starLargeSizeClasses(): string {
      return this.size === "lg" ? "text-2xl" : "";
    },
    dynamicWidthStyle(): string {
      return this.size === "sm" ? "width: 87px" : "width: 130px";
    },
    dynamicHeightClasses(): string {
      return this.size === "sm" ? "h-6" : "h-8";
    },
    hasScore(): boolean {
      return this.score !== null && this.score !== 0;
    }
  },
  methods: {
    setRating(rating: number): void {
      this.selectedIndex = rating;
      this.$emit("set-rating", rating + 1);
    },
    reverseOrder(n: number) {
      return [...Array(n).keys()].slice().reverse();
    }
  }
});
</script>
<style lang="scss">
.star-icon-empty,
.star-icon-full {
  font-family: -apple-system, BlinkMacSystemFont, serif;
}

.star-icon-empty {
  &:hover,
  &.selected {
    @apply text-yellow-800;
    // applies styles to all elements with the star-icon class
    // below the hovered element with the same class
    ~ .star-icon-empty {
      @apply text-yellow-800;
    }
  }
}

#star-empty-0 {
  &:hover,
  &.selected {
    ~ .emoji-wrapper {
      > .emoji {
        transform: translateY(-75px);
      }
    }
  }
}

#star-empty-1 {
  &:hover,
  &.selected {
    ~ .emoji-wrapper {
      > .emoji {
        transform: translateY(-150px);
      }
    }
  }
}

#star-empty-2 {
  &:hover,
  &.selected {
    ~ .emoji-wrapper {
      > .emoji {
        transform: translateY(-225px);
      }
    }
  }
}

#star-empty-3 {
  &:hover,
  &.selected {
    ~ .emoji-wrapper {
      > .emoji {
        transform: translateY(-300px);
      }
    }
  }
}

#star-empty-4 {
  &:hover,
  &.selected {
    ~ .emoji-wrapper {
      > .emoji {
        transform: translateY(-375px);
      }
    }
  }
}

.emoji-wrapper {
  height: 75px;

  .emoji {
    transition: 0.3s;

    > img {
      margin: 15px 0;
      width: 45px;
      height: 45px;
    }
    .no-rating {
      filter: grayscale(100%);
    }
  }
}
</style>

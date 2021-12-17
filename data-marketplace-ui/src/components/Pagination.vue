<template>
  <ul class="pagination">
    <li>
      <button class="pagination-item" @click.prevent="move(page - 1)">
        <span
          class="chevron left"
          :class="{
            'border-gray-500': page === 0,
            'border-black': page > 0
          }"
        ></span>
      </button>
    </li>
    <li>
      <button class="pagination-item" :class="{ 'bg-blue-500 border-blue-500': page === 0 }" @click.prevent="move(0)">
        1
      </button>
    </li>
    <li class="pagination-item-ellipsis" v-if="isPastTheBeginning">
      <span class="ellipsis" />
      <span class="ellipsis" />
      <span class="ellipsis" />
    </li>
    <li v-for="index in pages" :key="index">
      <button
        class="pagination-item"
        :class="{ 'bg-blue-500 border-blue-500': index === page }"
        @click.prevent="move(index)"
      >
        {{ index + 1 }}
      </button>
    </li>
    <li class="pagination-item-ellipsis" v-if="!isAboutToEnd">
      <span class="ellipsis" />
      <span class="ellipsis" />
      <span class="ellipsis" />
    </li>
    <li v-if="numberOfPages > 1">
      <button
        class="pagination-item"
        :class="{ 'bg-blue-500 border-blue-500': page === numberOfPages - 1 }"
        @click.prevent="move(numberOfPages - 1)"
      >
        {{ numberOfPages }}
      </button>
    </li>
    <li>
      <button class="pagination-item" @click.prevent="move(page + 1)">
        <span
          class="chevron right"
          :class="{
            'border-gray-500': page === numberOfPages - 1,
            'border-black': page !== numberOfPages - 1
          }"
        ></span>
      </button>
    </li>
  </ul>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Pagination",
  model: {
    prop: "page",
    event: "move"
  },
  props: {
    numberOfPageLinks: {
      type: Number,
      required: false,
      default: 5
    },
    numberOfPages: {
      type: Number,
      required: true
    },
    page: {
      type: Number,
      required: false,
      default: 0
    }
  },
  computed: {
    isPastTheBeginning(): boolean {
      const links = this.numberOfPageLinksLocal;
      const pages = this.numberOfPages;
      const page = this.page;
      const offset = Math.floor(links / 2);
      return pages < links ? false : page + offset >= links;
    },
    isAboutToEnd(): boolean {
      const links = this.numberOfPageLinksLocal;
      const pages = this.numberOfPages;
      const page = this.page;
      const offset = Math.floor(links / 2);
      return pages < links ? true : page + offset >= pages - 2;
    },
    pages(): Array<number> {
      const pages = this.numberOfPages;
      const links = this.numberOfPageLinksLocal;
      const page = this.page;
      const offset = Math.floor(links / 2);
      let begin = 1;
      let end = links - 2;
      const isPastTheBeginning = this.isPastTheBeginning;
      const isAboutToEnd = this.isAboutToEnd;
      if (pages <= links) {
        end = pages - 2;
      } else {
        if (isPastTheBeginning && !isAboutToEnd) {
          begin = page - offset + 1;
          end = page + offset - 1;
        } else if (isAboutToEnd) {
          begin = pages - links;
          end = pages - 2;
        }
      }
      const pageList = [];
      for (let i = begin; i <= end; i++) {
        pageList.push(i);
      }
      return pageList;
    }
  },
  data() {
    return {
      pageLocal: this.page,
      numberOfPageLinksLocal: this.numberOfPageLinks < 3 ? 3 : this.numberOfPageLinks
    };
  },
  methods: {
    move(page: number) {
      if (page >= 0 && page < this.numberOfPages) {
        this.$emit("move", page);
        window.scrollTo(0, 0);
      }
    }
  }
});
</script>

<style lang="scss" scoped>
.pagination {
  @apply flex flex-row justify-center mt-10 mx-auto w-full;
}
.pagination-item {
  @apply flex justify-center items-center mx-2 w-10 h-10 border rounded-lg;
}
.pagination-item-ellipsis {
  @apply flex flex-row justify-center items-center;
}
.ellipsis {
  @apply m-1 h-1 w-1 rounded-full bg-black;
}
.chevron {
  border-style: solid;
  border-width: 0 0.1875rem 0.1875rem 0;
  display: inline-block;
  padding: 0.25rem;

  &.right {
    transform: rotate(-45deg);
    margin-left: -0.25rem;
  }

  &.left {
    transform: rotate(135deg);
    margin-right: -0.25rem;
  }

  &.up {
    transform: rotate(-135deg);
    margin-bottom: -0.25rem;
  }

  &.down {
    transform: rotate(45deg);
    margin-top: -0.25rem;
  }
}
</style>

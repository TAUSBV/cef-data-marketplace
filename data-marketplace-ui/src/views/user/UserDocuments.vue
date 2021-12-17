<template>
  <section>
    <div v-if="!isFetchingData && documents.totalElements > 0">
      <div class="mb-4">
        <span class="mr-4">
          <FormInputSearch placeholder="Search" v-model="keywords" @submit="page(0)" />
        </span>
        <span class="text-sm">
          Viewing {{ pageIndex }} - {{ pageOffset }} of {{ documents.totalElements }} results
        </span>
      </div>
      <table class="w-full table-fixed bg-gray-100 px-1">
        <tbody>
          <tr v-for="document in documents.content" v-bind:key="document.uid">
            <td
              class="p-4 border rounded hover:bg-gray-200 cursor-pointer"
              @click.prevent="setDocumentSelected(document)"
            >
              <img src="/assets/img/file-outline.svg" alt="File" class="inline mr-3" />
              <span class="text-blue-700 mr-2">
                {{ document.name }}
              </span>
              <span class="text-xs text-gray-800 mr-2">
                {{ document.size | fileSize }}
              </span>
              <Tag
                :label="document.status.toLowerCase()"
                variant="compact"
                :color="
                  document.status === 'NEW'
                    ? 'bg-orange-900 text-orange-900 bg-opacity-25'
                    : 'bg-green-900 text-green-900 bg-opacity-25'
                "
                class="inline float-none py-1 px-2 text-sm capitalize"
              />
              <span class="text-xs text-gray-800 float-right">
                {{ document.updatedAt | dateTime }}
              </span>
            </td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <td>
              <Pagination v-model="pageNumber" :number-of-pages="documents.totalPages" />
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
    <!-- User has not documents -->
    <div
      v-else-if="!isFetchingData"
      class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27"
    >
      You haven't published any document yet. Click
      <router-link to="/publications" class="text-blue-700">here</router-link>
      and start publishing your translation memories.
    </div>
    <!-- Loading placeholder -->
    <div v-else class="col-span-1 lg:col-span-3">
      <UserDocumentsLoading />
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import FormInputSearch from "@/components/FormInputSearch.vue";
import Pagination from "@/components/Pagination.vue";
import UserDocumentsLoading from "@/components/user/UserDocumentsLoading.vue";
import Tag from "@/components/Tag.vue";
import { Page, UserDocument } from "@/models/models";

export default Vue.extend({
  name: "UserDocuments",
  data() {
    return {
      documents: {} as Page<UserDocument>,
      pageNumber: 0,
      keywords: "",
      isFetchingData: true,
      DOCUMENT_STATUS_NEW: "NEW"
    };
  },
  mounted() {
    this.$store
      .dispatch("user/fetchDocuments", {})
      .then((r: Page<UserDocument>) => {
        this.documents = r;
      })
      .finally(() => {
        this.isFetchingData = false;
      });
  },
  methods: {
    search() {
      this.$store
        .dispatch("user/fetchDocuments", {
          page: this.pageNumber,
          keywords: this.keywords
        })
        .then((r: Page<UserDocument>) => {
          this.$set(this, "documents", r);
        });
    },
    page(page: number) {
      this.pageNumber = page;
      this.search();
    },
    setDocumentSelected(document: UserDocument) {
      if (document.status === this.DOCUMENT_STATUS_NEW) {
        this.$router.push({
          name: "DocumentReview",
          params: {
            publicationId: document.publicationUid
          }
        });
      } else {
        // TODO share document
        this.$router.push({
          path: `/users/documents/${document.uid}`
        });
      }
    }
  },
  computed: {
    pageIndex(): number {
      const table = this.documents;
      return table.number * table.size + 1;
    },
    pageOffset(): number {
      const table = this.documents;
      return table.number * table.size + table.numberOfElements;
    }
  },
  watch: {
    pageNumber(newValue: number) {
      this.page(newValue);
    }
  },
  components: {
    FormInputSearch,
    Pagination,
    Tag,
    UserDocumentsLoading
  }
});
</script>
<style lang="scss"></style>

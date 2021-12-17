<template>
  <div class="px-4 py-6 rounded text-center">
    <p class="text-lg tracking-0.3 font-normal xs:text-base xs:leading-relaxed xs:tracking-0.27 mb-5">
      The download will start in a moment. If it does not start click
      <a :href="`/users/documents/${this.$route.params.uid}/download`" target="_self">
        here.
      </a>
    </p>
  </div>
</template>
<script>
export default {
  name: "UserDocumentDownload"
};
</script>

<style scoped></style>

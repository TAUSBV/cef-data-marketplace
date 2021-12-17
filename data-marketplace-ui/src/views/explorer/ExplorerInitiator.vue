<template>
  <section class="grid grid-cols-3 gap-8 xs:grid-cols-1 mx-auto" :class="{ 'w-full md:w-4/5 sm:py-8': !isCompacted }">
    <DropDown
      id="source"
      placeholder="Source Language"
      :options="sources"
      trackByKey="displayName"
      :is-required="false"
      :value="sourceSelected[0] || []"
      @selected="val => selectSourceLanguage(val)"
    />
    <DropDown
      id="target"
      placeholder="Target Language"
      :options="targets"
      trackByKey="displayName"
      :is-required="false"
      :value="targetSelected[0] || []"
      @selected="val => selectTargetLanguage(val)"
    />
    <Button
      :class="isCompacted ? 'mx-auto' : 'mx-auto'"
      :isLoading="isLoading"
      :disabled="isDisabled"
      @click="exploreData()"
    >
      Explore
    </Button>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Button from "@/components/Button.vue";
import DropDown from "@/components/DropDown.vue";
import { Language, LanguageSourceTargetCount } from "@/models/models";
import { computed, onMounted, ref } from "@vue/composition-api";
import DataExplorerService from "@/services/DataExplorerService";
import explorer from "@/services/Explorer";

export default Vue.extend({
  name: "ExploreInitiator",
  props: {
    isCompacted: {
      type: Boolean,
      required: false,
      default: false
    },
    s: {
      type: String,
      required: false
    },
    t: {
      type: String,
      required: false
    }
  },
  setup(props, context) {
    const { setSourceLanguage, setTargetLanguage, explore, isLoading, clearResult, resetFilters } = explorer();
    const state = ref({
      sourceLanguages: [],
      targetLanguages: [],
      languages: [] as LanguageSourceTargetCount[],
      sourceSelected: [] as Language[],
      targetSelected: [] as Language[]
    });

    function selectSourceLanguage(selectedValue: Language) {
      state.value.sourceSelected.splice(0);
      state.value.sourceSelected.push(selectedValue);
      setSourceLanguage(selectedValue);
      // Clear target language
      state.value.targetSelected.splice(0);
      // Clear results
      clearResult();
    }

    function selectTargetLanguage(selectedValue: Language) {
      state.value.targetSelected.splice(0);
      state.value.targetSelected.push(selectedValue);
      setTargetLanguage(selectedValue);
      // Clear results
      clearResult();
    }

    function exploreData() {
      if (props.isCompacted) {
        context.root.$router.push({
          name: "DataExploration",
          query: {
            s: state.value.sourceSelected[0].id.toString(),
            t: state.value.targetSelected[0].id.toString()
          }
        });
      }
      resetFilters();
      explore();
    }

    const sources = computed(() => state.value.languages.map(lg => lg.source));
    const targets = computed(() => {
      return state.value.sourceSelected.length > 0
        ? state.value.languages
            .find(lg => lg.source.id === state.value.sourceSelected[0].id)
            .targets.map(t => t.language)
        : [];
    });
    const isDisabled = computed(
      () => state.value.sourceSelected.length === 0 || state.value.targetSelected.length === 0
    );

    const loadLanguages = async () => {
      state.value.languages = await DataExplorerService.languages().then(r => r.data);

      // Preselect source and target language if those are part of the url
      if (props.s && props.t) {
        const sourcePreselected: Language = sources.value.find(source => source.id.toString() === props.s);
        selectSourceLanguage(sourcePreselected);
        const targetPreselected: Language = targets.value.find(target => target.id.toString() === props.t);
        selectTargetLanguage(targetPreselected);
        explore();
      }
    };

    onMounted(loadLanguages);

    return {
      sourceLanguages: state.value.sourceLanguages,
      targetLanguages: state.value.targetLanguages,
      sources: sources,
      targets: targets,
      selectSourceLanguage: selectSourceLanguage,
      selectTargetLanguage: selectTargetLanguage,
      sourceSelected: state.value.sourceSelected,
      targetSelected: state.value.targetSelected,
      isDisabled: isDisabled,
      exploreData: exploreData,
      isLoading: isLoading
    };
  },
  components: {
    Button,
    DropDown
  }
});
</script>

<style lang="scss" scoped></style>

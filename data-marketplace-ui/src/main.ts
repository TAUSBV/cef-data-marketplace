import "./installCompositionApi";
import Vue from "vue";
import VueGtag from "vue-gtag";

import Vuelidate from "vuelidate";
import "@/styles/index.scss";
import "nprogress/nprogress.css";
import "@stripe/stripe-js";

import router from "./router";
import store from "./store";
import App from "./App.vue";
import {
  FileSizeFilter,
  FormatCurrencyFilter,
  FormatIntegerFilter,
  FormatDecimalFilter,
  FormatCurrency2DecimalsFilter,
  FormatNumberAbbrFilter,
  FormatNameInitials,
  TruncateFilter,
  CleanStringFilter,
  DateFilter,
  MediumDateFilter,
  DateTimeFilter, FormatDecimal2FractionDigitsFilter
} from "./filters";

Vue.config.productionTip = false;

Vue.use(Vuelidate);

Vue.use(
  VueGtag,
  {
    appName: "taus-data-marketplace",
    pageTrackerScreenviewEnabled: true,
    pageTrackerExcludedRotues: ["signIn", "/user", "/user/profile", "/user/documents"],
    config: { id: import.meta.env.VITE_APP_GOOGLE_ANALYTICS_TRACKING_CODE }
}, router);

// Filters
Vue.filter("cleanString", CleanStringFilter);
Vue.filter("fileSize", FileSizeFilter);
Vue.filter("currency", FormatCurrencyFilter);
Vue.filter("currency2Decimals", FormatCurrency2DecimalsFilter);
Vue.filter("integer", FormatIntegerFilter);
Vue.filter("decimal", FormatDecimalFilter);
Vue.filter("decimal2FractionDigits", FormatDecimal2FractionDigitsFilter);
Vue.filter("numberAbbr", FormatNumberAbbrFilter);
Vue.filter("initials", FormatNameInitials);
Vue.filter("truncate", TruncateFilter);
Vue.filter("date", DateFilter);
Vue.filter("mediumDate", MediumDateFilter);
Vue.filter("dateTime", DateTimeFilter);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

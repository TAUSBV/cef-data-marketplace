import Vue from "vue";
import Vuex from "vuex";
import * as baseline from "@/store/modules/baseline";
import * as document from "@/store/modules/document";
import * as notification from "@/store/modules/notification";
import * as explorer from "@/store/modules/explorer";
import * as priceindex from "@/store/modules/priceindex";
import * as user from "@/store/modules/user";
import * as seller from "@/store/modules/seller";
import * as session from "@/store/modules/session";
import * as shoppingCart from "@/store/modules/shopping-cart";
import * as checkout from "@/store/modules/checkout";
import * as purchase from "@/store/modules/purchase";
import * as hubspot from "@/store/modules/hubspot";
import * as matchingDataSearch from "@/store/modules/matching-data-search";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    baseline,
    document,
    notification,
    explorer,
    priceindex,
    user,
    seller,
    session,
    shoppingCart,
    checkout,
    purchase,
    hubspot,
    matchingDataSearch
  }
});

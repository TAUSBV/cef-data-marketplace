import { ActionContext, GetterTree } from "vuex";
import BaselineService from "@/services/BaselineService";
import { ContentType, Country, Domain } from "@/models/models";

export const namespaced = true;

type BaselineData = {
  domains: Array<Domain>;
  contentTypes: Array<ContentType>;
  countries: Array<Country>;
};

export const state: BaselineData = {
  domains: [],
  contentTypes: [],
  countries: []
};

export const getters: GetterTree<BaselineData, any> = {
  domains: state => state.domains,
  contentTypes: state => state.contentTypes,
  countries: state => state.countries,
  isoCountries: state => state.countries.filter(c => c.code !== null)
};

export const mutations = {
  setCountries(state: BaselineData, countries: Array<Country>) {
    state.countries = countries;
  },
  setContentTypes(state: BaselineData, contentTypes: Array<ContentType>) {
    state.contentTypes = contentTypes;
  },
  setDomains(state: BaselineData, domains: Array<Domain>) {
    state.domains = domains;
  }
};

export const actions = {
  getCountries(context: ActionContext<BaselineData, any>) {
    BaselineService.getCountries()
      .then(r => context.commit("setCountries", r.data))
      .catch(() => context.commit("setCountries", []));
  },
  getDomains(context: ActionContext<BaselineData, any>) {
    return BaselineService.getDomains()
      .then(r => context.commit("setDomains", r.data))
      .catch(() => context.commit("setDomains", []));
  },
  getContentTypes(context: ActionContext<BaselineData, any>) {
    return BaselineService.getContentTypes()
      .then(r => context.commit("setContentTypes", r.data))
      .catch(() => context.commit("setContentTypes", []));
  }
};

import { Dispatch, GetterTree } from "vuex";
import { SignInForm } from "@/models/models";
import SessionService from "@/services/SessionService";

export const namespaced = true;

type AuthInfo = {
  uid: string;
  enforceTerms: boolean;
};

type Session = {
  auth: AuthInfo;
  isAuthenticated: boolean;
};

export const state: Session = {
  auth: null as AuthInfo,
  isAuthenticated: false
};

export const getters: GetterTree<Session, any> = {
  isNotAuthenticated: state => !!state.isAuthenticated,
  isAuthenticated: state => state.isAuthenticated
};

export const mutations = {
  setAuthentication(auth: AuthInfo) {
    state.auth = auth;
    state.isAuthenticated = auth != null;
  }
};

export const actions = {
  signIn({ dispatch }: { dispatch: Dispatch }, form: SignInForm) {
    return SessionService.signIn(form)
      .then(response => {
        mutations.setAuthentication(response.data);
        return response.data;
      })
      .catch(error => {
        if (error.response.status === 404) {
          mutations.setAuthentication(null);
        } else {
          throw error;
        }
      });
  },
  refresh() {
    return SessionService.refresh()
      .then(response => {
        mutations.setAuthentication(response.data);
        return response.data;
      })
      .catch(() => {
        mutations.setAuthentication(null);
      });
  },
  destroy() {
    return SessionService.destroy()
      .then(() => {
        mutations.setAuthentication(null);
      })
      .catch(() => {
        mutations.setAuthentication(null);
      });
  }
};

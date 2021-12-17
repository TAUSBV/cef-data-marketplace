import { Notification } from "@/models/models";
import { Commit } from "vuex";

export const namespaced = true;

export const state = {
  notification: Notification
};

export const mutations = {
  PUSH(state: State, notification: Notification) {
    state.notification = notification;
  },
  DELETE(state: State) {
    state.notification = null;
  }
};

export const actions = {
  add({ commit }: { commit: Commit }, notification: Notification) {
    commit("PUSH", new Notification(notification.type, notification.message));
  },
  remove({ commit }: { commit: Commit }) {
    commit("DELETE");
  }
};

class State {
  notification?: Notification | null;
}

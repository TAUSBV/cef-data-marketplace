import { Dispatch, GetterTree } from "vuex";
import ShoppingCartService from "@/services/ShoppingCartService";
import { Cart } from "@/models/models";

export const namespaced = true;

type ShoppingCart = {
  shoppingCart: Cart;
};

export const state: ShoppingCart = {
  shoppingCart: {} as Cart
};

export const getters: GetterTree<ShoppingCart, any> = {
  shoppingCart: state => state.shoppingCart
};

export const mutations = {
  setShoppingCart(cart: Cart) {
    state.shoppingCart = cart;
  },
  clean() {
    state.shoppingCart = {} as Cart;
  }
};

export const actions = {
  list() {
    return ShoppingCartService.list()
      .then(response => {
        mutations.setShoppingCart(response.data);
        return response.data;
      })
      .catch(error => {
        if (error.response.status === 404) {
          mutations.clean();
        } else {
          throw error;
        }
      });
  },
  update({ dispatch }: { dispatch: Dispatch }, updatedItemForm: FormData) {
    return ShoppingCartService.update(updatedItemForm).then(response => {
      mutations.setShoppingCart(response.data);
      return response.data;
    });
  },
  delete({ dispatch }: { dispatch: Dispatch }, product: FormData) {
    return ShoppingCartService.delete(product).then(response => {
      mutations.setShoppingCart(response.data);
      return response.data;
    });
  },
  addSellerDocumentToCart({ dispatch }: { dispatch: Dispatch }, product: FormData) {
    return ShoppingCartService.addSellerDocumentToCart(product).then(response => {
      mutations.setShoppingCart(response.data);
      return response.data;
    });
  },
  addVirtualDocumentToCart({ dispatch }: { dispatch: Dispatch }, product: FormData) {
    return ShoppingCartService.addVirtualDocumentToCart(product).then(response => {
      mutations.setShoppingCart(response.data);
      return response.data;
    });
  },
  addMatchingDocumentToCart({ dispatch }: { dispatch: Dispatch }, product: FormData) {
    return ShoppingCartService.addMatchingDocumentToCart(product).then(response => {
      mutations.setShoppingCart(response.data);
      return response.data;
    });
  }
};

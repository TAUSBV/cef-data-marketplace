// Core
import Vue from "vue";
import VueRouter, { Route, RouteConfig } from "vue-router";
import { RawLocation } from "vue-router/types/router";
import store from "@/store";
// External Modules
import NProgress from "nprogress";
// Views
import PriceIndexTable from "@/views/PriceIndexTable.vue";
import Explore from "@/views/explorer/Explorer.vue";
import Playground from "@/views/Playground.vue";
import LandingPage from "@/views/LandingPage.vue";
import About from "@/views/About.vue";
import HowItWorks from "@/views/HowItWorks.vue";
import AuthenticationPage from "@/views/AuthenticationPage.vue";
import Publication from "@/views/publication/Publication.vue";
import TermsOfUse from "@/views/TermsOfUse.vue";
import Sellers from "@/views/seller/Sellers.vue";
import Seller from "@/views/seller/Seller.vue";
import SellerDocument from "@/views/seller/SellerDocument.vue";
import Checkout from "@/views/Checkout.vue";
import ShoppingCart from "@/views/ShoppingCart.vue";
import Users from "@/views/user/Users.vue";
import UserProfile from "@/views/user/UserProfile.vue";
import UserDocuments from "@/views/user/UserDocuments.vue";
import UserDocument from "@/views/user/UserDocument.vue";
import UserDocumentDownload from "@/views/user/UserDocumentDownload.vue";
import Purchase from "@/views/purchase/Purchase.vue";
import Faq from "@/views/Faq.vue";
import UserPaymentDetails from "@/views/user/UserPaymentDetails.vue";
import PaymentTerms from "@/views/PaymentTerms.vue";
import UserPaymentList from "@/views/user/UserPaymentList.vue";
import Search from "@/views/search/Search.vue";
import ContactUs from "@/views/ContactUs.vue";
import { NotificationType } from "@/models/models";
import PurchaseDownload from "@/views/purchase/PurchaseDownload.vue";

Vue.use(VueRouter);

function checkAuth(routeTo: Route, routeFrom: Route, next: (to?: RawLocation) => void) {
  store
    .dispatch("user/fetch")
    .then(() => {
      if (store.getters["session/isAuthenticated"]) {
        next();
      } else {
        next("/sign-in");
      }
    })
    .catch(() => next("/sign-in"));
}

function checkShoppingCart(routeTo: Route, routeFrom: Route, next: (to?: RawLocation) => void) {
  let promise;
  if (routeTo.query.p) {
    promise = store.dispatch("purchase/get", routeTo.query.p);
  } else {
    promise = store.dispatch("shoppingCart/list");
  }
  promise
    .then(r => {
      routeTo.params.cart = r;
      next();
    })
    .catch(() => {
      next("/shopping-cart");
    });
}

const routes: Array<RouteConfig> = [
  {
    path: "/explore",
    name: "DataExploration",
    component: Explore
  },
  {
    path: "/search",
    name: "Search",
    component: Search
  },
  {
    path: "/price-index",
    name: "PriceIndexTable",
    component: PriceIndexTable,
    props: true,
    beforeEnter(routeTo, routeFrom, next) {
      const actions = [store.dispatch("priceindex/load", {})];
      Promise.all(actions)
        .then(r => {
          routeTo.params.table = r[0];
          next();
        })
        .catch(error => {
          store.dispatch(
            "notification/add",
            {
              type: NotificationType.DANGER,
              message: error.message
            },
            {
              root: true
            }
          );
          next();
        });
    }
  },
  {
    path: "/publications/:publicationId",
    name: "DocumentReview",
    props: true,
    beforeEnter(routeTo, routeFrom, next) {
      store
        .dispatch("document/fetchPublications", routeTo.params.publicationId)
        .then(response => {
          routeTo.params.publication = response;
          next();
        })
        .catch(error => {
          store.dispatch(
            "notification/add",
            {
              type: NotificationType.DANGER,
              message: error.message
            },
            {
              root: true
            }
          );
          next();
        });
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import("../views/publication/DocumentReview.vue")
  },
  {
    path: "/publications",
    name: "Publications",
    component: Publication
  },
  {
    path: "/pg",
    name: "Playground",
    component: Playground,
    props: true
  },
  {
    path: "/",
    name: "Home",
    component: LandingPage
  },
  {
    path: "/about",
    name: "About",
    component: About
  },
  {
    path: "/shopping-cart",
    name: "ShoppingCart",
    component: ShoppingCart
  },
  {
    path: "/how-it-works",
    name: "HowItWorks",
    component: HowItWorks
  },
  {
    path: "/sign-in",
    name: "Sign In",
    component: AuthenticationPage
  },
  {
    path: "/tou",
    name: "Terms Of Use",
    component: TermsOfUse
  },
  {
    path: "/faq",
    name: "FAQ",
    component: Faq
  },
  {
    path: "/sellers",
    name: "Sellers",
    component: Sellers
  },
  {
    path: "/sellers/:slugId",
    name: "Seller",
    component: Seller
  },
  {
    path: "/sellers/:slugId/documents/:uid",
    name: "SellerDocument",
    component: SellerDocument
  },
  {
    path: "/checkout",
    name: "Checkout",
    component: Checkout,
    props: true,
    beforeEnter: checkShoppingCart
  },
  {
    path: "/users",
    name: "Users",
    component: Users,
    redirect: { name: "UserProfile" },
    beforeEnter: checkAuth,
    children: [
      {
        path: "profile",
        name: "UserProfile",
        component: UserProfile
      },
      {
        path: "documents",
        name: "UserDocuments",
        component: UserDocuments
      },
      {
        path: "documents/:uid",
        name: "Document",
        component: UserDocument
      },
      {
        path: "documents/:uid/download",
        name: "UserDocumentsDownload",
        beforeEnter(routeTo, routeFrom, next) {
          location.href = `${import.meta.env.VITE_APP_API_BASE_URL}/users/documents/${routeTo.params.uid}/download`;
          next();
        },
        component: UserDocumentDownload
      },
      {
        path: "payment-history",
        name: "UserPaymentList",
        component: UserPaymentList
      },
      {
        path: "payment-details",
        name: "UserPaymentDetails",
        component: UserPaymentDetails
      }
    ]
  },
  {
    path: "/purchases/:uid",
    name: "Purchase",
    component: Purchase
  },
  {
    path: "/purchases/downloads/:uid",
    name: "Purchase Downloads",
    component: PurchaseDownload
  },
  {
    path: "/payment-terms",
    name: "PaymentTerms",
    component: PaymentTerms
  },
  {
    path: "/contact-us",
    name: "ContactUs",
    component: ContactUs
  },
  {
    path: "*",
    name: "NotFound",
    redirect: "/"
  }
];

const router = new VueRouter({
  mode: "history",
  base: import.meta.env.BASE_URL,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition ? savedPosition : { x: 0, y: 0 };
  },
  routes
});

router.beforeEach((routeTo, routeForm, next) => {
  NProgress.start();
  store.dispatch("notification/remove");
  next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;

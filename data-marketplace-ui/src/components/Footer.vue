<template>
  <footer class="bg-dark-grey-blue text-gray-100 h-footer xs:h-48 w-full">
    <section class="relative -top-8 left-0">
      <div
        class="w-1/3 xs:w-full h-20 xs:h-11 xs:w-full grid grid-cols-1 bg-blue-700 border-transparent text-center border align-middle rounded"
      >
        <div class="w-3/4 ml-auto xs:w-full flex justify-evenly items-center">
          <a href="https://www.facebook.com/TAUSDataMarketplace/"
            ><img
              src="/assets/img/icons-social-media-facebook.svg"
              class="inline cursor-pointer xl:ml-4 xs:w-5 md:w-6 lg:w-8"
          /></a>
          <a href="https://www.linkedin.com/showcase/taus-data-marketplace/"
            ><img src="/assets/img/icons-social-media-linkedin.svg" class="inline cursor-pointer xs:w-5 md:w-6 lg:w-8"
          /></a>
          <a href=" https://twitter.com/CefData"
            ><img
              src="/assets/img/icons-social-media-twitter.svg"
              class="inline cursor-pointer xl:mr-4 xs:w-5 md:w-6 lg:w-8"
          /></a>
        </div>
      </div>
    </section>
    <section>
      <div class="md:flex w-3/4 lg:p-8 h-full mx-auto">
        <div class="flex-1">
          <div class="flex lg:w-3/4 lg:py-8 lg:px-8 h-full mx-auto">
            <div class="flex-auto lg:flex-1">
              <a
                class="xs:text-base sm:text-lg tracking-0.3 block mb-6"
                href="https://www.taus.net/about/mission"
                target="_blank"
              >
                About TAUS
              </a>
              <a
                class="xs:text-base sm:text-lg tracking-0.3 block"
                href="https://www.taus.net/membership/taus-partner-foundation-board#overview"
                target="_blank"
              >
                Partner Board
              </a>
            </div>
            <div class="flex-auto lg:flex-1">
              <router-link to="/tou" class="xs:hidden sm:text-lg tracking-0.3 block mb-6 no-underline">
                Terms Of Use
              </router-link>
              <router-link to="/tou" class="sm:hidden text-base tracking-0.3 block mb-6 no-underline">
                ToU
              </router-link>
              <router-link to="/payment-terms" class="xs:text-base sm:text-lg tracking-0.3 block mb-6 no-underline">
                Payment Terms
              </router-link>
            </div>
            <div class="flex-auto lg:flex-1">
              <router-link to="/faq" class="xs:text-base sm:text-lg tracking-0.3 block mb-6 no-underline">
                FAQ
              </router-link>
              <a
                class="xs:text-base sm:text-lg tracking-0.3 block mb-6"
                href="https://www.taus.net/about/contact"
                target="_blank"
              >
                Contact
              </a>
            </div>
          </div>
        </div>
        <div class="flex-3 my-auto xs:hidden">
          <div class="ts-contact bg-gray-100 border border-solid border-gray-100 rounded p-6">
            <div class="flex flex-row items-center mb-3">
              <img src="/assets/img/location.svg" alt="location" class="w-6 h-6 my-auto mr-3" />
              <div class="text-xs text-gray-900">
                Danzigerkade 65 A 1013 AP, Amsterdam
              </div>
            </div>
            <div class="flex flex-row items-center mb-3">
              <img src="/assets/img/email.svg" alt="email" class="w-6 h-6 my-auto mr-3" />
              <a
                class="text-xs text-gray-900"
                href="mailto:datamarketplace@taus.net?subject=Data Marketplace inquiring."
              >
                datamarketplace@taus.net
              </a>
            </div>
          </div>
        </div>
      </div>
    </section>
  </footer>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Footer"
});
</script>

<style lang="scss">
.ts-contact {
  box-shadow: 0 20px 200px 10px rgba(0, 0, 0, 0.07);
  position: relative;

  &::before {
    position: absolute;
    content: "";
    bottom: -65px;
    left: -207px;
    width: 19rem;
    height: 19rem;
    background-image: url(/assets/img/map.png);
    background-size: 19rem 19rem;
  }

  &:after {
    position: absolute;
    top: 65px;
    left: -10px;
    content: "";
    width: 0;
    height: 0;
    border-right: solid 10px;
    border-bottom: solid 10px transparent;
    border-top: solid 10px transparent;
    @apply text-gray-100;
  }
}

@media only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (min-device-pixel-ratio: 2) {
  .ts-contact::before {
    background-image: url(/assets/img/map@2x.png);
  }
}

@media only screen and (-webkit-min-device-pixel-ratio: 3), only screen and (min-device-pixel-ratio: 3) {
  .ts-contact::before {
    background-image: url(/assets/img/map@3x.png);
  }
}

@media (max-width: 1024px) {
  .ts-contact::before {
    background-image: none;
  }
}
</style>

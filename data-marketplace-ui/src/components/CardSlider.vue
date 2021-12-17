<template>
  <swiper class="swiper" id="test_swiper" ref="mySwiper" :options="swiperOption">
    <swiper-slide>
      <CardStory
        img="Larry_Cady.jpg"
        title="Larry Cady"
        role="Chilin (HK) Ltd."
        text="See how a niche data provider makes use of the promotional opportunity Data Marketplace creates to reach large data buyers."
        linkref="https://blog.taus.net/to-sell-more-data-effortlessly "
        sellerprofile="https://datamarketplace.taus.net/sellers/Chilin(HK)___Ltd.___9H__xOprTVCqCET1P1sclw"
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="Dr_Khateeb.jpg"
        title="Muhammad Alkhateeb"
        role="Surgeon/Translator"
        text="The story of a doctor who created a medical dataset that brings 90% BLEU score improvement for EN-AR language pair."
        linkref="https://blog.taus.net/from-studying-medicine-to-selling-mt-training-datasets"
        sellerprofile="https://datamarketplace.taus.net/sellers/Muhammad___Alkhateeb___xNF_U-VLTJ-MzOba91a7bA"
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="user_margarita.jpg"
        title="Margarita Menyailova"
        role="EGO Translating Company"
        text="Find out how EGO Translating Company became a data seller while looking for data to buy."
        linkref="https://blog.taus.net/a-win-win-situation"
        sellerprofile="https://datamarketplace.taus.net/sellers/EGO___TECH___YViuLYmIRty-FOs0FBndMg"
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="user_adesina.jpg"
        title="Adéṣinà Ayẹni"
        role="Linguist"
        text="See how Adéṣínà Ayẹni contributes to the digital survival of Yorùbá while monetizing his lifelong ambition."
        linkref="https://blog.taus.net/we-have-stories-to-tell"
        sellerprofile="https://datamarketplace.taus.net/sellers/Ad%C3%A9%E1%B9%A3%C3%ADn%C3%A0___Ay%E1%BA%B9ni___K74bF2PdS2KEa2HCBzkxKw"
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="user_gilin.jpg"
        title="Mikhail Gilin"
        role="TransLink"
        linkref="https://blog.taus.net/head-start-advantage-with-early-adoption"
        sellerprofile="https://datamarketplace.taus.net/sellers/Mikhail___Morozov___DcUg_CyGQ2O1JxcUAn1D7Q"
        text="Read how TransLink makes the most of an early advantage and addresses data ownership concerns."
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="user_abdulselam.jpeg"
        title="Abdülselam Yıldırım"
        role="Linguist"
        text="Learn how Abdülselam Yıldırım mobilizes his native community to generate and monetize language data."
        linkref="https://blog.taus.net/ethical-responsibility-towards-underrepresented-languages"
        sellerprofile="https://datamarketplace.taus.net/sellers/TAUS___Data___H2vMmaMESZ2Yq_f3uJOmYA/documents/sQeaWq7kSoycDlvzjgkd9Q"
      />
    </swiper-slide>
    <swiper-slide>
      <CardStory
        img="ilan-kernerman.png"
        title="Ilan Kernerman"
        role="CEO, Lexicala"
        text="Lexicala has emerged from the publishing world, as a provider of quality lexicographic content for leading dictionary publishers worldwide."
        linkref="https://blog.taus.net/breaking-the-publishing-ground-from-dictionaries-to-linguistic-data"
        sellerprofile="https://datamarketplace.taus.net/sellers/Lexicala___Data___hbqMZVsETpiCTFzx3itK9A"
      />
    </swiper-slide>

    <div class="swiper-pagination invisible lg:visible" slot="pagination"></div>
  </swiper>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";
import CardStory from "@/components/CardStory.vue";
import Vue from "vue";
export default Vue.extend({
  name: "CardSlider",
  components: {
    Swiper,
    SwiperSlide,
    CardStory
  },
  data() {
    return {
      swiperOption: {
        slidesPerView: 3,
        centeredSlides: true,
        spaceBetween: 0,
        direction: "horizontal",
        freeMode: true,
        mousewheel: false,
        slideToClickedSlide: true,
        initialSlide: 1,
        pagination: {
          el: ".swiper-pagination",
          clickable: true
        }
      }
    };
  },
  created() {
    window.addEventListener("resize", this.onResize);
    this.onResize();
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResize);
  },
  methods: {
    onResize() {
      if (window.innerWidth < 1024) {
        this.swiperOption.mousewheel = true;
        this.swiperOption.direction = "vertical";
        this.swiperOption.centeredSlides = false;
        this.swiperOption.slidesPerView = 2;
        this.swiperOption.spaceBetween = 25;
        this.swiperOption.initialSlide = 0;
      } else {
        this.swiperOption.mousewheel = false;
        this.swiperOption.direction = "horizontal";
      }
    }
  }
});
</script>

<style lang="scss">
.swiper {
  width: 75vw;
  height: 600px;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    background-color: white;
    margin-right: 0px !important;
  }

  .swiper-slide:hover {
    cursor: pointer;
  }

  .swiper-slide-active {
    display: flex;
    position: relative;
    justify-content: center;
    align-items: center;
    text-align: center;
    background-color: transparent;
    margin-right: 0px !important;
    -webkit-transform: scale(1.2);
    transform: scale(1.2);
    -webkit-transition: all 500ms ease;
    -moz-transition: all 500ms ease;
    -ms-transition: all 500ms ease;
    -o-transition: all 500ms ease;
    transition: all 500ms ease;
    z-index: 98;
    cursor: default;

    .card-story {
      background-color: white;
    }

    .card-story:hover {
      cursor: default;
    }
  }
}

@media (min-width: 426px) and (max-width: 1023px) {
  .swiper {
    height: 750px;
    .swiper-slide {
      height: 360px !important;
    }
    .swiper-slide-active {
      transform: scale(1);
    }
  }
}

@media (max-width: 426px) {
  .swiper {
    height: 900px;
    .swiper-slide {
      height: 420px !important;
    }
    .swiper-slide-active {
      transform: scale(1);
    }
  }
}

html:root {
  --swiper-theme-color: #2da4df !important;
}

.swiper-pagination {
  margin-top: 20px;
}
.swiper-pagination-bullet {
  width: 1rem !important;
  height: 1rem !important;
  display: inline-block;
  border-radius: 100%;
  background: #2da4df !important;
  outline: none;
}
.swiper-container-horizontal > .swiper-pagination-bullets .swiper-pagination-bullet {
  margin: 0 6px;
}
</style>

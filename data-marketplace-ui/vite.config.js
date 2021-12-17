import { defineConfig } from "vite";
import { createVuePlugin } from "vite-plugin-vue2";
import viteImagemin from "vite-plugin-imagemin";
import { resolve } from "path";
import Icons from "vite-plugin-icons";
import html from "vite-plugin-html";

export default defineConfig({
  define: {
    "process.env": {}
  },
  plugins: [
    createVuePlugin(),
    Icons(),
    html({
      inject: {
        injectData: {
          title: "Data Marketplace - TAUS - The Language Data Network",
          description:
            "Monetize your language data and acquire data to improve your AI applications. Buy or sell language data easily on the Data Marketplace.",
          noscript:
            "We're sorry but Data Marketplace - TAUS - The Language Data Network doesn't work properly without JavaScript enabled. Please enable it to continue."
        }
      },
      minify: true
    }),
    viteImagemin({
      pngquant: {
        quality: [0.85, 0.95],
        speed: 4
      },
      optipng: {
        optimizationLevel: 7
      }
    })
  ],
  resolve: {
    alias: [
      {
        find: "@",
        replacement: resolve(__dirname, "src")
      }
    ]
  }
});

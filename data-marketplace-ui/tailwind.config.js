module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
    purgeLayersByDefault: true
  },
  purge: {
    content: ["./public/**/*.html", "./src/**/*.vue"],
    options: {
      whitelist: [
        /-(leave|enter|appear)(|-(to|from|active))$/,
        /^(?!(|.*?:)cursor-move).+-move$/,
        /^router-link(|-exact)-active$/
      ]
    }
  },
  theme: {
    extend: {
      screens: {
        xs: { max: "640px" },
        "3xl": { min: "1920px" },
        sclg: { max: "1151px" } // breakpoint for shopping cart page
      },
      colors: {
        watermelon: "#e65765",
        "dark-grey-blue": "#243746",
        "turquoise-blue": "#00b7c4",
        gray: {
          "900": "#1c1c28",
          "800": "#77777e",
          "700": "#28293d",
          "600": "#555770",
          "500": "#8f90a6",
          "400": "#c7c9d9",
          "300": "#e4e4eb",
          "200": "#f2f2f5",
          "100": "#ffffff"
        },
        blue: {
          "1000": "#243648",
          "900": "#006699",
          "800": "#0188cb",
          "700": "#2da4df",
          "600": "#65beea",
          "500": "#cfefff",
          "400": "#63B3ED",
          "300": "#cfefff",
          "200": "#e6f5ff",
          "100": "#EBF8FF"
        },
        yellow: {
          "1000": "#bc9801",
          "900": "#ffcc00",
          "800": "#e8ad02",
          "700": "#fded72",
          "600": "#fff6b1",
          "500": "#fffee6",
          "400": "#F6E05E",
          "300": "#fff1ba",
          "200": "#FEFCBF",
          "100": "#FFFFF0"
        },
        green: {
          "900": "#009933",
          "800": "#05af65",
          "700": "#30bd78",
          "600": "#4dcd8d",
          "500": "#e3fff1",
          "400": "#68D391",
          "300": "#9AE6B4",
          "200": "#C6F6D5",
          "100": "#F0FFF4"
        },
        red: {
          "900": "#cc3333",
          "800": "#f03535",
          "700": "#e65765",
          "600": "#fb7b7b",
          "500": "#ffe6e6",
          "400": "#FC8181",
          "300": "#FEB2B2",
          "200": "#FED7D7",
          "100": "#FFF5F5"
        },
        pink: {
          "900": "#cc3399",
          "800": "#ef3cb3",
          "700": "#f672ca",
          "600": "#ffa4e1",
          "500": "#fdd8f1",
          "400": "#F687B3",
          "300": "#FBB6CE",
          "200": "#FED7E2",
          "100": "#FFF5F7"
        },
        purple: {
          "700": "#6558ee",
          "300": "#e9e7fc",
          "900": "#204992"
        },
        orange: {
          "900": "#e3841b",
          "300": "#fbeddf"
        },
        turquoise: {
          "900": "#00b7c4"
        }
      },
      fontFamily: {
        sans: [
          "Roboto",
          "Helvetica Neue",
          "Arial",
          "Noto Sans",
          "sans-serif",
          "Apple Color Emoji",
          "Segoe UI Emoji",
          "Segoe UI Symbol",
          "Noto Color Emoji"
        ]
      },
      fontSize: {
        "34px": "34px",
        "26px": "26px",
        "18px": "18px",
        "0.95": "0.95rem",
        "1.625": "1.625rem",
        "2.125": "2.125rem"
      },
      boxShadow: {
        hero: "-2px 7px 20px 0 rgba(28, 28, 40, 0.08)",
        input: "1px 2px 9px 0 rgba(28, 28, 40, 0.06)",
        documentCard: "0 1px 5px 0 rgba(0, 0, 0, 0.1)"
      },
      height: {
        14: "3.5rem",
        "card-icon": "25.5rem",
        "card-story": "23.5rem",
        "landing-introduction": "43.5rem",
        "3.75": "3.75rem",
        "1.875": "1.875rem"
      },
      minHeight: {
        "5": "1.25rem",
        "6": "1.5rem",
        "20": "5rem",
        "1.875": "1.875rem",
        "3.75": "3.75rem"
      },
      maxHeight: {
        "0": "0",
        "1/4": "25%",
        "1/2": "50%",
        "3/4": "75%",
        full: "100%"
      },
      width: {
        292: "73rem",
        "card-icon": "18.5rem",
        "3.75": "3.75rem",
        "1.875": "1.875rem"
      },
      minWidth: {
        "5": "1.25rem",
        "6": "1.5rem",
        "20": "5rem",
        "1.875": "1.875rem",
        "3.75": "3.75rem",
        "22.75": "22.75rem"
      },
      borderRadius: {
        xxl: "3.5rem"
      },
      spacing: {
        footer: "23.5rem"
      },
      opacity: {
        "45": "0.45",
        "15": "0.15",
        "14": "0.14"
      },
      letterSpacing: {
        "5.0": "5px",
        "1.6": "1.6px",
        "1.06": "1.06px",
        "1.4": "1.4px",
        "0.76": "0.76px",
        "0.48": "0.48px",
        "0.44": "0.44px",
        "0.37": "0.37px",
        "0.3": "0.3px",
        "0.27": "0.27px",
        "0.23": "0.23px",
        "0.2": "0.2px",
        "0.7": "0.7px"
      },
      lineHeight: {
        "30": "30px",
        "49": "49px",
        "60": "60px",
        "1.375": "1.375rem",
        "1.75": "1.75rem",
        "1.875": "1.875rem",
        "2.25": "2.25rem"
      }
    }
  },
  variants: {
    backgroundColor: ["responsive", "first", "last", "even", "odd", "hover", "focus", "checked"],
    cursor: ["responsive", "disabled"],
    opacity: ["responsive", "hover", "focus", "disabled"],
    borderColor: ["checked"]
  },
  plugins: [require("@tailwindcss/typography")]
};

const locales = ["en-GB"];
const currency = new Intl.NumberFormat(locales, {
  style: "currency",
  currency: "EUR",
  maximumFractionDigits: 4
});
const currency2Decimals = new Intl.NumberFormat(locales, {
  style: "currency",
  currency: "EUR",
  maximumFractionDigits: 2
});
const integer = new Intl.NumberFormat(locales, {
  minimumFractionDigits: 0
});
const decimal = new Intl.NumberFormat(locales, {
  maximumFractionDigits: 4
});
const decimal2FractionDigits = new Intl.NumberFormat(locales, {
  maximumFractionDigits: 2
});
export function FileSizeFilter(size: number) {
  if (isNaN(size)) {
    size = 0;
  }
  if (size < 1024) {
    return `${size} Bytes`;
  }
  size /= 1024;
  if (size < 1024) {
    return `${size.toFixed(2)} Kb`;
  }
  size /= 1024;
  if (size < 1024) {
    return `${size.toFixed(2)} Mb`;
  }
  size /= 1024;
  if (size < 1024) {
    return `${size.toFixed(2)} Gb`;
  }
  size /= 1024;
  return `${size.toFixed(2)} Tb`;
}
export function FormatCurrencyFilter(value: number): string {
  return typeof value !== "number" ? value : currency.format(value);
}
export function FormatCurrency2DecimalsFilter(value: number): string {
  return typeof value !== "number" ? value : currency2Decimals.format(value);
}
export function FormatIntegerFilter(value: number): string {
  return typeof value !== "number" ? value : integer.format(value);
}
export function FormatDecimalFilter(value: number): string {
  return typeof value !== "number" ? value : decimal.format(value);
}
export function FormatDecimal2FractionDigitsFilter(value: number): string {
  return typeof value !== "number" ? value : decimal2FractionDigits.format(value);
}
export function FormatNumberAbbrFilter(number: number): string {
  const suffixes = ["k", "m", "b", "t"];

  if (number < 1000) {
    return number.toString();
  }

  const exp = Math.floor(Math.log(number) / Math.log(1000));

  return (number / Math.pow(1000, exp)).toFixed(2) + suffixes[exp - 1];
}
export function FormatNameInitials(items: string[]): string {
  return items[0] && items[1]
    ? (items[0].charAt(0) + items[1].charAt(0)).toUpperCase()
    : items[0]
    ? items[0].charAt(0)
    : "";
}
export function TruncateFilter(text: string, limit: number) {
  let truncated = text;
  const actualLimit = limit !== undefined ? limit : 30;
  if (truncated !== undefined) {
    truncated = text.length > actualLimit ? `${text.substring(0, actualLimit)}...` : text;
  }
  return truncated;
}
export function CleanStringFilter(value: string) {
  let cleanString = "";
  if (value) {
    cleanString = value.toString().replace(/_/g, " ");
    cleanString = cleanString.charAt(0).toUpperCase() + cleanString.slice(1).toLowerCase();
  }
  return cleanString;
}
export function DateFilter(dateUTC: string) {
  /* Format date from UTC to custom string (e.g. DD MM YYYY) */
  const date = new Date(dateUTC);
  const formatter = new Intl.DateTimeFormat(locales, {
    year: "numeric",
    month: "numeric",
    day: "numeric"
  });
  return formatter
    .formatToParts(date)
    .map(({ type, value }) => {
      switch (type) {
        case "literal":
          return " ";
        default:
          return value;
      }
    })
    .join("");
}
export function MediumDateFilter(dateUTC: string) {
  /* Format date from UTC to custom string (e.g. Mont dd, YYYY) */
  const date = new Date(dateUTC);
  const options: any = {
    dateStyle: "medium"
  };
  const formatter = new Intl.DateTimeFormat("en", options);

  return formatter.format(date);
}
export function DateTimeFilter(dateUTC: string) {
  /* Format date from UTC to custom string (e.g. DD/MM/YYYY hh:mm:ss) */
  const date = new Date(dateUTC);

  return date.toLocaleString();
}

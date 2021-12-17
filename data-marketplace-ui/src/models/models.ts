type Domain = {
  id: number;
  description: string;
};

type ContentType = {
  id: number;
  description: string;
};

interface Country {
  code: string;
  name: string;
  isEuropeanUnion: string;
  isAcceptedPayPal: string;
  isAcceptedStripe: string;
}

interface PurchaseItemReview {
  purchaseItemUid: string;
  score: number;
  comment: string;
  anonymous: boolean;
}

interface TranslationDocumentReview {
  documentUid?: number;
  score: number;
  comment: string;
  reviewerFirstName: string;
  reviewerLastName: string;
  createdAt: string;
}

interface TranslationDocumentReviewStatistics {
  total: number;
  score: number;
}

class Publication {
  uid: string;
  document?: TranslationDocument;
  status: PublicationStatus;
  errors?: Array<PublicationEvent>;
  createdAt: string;
  updatedAt: string;

  constructor(uid: string, document: TranslationDocument, currentStatus: PublicationStatus) {
    this.uid = uid;
    this.document = document;
    this.status = currentStatus;
  }

  static parse(response: Publication): Publication {
    const publication = new Publication(response.uid, response.document, response.status);
    publication.errors = response.errors;
    return publication;
  }

  hasErrors(): boolean {
    return this.errors !== undefined && this.errors.length > 0;
  }
}

enum PublicationStatus {
  RECEIVED = "RECEIVED",
  ANALYZING = "ANALYZING",
  REVIEW = "REVIEW",
  PUBLISHING = "PUBLISHING",
  PUBLISHED = "PUBLISHED"
}

export const PUBLICATION_STATUSES = [
  PublicationStatus.RECEIVED,
  PublicationStatus.ANALYZING,
  PublicationStatus.REVIEW,
  PublicationStatus.PUBLISHING,
  PublicationStatus.PUBLISHED
];

class PublicationEvent {
  id: string;
  type: string;
  status: PublicationStatus;
  description: string;
  createdAt: string;

  constructor(id: string, description: string, type: string, createdAt: string) {
    this.id = id;
    this.description = description;
    this.type = type;
    this.createdAt = createdAt;
  }
}

class MatchingData {
  requestId: string;
  status?: string;
  type: string;
  email: string;
  fileName: string;
  fileSize?: number;
  totalUnits?: number;
  sampleLanguage?: string;
  sourceLanguage?: Language;
  targetLanguage?: Language;
  domain?: Domain;
  analysisResult?: SampleAnalysisResult;
  allEvents?: Array<MatchingDataEvent>;
  availableSubsets?: Array<MatchingDataAvailableResultSet>;
  sampleUnits?: Array<TranslationUnit>;
  errors?: Array<MatchingDataEvent>;
  hasError?: boolean;
  // createdAt?: string;
  // updatedAt?: string;

  constructor(
    requestId: string,
    status: string,
    type: string,
    email: string,
    fileName: string,
    totalUnits: number,
    sourceLanguage: Language,
    targetLanguage: Language,
    domain: Domain,
    analysisResult: SampleAnalysisResult,
    sampleUnits: Array<TranslationUnit>
  ) {
    this.requestId = requestId;
    this.status = status;
    this.type = type;
    this.email = email;
    this.fileName = fileName;
    this.totalUnits = totalUnits;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.domain = domain;
    this.analysisResult = analysisResult;
    this.sampleUnits = sampleUnits;
  }

  static parse(response: MatchingData): MatchingData {
    const mdObject = new MatchingData(
      response.requestId,
      response.status,
      response.type,
      response.email,
      response.fileName,
      response.totalUnits,
      response.sourceLanguage,
      response.targetLanguage,
      response.domain,
      response.analysisResult,
      response.sampleUnits
    );
    mdObject.allEvents = response.allEvents;
    mdObject.availableSubsets = response.availableSubsets;
    return mdObject;
  }

  hasErrors(): boolean {
    return this.status !== undefined && this.status == "FAILED";
  }
  hasResults(): boolean {
    return this.sampleUnits !== undefined && this.sampleUnits.length > 0;
  }
}

class SampleAnalysisResult {
  LOW: number;
  HIGH: number;
}

class MatchingDataEvent {
  type: string;
  status: string;
  description: string;
  createdAt: string;

  constructor(status: string, description: string, type: string, createdAt: string) {
    this.status = status;
    this.description = description;
    this.type = type;
    this.createdAt = createdAt;
  }
}

class MatchingDataAvailableResultSet {
  datasetSize: MatchingDataResultDatasetSizes;
  metaData: MatchingDataResultMetaData;
  threshold: number;
}

class MatchingDataResultDatasetSizes {
  description: string;
  amount: number;
}

class MatchingDataResultMetaData {
  segmentCount: number;
  sourceWordCount: number;
  targetWordCount: number;
  price: number;
}

class TranslationDocument {
  domain: string;
  contentType: string;
  name: string;
  uid: string;
  size?: number;
  format?: string;
  anonymize?: boolean;
  sourceLanguage?: Language;
  targetLanguage?: Language;

  currentStatistics: TranslationDocumentStatistics;
  statisticsList: Array<TranslationDocumentStatistics>;

  pricePerWord?: number;
  suggestedPricePerWord?: number;
  priceCategory?: LanguageDataAvailabilityCategory;

  owner: string;
  date: string;
  sourceLanguageExample: string;
  targetLanguageExample: string;

  units: Array<TranslationUnit>;
  annotations?: Array<DataAnnotationCategoryCount>;

  constructor(uid: string, domain: string, contentType: string, name: string) {
    this.uid = uid;
    this.domain = domain;
    this.contentType = contentType;
    this.name = name;
  }
}

class TranslationUnit {
  sourceValue: string;
  sourceWordCount?: string;
  sourceAnnotations?: Array<DataAnnotation>;
  targetValue: string;
  targetWordCount?: string;
  targetAnnotations?: Array<DataAnnotation>;
  domain?: string;
  contentType?: string;
  sellerFirstName?: string;
  sellerJoinedAt?: string;
  sellerLastName?: string;
}

class TranslationVariant {
  language: Language;
  value: string;
  wordCount: number;
}

interface DataAnnotation {
  id: number;
  offsetStart: number;
  offsetEnd: number;
  categoryId: number;
  categoryDescription: string;
  categoryCode: string;
  categoryKind: string;
}

interface DataAnnotationCategoryCount {
  id: number;
  code: string; // PER
  description: string; //Personal Names
  type?: string; // PII
  count?: number;
  units: Array<TranslationUnitAnnotation>;
}

interface TranslationUnitAnnotation {
  id: number;
  sourceValue: string;
  sourceWordCount: number;
  targetValue: string;
  targetWordCount: number;
  sourceAnnotations: Array<DataAnnotation>;
  targetAnnotations: Array<DataAnnotation>;
}

interface TranslationDocumentStatistics {
  // units counts
  totalUnits?: number;
  totalUnitsOriginal?: number;
  totalUnitsReplica?: number;
  totalUnitsLowQuality?: number;
  totalUnitsAnnotatedPii?: number;

  // source words counts
  totalSourceWordCount?: number;
  totalSourceOriginalWordCount?: number;
  totalSourceReplicaWordCount?: number;
  totalSourceLowQualityWordCount?: number;
  totalSourceWordCountAnnotatedPii?: number;

  // target words counts
  totalTargetWordCount?: number;
  totalTargetOriginalWordCount?: number;
  totalTargetReplicaWordCount?: number;
  totalTargetLowQualityWordCount?: number;
  totalTargetWordCountAnnotatedPii?: number;
}

enum NotificationType {
  DANGER = "Error",
  INFO = "Information",
  SUCCESS = "Success",
  WARNING = "Attention"
}

class Notification {
  type: NotificationType;
  message: string;

  constructor(type: NotificationType, message: string) {
    this.type = type;
    this.message = message;
  }
  isSuccess(): boolean {
    return this.type === NotificationType.SUCCESS;
  }
  isWarning(): boolean {
    return this.type === NotificationType.WARNING;
  }
  isDanger(): boolean {
    return this.type === NotificationType.DANGER;
  }
  isInfo(): boolean {
    return this.type === NotificationType.INFO;
  }
}

class DataExplorerRequest {
  sourceLanguage: string;
  targetLanguage: string;
  domains?: Array<number>;
  contentTypes?: Array<number>;
  sellers?: string[];
  priceMin?: string;
  priceMax?: string;
  segmentsView: boolean;
  page: number;

  constructor(sourceLanguage: string, targetLanguage: string) {
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.domains = new Array<number>();
    this.contentTypes = new Array<number>();
    this.sellers = new Array<string>();
    this.priceMin = null;
    this.priceMax = null;
    this.segmentsView = null;
    this.page = 0;
  }

  clearFilters() {
    this.domains = new Array<number>();
    this.contentTypes = new Array<number>();
    this.sellers = new Array<string>();
    this.priceMin = null;
    this.priceMax = null;
  }
}

class DataExplorerResult {
  statistics?: ResultStatistics;
  sample: Array<TranslationUnit>;
  sample1: Page<TranslationUnit | SellerDocument>;
  filters: ResultFilters;

  hasResults(): boolean {
    return this.sample !== undefined && this.sample.length > 0;
  }
}

class ResultFilters {
  domains: Array<Domain>;
  contentTypes: Array<ContentType>;
  prices: Array<number>;
  sellers: Array<Seller>;
}

class ResultStatistics {
  sourceWordCountTotal: number;
  targetWordCountTotal: number;
  unitsTotal: number;
  documents: number;
  totalPrice: number;
  sellersTotal: number;
  sourceLanguage: Language;
  targetLanguage: Language;
  collectedAt: string;
}

interface LanguageSourceTargetCountComposite {
  sources: LanguageSourceTargetCount[];
  targets: LanguageSourceTargetCount[];
}

interface LanguageSourceTargetCount {
  source: Language;
  targets: Array<LanguageCount>;
}

interface Language {
  id: number;
  tag: string;
  displayName: string;
  displayCountry?: string;
  enabledPii?: boolean;
  nameAndCountry: string;
}

type LanguageCount = {
  language: Language;
  count: number;
};

interface Page<T> {
  content: Array<T>;
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  totalElements: number;
  totalPages: number;
  size: number;
  pageable: Pageable;
  sort: PageSort;
}

interface PageSort {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}

interface Pageable extends PageSort {
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  unpaged: boolean;
  sort: PageSort;
}

interface PriceIndexEntry {
  sourceLanguage: Language;
  targetLanguage: Language;
  domain: string;
  contentType: string;
  availabilityCategory: LanguageDataAvailabilityCategory;
  suggestedPricePerWord: number;
}

interface LanguageDataAvailabilityCategory {
  id: string;
  label: string;
  sort: number;
}

interface SignUpForm {
  prefix: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  matchingPassword: string;
  country: string;
}

interface SignInForm {
  email: string;
  password: string;
}

interface SuggestedPricePerWord {
  categoryId: number;
  categoryLabel: string;
  categorySort: number;
  commissionFee: number;
  suggestedPricePerWord: number;
  transactionFee: number;
}

class Seller {
  uid: string;
  slugId: string;
  firstName: string;
  lastName: string;
  fullName?: string;
  joinedAt: string;
  languages: string[];
  domains: string[];
  contentTypes: string[];
  verified: boolean;
  documentCount: number;
  documents: SellerDocument[];
}

interface SellerDocument {
  uid: string;
  name: string;
  sourceLanguage: {
    id: number;
    tag: string;
    displayName: string;
    displayCountry: string;
  };
  targetLanguage: {
    id: number;
    tag: string;
    displayName: string;
    displayCountry: string;
  };
  domain: string;
  contentType: string;
  unitCount: number;
  sourceWordCount: number;
  targetWordCount: number;
  price: number;
  pricePerWord: number;
  units: Unit[];
  sellerSlugId: string;
  sellerUid: string;
  sellerFirstName: string;
  sellerLastName: string;
  sellerJoinedAt: string;
  reviewStatistics: TranslationDocumentReviewStatistics;
}

interface Unit {
  sourceValue: string;
  targetValue: string;
}

class User {
  id: number;
  uid: string;
  paymentTermConsent: boolean;
  termsOfServiceConsent: boolean;
  privacyPolicyConsent: boolean;
  verified: boolean;
  requestTermsAgreement: boolean;
  createdAt: string;
  updatedAt: string;
  details: UserDetails;
  paymentDetails?: UserPaymentDetailsApiResponse;
}

interface UserDetails {
  corporateId: number;
  email: string;
  prefix: string;
  firstName: string;
  lastName: string;
  country: string;
}

interface UserPaymentDetailsApiResponse {
  id: number;
  method: string;
  legalType: string;
  vatStatus: string;
  vatNumber: string;
  name: string;
  addressLine: string;
  city: string;
  zipCode: string;
  countryCode: string;
  paypalEmail: string;
  transferwiseType: string | null;
  iban: string;
  swift: string;
  accountNumber: string;
  ifscCode: string;
  bankCode: string;
  branchCode: string;
  stripeId: string;
  stripeChargesEnabled: boolean;
  paymentTermsConsent: boolean;
  transferWiseOptions: TransferWiseOptions;
  bankTransferOptions: BankTransferOptions;
}

interface TransferWiseOptions {
  supportedCountries: TransferWiseSupportedCountry[];
}

interface BankTransferOptions {
  mandatoryIbanCountries: IbanSupportedCountry[];
  recommendedIbanCountries: IbanSupportedCountry[];
}

interface TransferWiseSupportedCountry {
  countryCode: string;
  currencyCode: string;
  type: string;
  requiredFields: string[];
}

interface IbanSupportedCountry {
  countryCode: string;
  length: number;
}

interface TransferWiseResponseEnvelope<T> {
  country?: string;
  values: T[];
}

interface TransferWiseBank {
  code: string;
  title: string;
}

interface TransferWiseBranch extends TransferWiseBank {
  parentCode: string;
}

interface PaymentMethod {
  id: string;
  value: string;
}

interface PaymentDetailsForm {
  method: PaymentMethod | null;
  paypalEmail: string;
  legalType: string;
  addressLine: string;
  city: string;
  zipCode: string;
  country: Country | null;
  name: string;
  vatNumber: string;
  vatStatus: string;
  iban: string;
  transferwiseType: string;
  ifscCode: string;
  accountNumber: string;
  bank: TransferWiseBank | null;
  branch: TransferWiseBranch | null;
  swift: string;
  paymentTermsConsent: boolean;
}

interface UserPaymentDetailsPayload {
  method?: string;
  name: string;
  addressLine: string;
  city: string;
  zipCode: string;
  countryCode: string;
  vatStatus: string;
  vatNumber: string;
  legalType: string;
  paypalEmail: string;
  paymentTermsConsent: boolean;
  transferwiseType: string;
  iban: string;
  ifscCode: string;
  bankCode: string;
  branchCode: string;
  swift: string;
  accountNumber: string;
}

interface SellerPayment {
  uuid: string;
  commissionFee: number;
  status: string;
  requestedAt: string | null;
  settledAt: string | null;
  sumTotalPrice: number;
  sumTotalCommission: number;
  sumTotalEarning: number;
  items: SellerSale[];
}

interface SellerSale {
  docName: string;
  sku: string;
  description: string;
  quantity: number;
  unitPrice: number;
  totalPrice: number;
  unitEarning: number;
  totalEarning: number;
  unitCommission: number;
  totalCommission: number;
  soldAt: string | null;
}

interface UserDocument {
  uid: string;
  name: string;
  size: number;
  description: string;
  pricePerWord: string;
  createdAt: string;
  updatedAt: string;
  status: string;
  publicationUid: string;
  domain: Domain;
  contentType: ContentType;
  currentStatistics: TranslationDocumentStatistics;
  sourceLanguage: Language;
  targetLanguage: Language;
}

enum ProductType {
  SELLER_DOCUMENT = "SDOC",
  VIRTUAL_DOCUMENT = "VDOC",
  MATCHING_DOCUMENT = "MDOC"
}

interface Product {
  sku?: string;
  type?: ProductType;
  documentUid?: string;
  sourceLanguage?: number;
  targetLanguage?: number;
  domains?: Array<number>;
  contentTypes?: Array<number>;
  sellers?: Array<number>;
  priceMin?: number;
  priceMax?: number;
  requestUid?: string;
  amount?: number;
}

interface Cart {
  uid: string;
  items: CartItem[];
  buyer: Buyer;
  payment: CartItemPayment;
  status: string;
  createdAt: string;
  updatedAt: string;
  subTotal: number;
  tax: number;
  serviceFee: number;
  total: number;
}

interface CartItem {
  uid: string;
  product: CartItemProduct;
  price: CartItemPrice;
  review: PurchaseItemReview;
}

interface CartItemProduct {
  sku: string;
  uid: string;
  description: string;
  type: ProductType;
  contentTypes: ContentType[];
  domains: Domain[];
  sourceLanguage: Language;
  targetLanguage: Language;
  amountVariable: boolean;
}

interface CartItemPrice {
  amount: number;
  price: number;
  taxPercentage: number;
  taxAmount: number;
  totalPrice: number;
  totalSourceWords: number;
  totalTargetWords: number;
  totalUnits: number;
}

interface CartItemPayment {
  brokerTransactionId: string;
  createdAt: string;
  id: number;
  method: string;
  status: string;
  updatedAt: string;
}

interface Buyer {
  address: string;
  city: string;
  company: string;
  corporateId: string;
  country: string;
  email: string;
  entityType: string;
  firstName: string;
  lastName: string;
  postalCode: string;
  prefix: string;
  state: string;
  vat: string;
  privacyPolicyConsent: boolean;
  termsOfUseConsent: boolean;
}

enum PaymentMethods {
  STRIPE = "STRIPE",
  BANK_TRANSFER = "BANK_TRANSFER",
  PAYPAL = "PAYPAL"
}

enum PaymentStatus {
  UNPAID = "UNPAID",
  PAID = "PAID",
  CANCELED = "CANCELED"
}

enum CartStatus {
  RECEIVED = "RECEIVED",
  AWAITING_PAYMENT = "AWAITING_PAYMENT",
  COMPLETED = "COMPLETED"
}

interface Price {
  text: string;
  value: number;
}

interface PriceRange {
  min: Price;
  max: Price;
}
interface HSFormData {
  createdAt: Date;
  guid: string;
  name: string;
  submitText: string;
  formFieldGroups: HSFormFieldGroup[];
}

interface HSFormFieldGroup {
  fields: HSFormField[];
}

interface HSFormField {
  defaultValue: string;
  description: string;
  enabled: boolean;
  fieldType: string;
  groupName: string;
  hidden: boolean;
  label: string;
  labelHidden: boolean;
  name: string;
  options: HSFormSelectOption[];
  selectedOptions: HSFormSelectOption[];
  placeholder: string;
  required: boolean;
  type: string;
  unselectedLabel: string;
  validation: HSFormValidation;
}

interface HSFormSelectOption {
  description: string;
  displayOrder: number;
  doubleData: number;
  hidden: boolean;
  label: string;
  readOnly: boolean;
  value: string;
}

interface HSFormValidation {
  blockedEmailAddresses: string[];
  data: string;
  message: string;
  name: string;
  useDefaultBlockList: boolean;
}

interface HSFormDataPayload {
  fields: HSFormFieldSubmitted[];
  context?: HSContextData;
}

interface HSFormFieldSubmitted {
  name: string;
  value: string;
}

interface HSSuccessResponse {
  inlineMessage: string;
}

interface HSContextData {
  hutk?: string;
  pageUri: string;
  pageName: string;
}

interface HSErrorResponse {
  correlationId: string;
  errors: HSError[];
  message: string;
  status: string;
}

interface HSError {
  errorType: string;
  message: string;
}

export {
  Country,
  Domain,
  ContentType,
  Publication,
  PublicationEvent,
  PublicationStatus,
  Notification,
  NotificationType,
  TranslationDocument,
  TranslationDocumentStatistics,
  TranslationUnit,
  TranslationVariant,
  DataExplorerRequest,
  DataExplorerResult,
  ResultFilters,
  ResultStatistics,
  PriceRange,
  LanguageSourceTargetCountComposite,
  LanguageSourceTargetCount,
  Language,
  LanguageCount,
  PriceIndexEntry,
  LanguageDataAvailabilityCategory,
  Page,
  Product,
  ProductType,
  Seller,
  SellerDocument,
  SignUpForm,
  SignInForm,
  SuggestedPricePerWord,
  Unit,
  User,
  UserDocument,
  Cart,
  CartItem,
  CartItemPrice,
  Buyer,
  PaymentMethods,
  PaymentStatus,
  CartStatus,
  Price,
  UserPaymentDetailsPayload,
  UserPaymentDetailsApiResponse,
  PaymentDetailsForm,
  SellerPayment,
  SellerSale,
  TransferWiseResponseEnvelope,
  TransferWiseOptions,
  TransferWiseSupportedCountry,
  TransferWiseBank,
  TransferWiseBranch,
  BankTransferOptions,
  IbanSupportedCountry,
  PaymentMethod,
  HSFormData,
  HSFormFieldGroup,
  HSFormField,
  HSFormValidation,
  HSFormSelectOption,
  HSFormDataPayload,
  HSFormFieldSubmitted,
  HSSuccessResponse,
  HSErrorResponse,
  HSContextData,
  HSError,
  DataAnnotationCategoryCount,
  DataAnnotation,
  TranslationUnitAnnotation,
  MatchingData,
  SampleAnalysisResult,
  MatchingDataEvent,
  MatchingDataAvailableResultSet,
  MatchingDataResultDatasetSizes,
  MatchingDataResultMetaData,
  TranslationDocumentReview,
  TranslationDocumentReviewStatistics,
  PurchaseItemReview
};

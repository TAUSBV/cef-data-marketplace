package net.taus.data.language.model;

public enum TranslationMetadataOrigin {
  DQF, DATA_CLOUD, MARKETPLACE;

  public String translateDomainFromDataCloud(final String dataCloudDomainDescription) {
    return switch (dataCloudDomainDescription) {
      case "Automotive Manufacturing" -> "Automotive";
      case "Chemicals" -> "Chemicals";
      case "Computer Hardware", "Consumer Electronics" -> "Consumer Goods / Electronics & Hardware";
      case "Computer Software", "Telecommunications" -> "Cloud Services & Computer Software";
      case "Energy, Water and Utilities" -> "Energy - Water & Utilities";
      case "Financials" -> "Financial Services";
      case "Healthcare", "Medical Equipment and Supplies" -> "Healthcare/Medical Equipment & Supplies";
      case "Industrial Electronics", "Industrial Manufacturing" -> "Manufacturing/Industrial Electronics";
      case "Legal Services" -> "Legal Services";
      case "Leisure, Tourism, and Arts" -> "Hospitality/Travel/Tourism";
      case "Pharmaceuticals and Biotechnology" -> "Pharmaceuticals & Biotechnology";
      case "Professional and Business Services" -> "Business Services";
      case "Stores and Retail Distribution" -> "Retail & Wholesale Distribution/E-Commerce";
      default -> "Undefined Sector";
    };
  }

  public String translateContentTypeFromDataCloud(final String dataCloudDomainDescription) {
    return switch (dataCloudDomainDescription) {
      case "Financial Documentation" -> "Administrative";
      case "Instructions for Use" -> "User Manual";
      case "News Announcements, Reports and Research" -> "Informative content";
      case "Patents" -> "R&D/Patents";
      case "Policies, Process and Procedures", "Standards, Statutes and Regulations" -> "Legal";
      case "Sales and Marketing Material" -> "Marketing Material";
      case "Software Strings and Documentation" -> "User Interface Text";
      case "Support Content" -> "Online Help";
      default -> "Other";
    };
  }
}

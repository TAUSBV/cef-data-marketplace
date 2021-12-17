package net.taus.data.marketplace.api.validation;

import java.util.Arrays;
import java.util.Optional;

public enum Country {
  AFGHANISTAN("AF", "Afghanistan", true, false, false),
  AKROTIRI("AX", "Åland Islands", true, false, false),
  ALBANIA("AL", "Albania", true, true, false),
  ALGERIA("DZ", "Algeria", true, true, false),
  AMERICAN_SAMOA("AS", "American Samoa", true, false, false),
  ANDORRA("AD", "Andorra", true, true, false),
  ANGOLA("AO", "Angola", true, true, false),
  ANGUILLA("AI", "Anguilla", true, true, false),
  ANTARCTICA("AQ", "Antarctica", true, false, false),
  ANTIGUA_AND_BARBUDA("AG", "Antigua and Barbuda", true, true, false),
  ARGENTINA("AR", "Argentina", true, true, false),
  ARMENIA("AM", "Armenia", true, true, false),
  ARUBA("AW", "Aruba", true, true, false),
  ASHMORE_AND_CARTIER_ISLANDS("null", "Ashmore and Cartier Islands", true, false, false),
  AUSTRALIA("AU", "Australia", true, true, false),
  AUSTRIA("AT", "Austria", true, true, true),
  AZERBAIJAN("AZ", "Azerbaijan", true, true, false),
  BAHAMAS("BS", "Bahamas", true, true, false),
  BAHRAIN("BH", "Bahrain", true, true, false),
  BANGLADESH("BD", "Bangladesh", true, false, false),
  BARBADOS("BB", "Barbados", true, true, false),
  BASSAS_DA_INDIA("null", "Bassas da India", true, false, false),
  BELARUS("BY", "Belarus", true, true, false),
  BELGIUM("BE", "Belgium", true, true, true),
  BELIZE("BZ", "Belize", true, true, false),
  BENIN("BJ", "Benin", true, true, false),
  BERMUDA("BM", "Bermuda", true, true, false),
  BHUTAN("BT", "Bhutan", true, true, false),
  BOLIVIA("BO", "Bolivia (Plurinational State of)", true, true, false),
  //null("BQ","Bonaire, Sint Eustatius and Saba", true,false, false),
  BOSNIA_AND_HERZEGOVINA("BA", "Bosnia and Herzegovina", true, true, false),
  BOTSWANA("BW", "Botswana", true, true, false),
  BOUVET_ISLAND("BV", "Bouvet Island", true, false, false),
  BRAZIL("BR", "Brazil", true, true, false),
  BRITISH_INDIAN_OCEAN_TERRITORY("IO", "British Indian Ocean Territory", true, false, false),
  BRITISH_VIRGIN_ISLANDS("VG", "Virgin Islands (British)", true, true, false),
  BRUNEI("BN", "Brunei Darussalam", true, true, false),
  BULGARIA("BG", "Bulgaria", true, true, true),
  BURKINA_FASO("BF", "Burkina Faso", true, true, false),
  BURMA("MM", "Myanmar", true, false, false),
  BURUNDI("BI", "Burundi", true, true, false),
  CAMBODIA("KH", "Cambodia", true, true, false),
  CAMEROON("CM", "Cameroon", true, true, false),
  CANADA("CA", "Canada", true, true, false),
  CAPE_VERDE("CV", "Cabo Verde", true, true, false),
  CAYMAN_ISLANDS("KY", "Cayman Islands", true, true, false),
  CENTRAL_AFRICAN_REPUBLIC("CF", "Central African Republic", true, false, false),
  CHAD("TD", "Chad", true, true, false),
  CHILE("CL", "Chile", true, true, false),
  CHINA("CN", "China", true, false, false),
  CHRISTMAS_ISLAND("CX", "Christmas Island", true, false, false),
  CLIPPERTON_ISLAND("null", "Clipperton Island", true, false, false),
  COCOS_KEELING_ISLANDS("CC", "Cocos (Keeling) Islands", true, false, false),
  COLOMBIA("CO", "Colombia", true, true, false),
  COMOROS("KM", "Comoros", true, true, false),
  COOK_ISLANDS("CK", "Cook Islands", true, true, false),
  CORAL_SEA_ISLANDS("null", "Coral Sea Islands", true, false, false),
  COSTA_RICA("CR", "Costa Rica", true, true, false),
  COTE_D_IVOIRE("CI", "Côte d'Ivoire", true, true, false),
  CROATIA("HR", "Croatia", true, true, true),
  CUBA("CU", "Cuba", true, false, false),
  CYPRUS("CY", "Cyprus", true, true, true),
  CZECH_REPUBLIC("CZ", "Czechia", true, true, true),
  //null("CW","Curaçao", true,false, false),
  REPUBLIC_OF_THE_CONGO("CG", "Congo", true, true, false),
  DEMOCRATIC_REPUBLIC_OF_THE_CONGO("CD", "Congo, Democratic Republic of the", true, true, false),
  DENMARK("DK", "Denmark", true, true, true),
  DHEKELIA(null, "Dhekelia", true, false, false),
  DJIBOUTI("DJ", "Djibouti", true, true, false),
  DOMINICA("DM", "Dominica", true, true, false),
  DOMINICAN_REPUBLIC("DO", "Dominican Republic", true, true, false),
  ECUADOR("EC", "Ecuador", true, true, false),
  EGYPT("EG", "Egypt", true, true, false),
  EL_SALVADOR("SV", "El Salvador", true, true, false),
  EQUATORIAL_GUINEA("GQ", "Equatorial Guinea", true, false, false),
  ERITREA("ER", "Eritrea", true, true, false),
  ESTONIA("EE", "Estonia", true, true, true),
  SWAZILAND("SZ", "Eswatini (Swaziland)", true, true, false),
  ETHIOPIA("ET", "Ethiopia", true, true, false),
  EUROPA_ISLAND("null", "Europa Island", true, false, false),
  FALKLAND_ISLANDS_ISLAS_MALVINAS("FK", "Falkland Islands (Malvinas)", true, true, false),
  FAROE_ISLANDS("FO", "Faroe Islands", true, true, false),
  FIJI("FJ", "Fiji", true, true, false),
  FINLAND("FI", "Finland", true, true, true),
  FRANCE("FR", "France", true, true, true),
  FRENCH_GUIANA("GF", "French Guiana", true, true, false),
  FRENCH_POLYNESIA("PF", "French Polynesia", true, true, false),
  FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS("TF", "French Southern Territories", true, false, false),
  GABON("GA", "Gabon", true, true, false),
  GAMBIA("GM", "Gambia", true, true, false),
  GAZA_STRIP("null", "Gaza Strip", true, false, false),
  GEORGIA("GE", "Georgia", true, true, false),
  GERMANY("DE", "Germany", true, true, true),
  GHANA("GH", "Ghana", true, false, false),
  GIBRALTAR("GI", "Gibraltar", true, true, false),
  GLORIOSO_ISLANDS("null", "Glorioso Islands", true, false, false),
  GREECE("GR", "Greece", true, true, true),
  GREENLAND("GL", "Greenland", true, true, false),
  GRENADA("GD", "Grenada", true, true, false),
  GUADELOUPE("GP", "Guadeloupe", true, true, false),
  GUAM("GU", "Guam", true, false, false),
  GUATEMALA("GT", "Guatemala", true, true, false),
  GUERNSEY("GG", "Guernsey", true, false, false),
  GUINEA("GN", "Guinea", true, true, false),
  GUINEA_BISSAU("GW", "Guinea-Bissau", true, true, false),
  GUYANA("GY", "Guyana", true, true, false),
  HAITI("HT", "Haiti", true, false, false),
  HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "Heard Island and McDonald Islands", true, false, false),
  HOLY_SEE_VATICAN_CITY("VA", "Holy See", true, true, false),
  HONDURAS("HN", "Honduras", true, true, false),
  HONG_KONG("HK", "Hong Kong", true, true, false),
  HUNGARY("HU", "Hungary", true, true, true),
  ICELAND("IS", "Iceland", true, true, false),
  INDIA("IN", "India", true, true, false),
  INDONESIA("ID", "Indonesia", true, true, false),
  IRAN("IR", "Iran (Islamic Republic of)", true, false, false),
  IRAQ("IQ", "Iraq", true, false, false),
  IRELAND("IE", "Ireland", true, true, true),
  ISLE_OF_MAN("IM", "Isle of Man", true, false, false),
  ISRAEL("IL", "Israel", true, true, false),
  ITALY("IT", "Italy", true, true, true),
  JAMAICA("JM", "Jamaica", true, true, false),
  JAN_MAYEN("null", "Jan Mayen", true, false, false),
  JAPAN("JP", "Japan", true, true, false),
  JERSEY("JE", "Jersey", true, false, false),
  JORDAN("JO", "Jordan", true, true, false),
  JUAN_DE_NOVA_ISLAND("null", "Juan de Nova Island", true, false, false),
  KAZAKHSTAN("KZ", "Kazakhstan", true, true, false),
  KENYA("KE", "Kenya", true, true, false),
  KIRIBATI("KI", "Kiribati", true, true, false),
  KUWAIT("KW", "Kuwait", true, true, false),
  KYRGYZSTAN("KG", "Kyrgyzstan", true, true, false),
  LAOS("LA", "Lao People's Democratic Republic", true, true, false),
  LATVIA("LV", "Latvia", true, true, true),
  LEBANON("LB", "Lebanon", true, false, false),
  LESOTHO("LS", "Lesotho", true, true, false),
  LIBERIA("LR", "Liberia", true, false, false),
  LIBYA("LY", "Libya", true, false, false),
  LIECHTENSTEIN("LI", "Liechtenstein", true, true, false),
  LITHUANIA("LT", "Lithuania", true, true, true),
  LUXEMBOURG("LU", "Luxembourg", true, true, true),
  MACAU("MO", "Macao", true, false, false),
  MACEDONIA("MK", "North Macedonia", true, true, false),
  MADAGASCAR("MG", "Madagascar", true, true, false),
  MALAWI("MW", "Malawi", true, true, false),
  MALAYSIA("MY", "Malaysia", true, true, false),
  MALDIVES("MV", "Maldives", true, true, false),
  MALI("ML", "Mali", true, true, false),
  MALTA("MT", "Malta", true, true, true),
  MARSHALL_ISLANDS("MH", "Marshall Islands", true, true, false),
  MARTINIQUE("MQ", "Martinique", true, true, false),
  MAURITANIA("MR", "Mauritania", true, true, false),
  MAURITIUS("MU", "Mauritius", true, true, false),
  MAYOTTE("YT", "Mayotte", true, true, false),
  MEXICO("MX", "Mexico", true, true, false),
  FEDERATED_STATES_OF_MICRONESIA("FM", "Micronesia (Federated States of)", true, true, false),
  MOLDOVA("MD", "Moldova, Republic of", true, true, false),
  MONACO("MC", "Monaco", true, true, false),
  MONGOLIA("MN", "Mongolia", true, true, false),
  //null("ME","Montenegro", true,true, false),
  MONTSERRAT("MS", "Montserrat", true, true, false),
  MOROCCO("MA", "Morocco", true, true, false),
  MOZAMBIQUE("MZ", "Mozambique", true, true, false),
  NAMIBIA("NA", "Namibia", true, true, false),
  NAURU("NR", "Nauru", true, true, false),
  NAVASSA_ISLAND("null", "Navassa Island", true, false, false),
  NEPAL("NP", "Nepal", true, true, false),
  NETHERLANDS("NL", "Netherlands", true, true, true),
  NETHERLANDS_ANTILLES("null", "Netherlands Antilles", true, false, false),
  NEW_CALEDONIA("NC", "New Caledonia", true, true, false),
  NEW_ZEALAND("NZ", "New Zealand", true, true, false),
  NICARAGUA("NI", "Nicaragua", true, true, false),
  NIGER("NE", "Niger", true, true, false),
  NIGERIA("NG", "Nigeria", true, true, false),
  NIUE("NU", "Niue", true, true, false),
  NORFOLK_ISLAND("NF", "Norfolk Island", true, true, false),
  NORTHERN_MARIANA_ISLANDS("MP", "Northern Mariana Islands", true, false, false),
  NORTH_KOREA("KP", "Korea (Democratic People's Republic of)", true, false, false),
  NORWAY("NO", "Norway", true, true, false),
  OMAN("OM", "Oman", true, true, false),
  PAKISTAN("PK", "Pakistan", true, false, false),
  PALAU("PW", "Palau", true, true, false),
  //null("PS","Palestine, State of", true,false, false),
  PANAMA("PA", "Panama", true, true, false),
  PAPUA_NEW_GUINEA("PG", "Papua New Guinea", true, true, false),
  PARACEL_ISLANDS("null", "Paracel Islands", true, false, false),
  PARAGUAY("PY", "Paraguay", true, true, false),
  PERU("PE", "Peru", true, true, false),
  PHILIPPINES("PH", "Philippines", true, true, false),
  PITCAIRN_ISLANDS("PN", "Pitcairn", true, true, false),
  POLAND("PL", "Poland", true, true, true),
  PORTUGAL("PT", "Portugal", true, true, true),
  PUERTO_RICO("PR", "Puerto Rico", true, false, false),
  QATAR("QA", "Qatar", true, true, false),
  REUNION("RE", "Réunion", true, true, false),
  ROMANIA("RO", "Romania", true, true, true),
  RUSSIA("RU", "Russian Federation", true, true, false),
  RWANDA("RW", "Rwanda", true, true, false),
  //null("BL","Saint Barthélemy", true,false, false),
  SAINT_HELENA("SH", "Saint Helena, Ascension and Tristan da Cunha", true, true, false),
  SAINT_KITTS_AND_NEVIS("KN", "Saint Kitts and Nevis", true, true, false),
  SAINT_LUCIA("LC", "Saint Lucia", true, true, false),
  //null("MF","Saint Martin (French part)", true,false, false),
  SAINT_PIERRE_AND_MIQUELON("PM", "Saint Pierre and Miquelon", true, true, false),
  SAINT_VINCENT_AND_THE_GRENADINES("VC", "Saint Vincent and the Grenadines", true, true, false),
  SAMOA("WS", "Samoa", true, true, false),
  SAN_MARINO("SM", "San Marino", true, true, false),
  SAO_TOME_AND_PRINCIPE("ST", "Sao Tome and Principe", true, true, false),
  SAUDI_ARABIA("SA", "Saudi Arabia", true, true, false),
  SENEGAL("SN", "Senegal", true, true, false),
  SERBIA_AND_MONTENEGRO("RS", "Serbia", true, true, false),
  SEYCHELLES("SC", "Seychelles", true, true, false),
  SIERRA_LEONE("SL", "Sierra Leone", true, true, false),
  SINGAPORE("SG", "Singapore", true, true, false),
  //null("SX","Sint Maarten (Dutch part)", true,false, false),
  SLOVAKIA("SK", "Slovakia", true, true, true),
  SLOVENIA("SI", "Slovenia", true, true, true),
  SOLOMON_ISLANDS("SB", "Solomon Islands", true, true, false),
  SOMALIA("SO", "Somalia", true, true, false),
  SOUTH_AFRICA("ZA", "South Africa", true, true, false),
  SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("GS", "South Georgia and the South Sandwich Islands", true, false, false),
  SOUTH_KOREA("KR", "Korea, Republic of", true, true, false),
  SPAIN("ES", "Spain", true, true, true),
  SPRATLY_ISLANDS("null", "Spratly Islands", true, false, false),
  SRI_LANKA("LK", "Sri Lanka", true, true, false),
  SUDAN("SD", "Sudan", true, false, false),
  //null("SS","South Sudan", true,false, false),
  SURINAME("SR", "Suriname", true, true, false),
  SVALBARD("SJ", "Svalbard and Jan Mayen", true, true, false),
  SWEDEN("SE", "Sweden", true, true, true),
  SWITZERLAND("CH", "Switzerland", true, true, false),
  SYRIA("SY", "Syrian Arab Republic", true, false, false),
  TAIWAN("TW", "Taiwan, Province of China", true, true, false),
  TAJIKISTAN("TJ", "Tajikistan", true, true, false),
  TANZANIA("TZ", "Tanzania, United Republic of", true, true, false),
  THAILAND("TH", "Thailand", true, true, false),
  TIMOR_LESTE("TL", "Timor-Leste", true, false, false),
  TOGO("TG", "Togo", true, true, false),
  TOKELAU("TK", "Tokelau", true, false, false),
  TONGA("TO", "Tonga", true, true, false),
  TRINIDAD_AND_TOBAGO("TT", "Trinidad and Tobago", true, true, false),
  TROMELIN_ISLAND("null", "Tromelin Island", true, false, false),
  TUNISIA("TN", "Tunisia", true, true, false),
  TURKEY("TR", "Turkey", true, false, false),
  TURKMENISTAN("TM", "Turkmenistan", true, true, false),
  TURKS_AND_CAICOS_ISLANDS("TC", "Turks and Caicos Islands", true, true, false),
  TUVALU("TV", "Tuvalu", true, true, false),
  UGANDA("UG", "Uganda", true, true, false),
  UKRAINE("UA", "Ukraine", true, true, false),
  UNITED_ARAB_EMIRATES("AE", "United Arab Emirates", true, true, false),
  UNITED_KINGDOM("GB", "United Kingdom of Great Britain and Northern Ireland", true, true, false),
  UNITED_STATES("US", "United States", true, true, false),
  //    ;UM("United, false),"States Minor Outlying Islands", true,false, false),
  URUGUAY("UY", "Uruguay", true, true, false),
  UZBEKISTAN("UZ", "Uzbekistan", true, false, false),
  VANUATU("VU", "Vanuatu", true, true, false),
  VENEZUELA("VE", "Venezuela (Bolivarian Republic of)", true, true, false),
  VIETNAM("VN", "Vietnam", true, true, false),
  VIRGIN_ISLANDS("VI", "Virgin Islands (U.S.)", true, false, false),
  WAKE_ISLAND("null", "Wake Island", true, false, false),
  WALLIS_AND_FUTUNA("WF", "Wallis and Futuna", true, true, false),
  WESTERN_SAHARA("EH", "Western Sahara", true, false, false),
  WEST_BANK("null", "West Bank", true, false, false),
  YEMEN("YE", "Yemen", true, true, false),
  ZAMBIA("ZM", "Zambia", true, true, false),
  ZIMBABWE("ZW", "Zimbabwe", true, true, false);

  private final String code;
  private final String name;
  private final boolean isAcceptedStripe;
  private final boolean isAcceptedPayPal;
  private final boolean isEuropeanUnion;

  Country(final String code, final String name, final boolean isAcceptedStripe, final boolean isAcceptedPayPal, final boolean isEuropeanUnion) {
    this.code = code;
    this.name = name;
    this.isAcceptedStripe = isAcceptedStripe;
    this.isAcceptedPayPal = isAcceptedPayPal;
    this.isEuropeanUnion = isEuropeanUnion;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public boolean isAcceptedPayPal() {
    return isAcceptedPayPal;
  }

  public boolean isAcceptedStripe() {
    return isAcceptedStripe;
  }

  public boolean isEuropeanUnion() {
    return isEuropeanUnion;
  }

  public boolean isNetherlands() {
    return NETHERLANDS.getCode().equals(this.code);
  }

  @Override
  public String toString() {
    return name;
  }

  public static Optional<Country> findByName(final String name) {
    return Arrays.stream(values()).filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
  }
}

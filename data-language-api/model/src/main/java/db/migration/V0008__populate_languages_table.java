package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.util.Locale;

public class V0008__populate_languages_table extends BaseJavaMigration {
  public void migrate(Context context) {

    var insertQuery = new StringBuilder().append("insert into languages " +
      "(name, display_name, country, display_country, script, variant, tag, enabled_pii) values ");
    var values = "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'),";
    for (Locale l : Locale.getAvailableLocales()) {
      if (!l.getLanguage().isBlank() && !l.getDisplayName().isBlank()) {
        insertQuery.append(
          String.format(values,
            l.getLanguage(),
            l.getDisplayLanguage(),
            l.getCountry(),
            l.getDisplayCountry(),
            l.getScript(),
            l.getVariant(),
            l.toLanguageTag(),
            0
          )
        );
      }
    }
    insertQuery.deleteCharAt(insertQuery.length() - 1);

    new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true)).execute(insertQuery.toString());
  }
}
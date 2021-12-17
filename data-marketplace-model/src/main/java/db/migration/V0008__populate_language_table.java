package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.util.Locale;

public class V0008__populate_language_table extends BaseJavaMigration {
  public void migrate(Context context) {

    var insertQuery = new StringBuilder().append("insert into language " +
      "(display_name, name, script, country, display_country, variant, tag) values ");
    var values = "('%s', '%s', '%s', '%s', '%s', '%s', '%s'),";
    for (Locale l : Locale.getAvailableLocales()) {
      if (!l.getLanguage().isBlank() && !l.getDisplayName().isBlank()) {
        insertQuery.append(
          String.format(values,
            l.getDisplayName(),
            l.getLanguage(),
            l.getScript(),
            l.getCountry(),
            l.getDisplayCountry(),
            l.getVariant(),
            l.toLanguageTag())
        );
      }
    }
    insertQuery.deleteCharAt(insertQuery.length() - 1);

    new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true)).execute(insertQuery.toString());
  }
}
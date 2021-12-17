package net.taus.data.language.api;

import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.search.LanguageDataSearchDocumentResult;
import net.taus.data.language.api.model.search.LanguageDataSearchUnitResult;
import net.taus.data.language.api.web.QueryParam;
import net.taus.data.language.api.web.WebClient;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DataLanguageSearchClient {

  private final WebClient http;

  public DataLanguageSearchClient(final String url) {
    this.http = new WebClient(url);
  }

  public DataLanguageSearchClient(final WebClient webClient) {
    this.http = webClient;
  }

  public LanguageDataSearchUnitResult units(final LanguageDataParams params) {
    return http.get(format("%s", Endpoints.SEARCH_UNITS), QueryParam.transform(params), LanguageDataSearchUnitResult.class);
  }

  public LanguageDataSearchDocumentResult documents(final LanguageDataParams params, final Pageable pageRequest) {
    final List<QueryParam> queryParams = new ArrayList<>();
    queryParams.add(new QueryParam("pg", String.valueOf(pageRequest.getPageNumber())));
    queryParams.add(new QueryParam("pgs", String.valueOf(pageRequest.getPageSize())));
    queryParams.addAll(QueryParam.transform(params));
    return http.get(format("%s", Endpoints.SEARCH_DOCUMENTS), queryParams, LanguageDataSearchDocumentResult.class);
  }

}

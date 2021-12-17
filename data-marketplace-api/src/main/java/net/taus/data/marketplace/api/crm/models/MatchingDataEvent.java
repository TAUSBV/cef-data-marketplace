package net.taus.data.marketplace.api.crm.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import net.taus.support.crm.hubspot.models.TimelineEvent;

import java.util.Collections;
import java.util.List;

/**
 * Data Marketplace event when a user makes an MD request
 */
@Getter
public class MatchingDataEvent implements TimelineEvent {

    int eventTemplateId;
    String email;
    String requestStatus;
    String inputQueryName;
    String requestUrl;
    List<String> errors;


    public MatchingDataEvent(int eventTemplateId, String email, String requestStatus, String inputQueryName, String requestUrl) {
        this.eventTemplateId = eventTemplateId;
        this.email = email;
        this.requestStatus = requestStatus;
        this.inputQueryName = inputQueryName;
        this.requestUrl = requestUrl;
        this.errors = Collections.emptyList();
    }

    public MatchingDataEvent(int eventTemplateId, String email, String requestStatus, String inputQueryName, String requestUrl, List<String> errors) {
        this.eventTemplateId = eventTemplateId;
        this.email = email;
        this.requestStatus = requestStatus;
        this.inputQueryName = inputQueryName;
        this.requestUrl = requestUrl;
        this.errors = errors;
    }


    @Override
    public JsonNode getTokens() {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("requestStatus", requestStatus);
        node.put("inputQueryName", inputQueryName);
        node.put("requestUrl", requestUrl);
        node.put("errors", String.join("  \n", errors));

        return node;
    }

    @Override
    public JsonNode getExtraData() {
        return null;
    }
}

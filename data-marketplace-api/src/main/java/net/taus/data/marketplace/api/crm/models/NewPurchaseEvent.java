package net.taus.data.marketplace.api.crm.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Value;
import net.taus.support.crm.hubspot.models.TimelineEvent;

import java.util.List;

/**
 * Data Marketplace event when a user makes a new purchase.
 */
@Value
public class NewPurchaseEvent implements TimelineEvent {

    int eventTemplateId;
    String email;
    String uid;
    String header;
    String status;
    String date;
    String purchaseUrl;
    String url;
    List<Item> items;


    /**
     * TODO
     *
     * @param eventTemplateId The hubspot event template we want to use
     * @param email           The email of the user who made the purchase
     * @param uid             The purchase unique identifier as it will be presented to the user
     * @param header          A header value for purchase. Can be used as subject in subsequent emails triggered from HS workflows
     * @param status          The purchase status
     * @param date            The purchase formatted date
     * @param purchaseUrl     The url we redirect the user to when the purchase is completed in order to download the documents
     * @param url             The applications url
     * @param items           The purchase item list
     */
    public NewPurchaseEvent(int eventTemplateId,
                            String email,
                            String uid,
                            String header,
                            String status,
                            String date,
                            String purchaseUrl,
                            String url,
                            List<Item> items) {
        this.eventTemplateId = eventTemplateId;
        this.email = email;
        this.uid = uid;
        this.header = header;
        this.status = status;
        this.date = date;
        this.purchaseUrl = purchaseUrl;
        this.url = url;
        this.items = items;
    }

    @Value
    public static class Item {
        String product;
        String amount;
        String totalSourceWordCount;
        String sourceLanguage;
        String totalUnits;
        String price;
        String downloadUrl;
    }

    @Override
    public JsonNode getTokens() {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("header", header);
        node.put("uid", uid);
        node.put("status", status);
        node.put("date", date);
        node.put("url", url);
        node.put("purchaseUrl", purchaseUrl);
        node.put("items", getFormattedItems());

        return node;
    }

    private String getFormattedItems() {
        StringBuilder itemsString = new StringBuilder();
        for (Item i : items) {
            itemsString.append("<strong>Product: ").append(i.product).append("</strong><br/>");
            itemsString.append("Amount: ").append(i.amount).append("<br/>");
            itemsString.append("Number of segments: ").append(i.totalUnits).append("<br/>");
            itemsString.append("Number of words: ").append(i.totalSourceWordCount).append("<br/>");
            itemsString.append("Price: ").append(i.price).append("<br/>");
            if(i.downloadUrl!=null)
              itemsString.append("Download: ").append(i.downloadUrl).append("<br/>");
            itemsString.append("<br/>");
        }

        return itemsString.toString();
    }

    @Override
    public JsonNode getExtraData() {
        ObjectNode extraDataNode = objectMapper.createObjectNode();
        ArrayNode itemsNode = extraDataNode.putArray("items");
        items.forEach(item -> itemsNode.add(objectMapper.valueToTree(item)));

        return extraDataNode;
    }


}

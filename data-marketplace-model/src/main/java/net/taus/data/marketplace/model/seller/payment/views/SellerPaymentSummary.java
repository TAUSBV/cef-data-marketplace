package net.taus.data.marketplace.model.seller.payment.views;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.taus.data.marketplace.model.support.TimeConverter;
import net.taus.data.marketplace.model.support.UUIDDeserializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class SellerPaymentSummary {

  private final UUID uuid;
  private final double commissionFee;
  private final Status status;
  private final LocalDateTime requestedAt;
  private final LocalDateTime settledAt;
  private final List<SoldItem> items;
  private double sumTotalPrice;
  private double sumTotalCommission;
  private double sumTotalEarning;

  public enum Status { PENDING, SETTLED }


  public SellerPaymentSummary(UUID uuid, Status status, LocalDateTime requestedAt, LocalDateTime settledAt, List<SoldItem> soldItems) {

    this.uuid = uuid;
    this.status = status;
    this.requestedAt = requestedAt;
    this.settledAt = settledAt;
    this.commissionFee = 0;
    this.sumTotalPrice = 0;
    this.sumTotalCommission = 0;
    this.sumTotalEarning = 0;

    this.items = soldItems;
    for (SoldItem item : soldItems) {
      this.sumTotalPrice += item.getTotalPrice();
      this.sumTotalCommission += item.getTotalCommission();
      this.sumTotalEarning += item.getTotalEarning();
    }
  }

  @Getter
  @AllArgsConstructor
  public static class SoldItem {

    private final String docName;
    private final String sku;
    private final String description;
    private final double quantity;
    private final double unitPrice;
    private final double totalPrice;
    private final double unitEarning;
    private final double totalEarning;
    private final double unitCommission;
    private final double totalCommission;
    private final LocalDateTime soldAt;

    public SoldItem(String sku, String description, double quantity, double unitPrice, LocalDateTime soldAt, double commission) {
      this.sku = sku;
      this.description = description;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
      this.totalPrice = quantity * this.unitPrice;
      this.unitCommission = this.unitPrice * commission;
      this.totalCommission = quantity * this.unitCommission;
      this.unitEarning = this.unitPrice - this.unitCommission;
      this.totalEarning = this.totalPrice - this.totalCommission;
      this.soldAt = soldAt;
      this.docName = description;
    }
  }

  public static SellerPaymentSummary transformPayment(JsonNode payment) {
    // Used to deserialize the payment state value (UUID)
    final UUIDDeserializer uuidDeserializer = new UUIDDeserializer();

    final List<SellerPaymentSummary.SoldItem> soldItems = new ArrayList<>();

    final JsonNode lines = payment.get("lines");
    lines.forEach(line -> {
      final JsonNode product = line.get("product");
      soldItems.add(new SellerPaymentSummary.SoldItem(product.get("code").asText(),
        product.get("description").asText(),
        line.get("quantity").asDouble(),
        line.get("productPrice").asDouble(),
        TimeConverter.timestampToLocalDateTime(payment.get("timestampCreated").asLong()),
        0)
      );
    });
    String stateValue = payment.get("stateValue").asText();
    UUID uuid;
    try {
      uuid = UUID.fromString(stateValue);
    }
    catch (Exception e) {
      uuid = uuidDeserializer.convert(stateValue);
    }
    return new SellerPaymentSummary(uuid,
      payment.get("status").asText().equals("Completed") ? SellerPaymentSummary.Status.SETTLED : SellerPaymentSummary.Status.PENDING,
      TimeConverter.timestampToLocalDateTime(payment.get("timestampCreated").asLong()),
      TimeConverter.timestampToLocalDateTime(payment.get("timestampUpdated").asLong()),
      soldItems);
  }

}

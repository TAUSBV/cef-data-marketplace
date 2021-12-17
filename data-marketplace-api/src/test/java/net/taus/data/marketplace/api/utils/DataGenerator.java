package net.taus.data.marketplace.api.utils;

import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.marketplace.model.user.UserDetail;

import java.util.List;
import java.util.UUID;

public class DataGenerator {

  public static Purchase createPurchase(final UUID documentUid, final Buyer buyer, final User seller) {
    final Purchase purchase = new Purchase();
    purchase.setBuyer(buyer);
    final Product product = new Product("Product", "Product Description", "123",
      new Language(1L, "en-GB", "English", "English United Kingdom",
        "UK", "United Kingdom", true),
      new Language(1L, "pt-BR", "Portuguese", "Portuguese Brazil",
        "BR", "Brazil", true),
      new Domain(1L, "Aviation"),
      10L, 10L, 10L, 10.0, 10.0, 10.0
    );
    final PurchaseItemSellerShare share = new PurchaseItemSellerShare(seller, documentUid, product.getName(),
      0.0065, 10L, 10L, 10L);
    final PurchaseItem item = new PurchaseItem(product, List.of(share));
    purchase.addItem(item);
    return purchase;
  }

  public static User createSeller() {
    return new User(1L, "Mr.",
      new UserDetail("john@appleseed.com", "John", "Appleseed", "UK"));
  }

  public static Buyer createBuyer() {
    return new Buyer(new UserDetail("john@appleseed.com",
      "John", "Appleseed", "UK", "Street 10", "Amsterdam",
      "North-Holland", "9900 AA", UserDetail.EntityType.PRIVATE, null, null),
      true, true);
  }
}

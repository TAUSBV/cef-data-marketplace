<template>
  <div class="w-3/4 mx-auto mt-8">
    <SectionTitle title="Data Marketplace Payment Terms" />
    <div class="pb-32 pt-24">
      <ol class="list-decimal">
        <li class="leading-relaxed pb-5">
          The data that the Seller publishes on the Data Marketplace can be sold either as whole documents, or in
          fragments on segment basis.
        </li>
        <li class="leading-relaxed pb-5">
          The Seller defines the price per word that gets applied to their datasets and can update it at any time.
        </li>
        <li class="leading-relaxed pb-5">
          TAUS offers large data customers the option to review and try data before they make a purchase decision (‘Try
          Before You Buy’). The period for review by these customers is maximum 60 days.
        </li>
        <li class="leading-relaxed pb-5">
          TAUS notifies the Seller when their data gets sold. TAUS reserves the right to withhold the payment for 14
          days in order to give the Buyer the right to review the purchased data. In case the Buyer raises a complaint
          regarding the data quality, TAUS reserves the right to reject the payment to the Seller.
        </li>
        <li class="leading-relaxed pb-5">
          TAUS offers a self-billing option to all Sellers. It means that TAUS can issue invoices on behalf of the
          Seller for the amounts due for payment. The invoices are issued in the first week of each calendar month. The
          raised invoices meet the
          <a
            href="https://ec.europa.eu/taxation_customs/business/vat/eu-vat-rules-topic/vat-invoicing-rules_en"
            target="_blank"
            class="inline-block text-blue-700 font-medium"
            >EU requirements on self-billing</a
          >.
        </li>
        <li class="leading-relaxed pb-5">
          If the Seller doesn’t want to use the self-billing option, they are asked to issue their own invoices for the
          amounts due for payment.
        </li>
        <li class="leading-relaxed pb-5">
          The available payment methods are bank transfer, Stripe and PayPal.
        </li>
        <li class="leading-relaxed pb-5">
          Payments are made in Euro only.
        </li>
        <li class="leading-relaxed pb-5">
          The Seller agrees to any eventual transaction fees that the selected payment provider might apply to payments.
          These costs are outside of TAUS control and depend on many variables (country, bank, currency, etc). For more
          information on these fees, the Seller should consult the relevant payment provider.
        </li>
        <li class="leading-relaxed pb-5">
          The Seller is responsible to update immediately the payment information on their user profile page in case of:
          <ul class="list-disc list-inside pt-3">
            <li class="leading-relaxed pb-3">
              changes in the VAT registration number (EU) or local Tax ID number (non-EU) - if applicable;
            </li>
            <li class="leading-relaxed pb-3">termination of the company/business activities (if applicable);</li>
            <li class="leading-relaxed pb-3">changes to the preferred payment method.</li>
          </ul>
        </li>
        <li class="leading-relaxed pb-5">
          TAUS charges a commission of 15% on all transactions. (Until October 31 2021 no commissions will be charged.)
        </li>
      </ol>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

export default Vue.extend({
  name: "PaymentTerms",
  components: {
    SectionTitle
  }
});
</script>

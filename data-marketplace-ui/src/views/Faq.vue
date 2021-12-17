<template>
  <article class="w-3/4 mx-auto my-12">
    <p>
      <SectionTitle title="Get To Know Better the Data Marketplace" />
    </p>
    <section class="grid grid-cols-1 gap-4 mt-12">
      <SectionTitle title="Data Ownership" position="left" />
      <h2 class="font-medium text-2xl">
        I am a freelance translator and I have many TMs. Can I sell them?
      </h2>
      <p>
        That depends. First of all you have to consider whether or not the text that you translated was copyrighted.
        Copyright normally applies to a whole document, or at least to a string of text with a minimum length of eleven
        words, provided that from those eleven words one can still distinguish ‘the hand of the author’. Note that the
        <router-link to="/tou" class="underline">Data Marketplace Terms of Use</router-link>
        do not allow for a reconstruction of full documents from the uploaded translation memories.<br />
        A second consideration is whether you have the right to sell the translation segments. As a freelance translator
        you normally share ownership of the target side of the translation with the author of the source text that you
        translated. In a corporate business and publishing environment, freelance authors and translators often sign an
        agreement with their clients in which they transfer ownership of the texts to the clients.<br />
        A third consideration is that as a freelance translator you have invested in the creation of the translation
        memories. Under European law that gives you specific rights to the database.<br />
        As you can see, there is no simple and unambiguous answer to this question. But as Baker McKenzie and TAUS state
        in a joint
        <a href="https://www.taus.net/white-paper-who-owns-my-language-data" target="_blank" class="underline"
          >White Paper</a
        >
        on the ownership of translation data, the problems exist mostly in a theoretical setting. If a problem arises, a
        ‘notice and take-down’ will settle it. Courts are unwilling to award financial compensation and we have no
        record of legal cases around the ‘unlawful’ use of translation memories.<br />
        If you are in doubt, you may want to check in with your customers and perhaps ask their consent for sharing your
        translation memories in the Data Marketplace.
      </p>
      <h2 class="font-medium text-2xl">
        I work in an LSP and we have many TMs. Can we sell them?
      </h2>
      <p>
        As a language service provider you may already have formulated a company policy when it comes to how you manage
        the translation memories. Given the changes in the translation industry and the rapid adoption of MT, you may
        want to take another look at your policies. Data is in ever greater demand and your customers will also benefit
        if translation memories are being transformed and used for the training of MT engines. For advice on whether you
        can share and sell your translation memories, please consult the
        <a href="https://www.taus.net/white-paper-who-owns-my-language-data" target="_blank" class="underline">
          White Paper on Who Owns My Language Data.
        </a>
      </p>
      <h2 class="font-medium text-2xl">
        How can I find out if I have the rights to sell my TMs?
      </h2>
      <p>
        Whether you are the customer (who paid for the translations), the LSP (the agency in the middle) or the
        translator (who actually produced the translation), the question whether you have the rights to share and sell
        the translation memories is never a simple question. Considerations to take into account are:
      </p>
      <ol class="list-decimal ml-4">
        <li>
          Is the source text copyrighted or copyrightable? Note that in the case of translation memories we are not
          looking at the entire document in source and or target language, but at individual segments. Under European
          law a sequence of text must contain at least 11 words to make it copyrightable. Even then it must be
          ‘creative’ in the sense that the ‘hand of the author’ can be recognized.
        </li>
        <li>
          Ownership of the target side of the translations is normally shared between the author and the translator of
          the text, unless a service agreement has been signed with the company paying for the translation in which an
          explicit transfer of copyright is mentioned.
        </li>
        <li>
          Then there are also certain rights that are attributed to the database, in Europe at least. The translators
          and the language service providers who have built up the translation memories over time are usually the
          holders of the so-called sui generis rights.
        </li>
      </ol>
      <p>
        For more insights and guidance on the rights to sell translation memories, see the
        <a href="https://www.taus.net/white-paper-who-owns-my-language-data" target="_blank" class="underline">
          White Paper.
        </a>
      </p>
      <h2 class="font-medium text-2xl">
        Have there ever been legal cases for use of TMs?
      </h2>
      <p>
        As Baker McKenzie and TAUS state in the
        <a href="https://www.taus.net/white-paper-who-owns-my-language-data" target="_blank" class="underline">
          White Paper.</a
        >, we are not aware of a precedent of a translator or a company being penalized or sued for using data for which
        perhaps they did not own the copyrights. The best advice we can give is to always consider if your use or
        sharing of the language data could possibly damage the interests of other parties: your translators or your
        customers. And if any concerns or complaints arise, treat them respectfully and with careful consideration. If a
        dispute would still come up the most common resolution is a ‘notice and take-down’ action from your side.
      </p>
      <h2 class="font-medium text-2xl">
        What happens with the copyrights of the data sold through the Data Marketplace?
      </h2>
      <p>
        The copyrights do not change. Whoever buys data on the Data Marketplace gets the right to use the data, but she
        or he does not acquire the copyrights. The copyrights always stay with the original copyright owners.
      </p>
      <h2 class="font-medium text-2xl">
        How will the sold datasets be used?
      </h2>
      <p>
        Typically, organizations acquire language data to train and customize their MT engines. However, the data could
        also be used in other NLP applications.
      </p>
      <h2 class="font-medium text-2xl">
        Should I ask for my customer’s permission to sell my TMs?
      </h2>
      <p>
        That depends. If you have signed a contract or a service agreement with your customer and this agreement
        contains a clause about the transfer of ownership of translation memories, it will be good to check in with your
        customer.
      </p>
      <h2 class="font-medium text-2xl">
        Is selling language data compliant with GDPR?
      </h2>
      <p>
        In the context of the Data Marketplace, the seller acts as a data controller and TAUS acts as a data processor.
        That means that as a seller, you grant TAUS the right to process your datasets on your behalf and you are also
        responsible for ensuring that the datasets are free of personally identifiable information. Upon discovery that
        any of the published datasets contain personally identifiable information, TAUS will notify you.
      </p>
      <h2 class="font-medium text-2xl">
        My TMs may contain sensitive and personal data. Can I still sell them?
      </h2>
      <p>
        By making your datasets available for sale on the Data Marketplace, you as the seller confirm that you are
        familiar with the content of the dataset and that it does not contain any sensitive or personally identifiable
        information. TAUS will provide anonymization tools that facilitate the detection of potential personally
        identifiable information, and you will be offered a possibility to remove the corresponding segments from the
        dataset.
      </p>
      <h2 class="font-medium text-2xl">
        Can someone buy my TM and resell it?
      </h2>
      <p>
        No, the
        <router-link to="/tou" class="underline">Data Marketplace Terms of Use</router-link>
        do not allow buyers of TMs to resell the data as such.
      </p>
      <h2 class="font-medium text-2xl">
        An ownership issue is raised for a dataset. How does the Marketplace handle those cases?
      </h2>
      <p>
        If the seller finds out that the dataset that they have put up for sale in the Data Marketplace contains
        proprietary information that cannot be shared with other parties, the seller should inform TAUS immediately.
        TAUS will retrieve and remove the affected listings from the platform.<br />
        Data Marketplace is a technology platform that enables anyone to buy and sell translation data. The marketplace
        model means we do not review or edit the data content for legal issues, and we are not in a position to
        determine the legality of published content. However, it is important to us that the buyers and sellers respect
        the intellectual property of others. When sellers publish a dataset on the Data Marketplace, they make the
        promise that they have the necessary authorization or rights to use all the content contained in their datasets.
        Infringing activity is not tolerated on or through our platform.<br />
        The
        <router-link to="/tou" class="underline">Data Marketplace Terms of Use</router-link>
        address what we do in the event of copyright infringement reports from content owners and trademark infringement
        claims from trademark owners with respect to the data on the Data Marketplace.
      </p>
    </section>
    <section class="grid grid-cols-1 gap-4 mt-12">
      <SectionTitle title="Payment terms" position="left" />
      <h2 class="font-medium text-2xl">
        What is the Price Index?
      </h2>
      <p>
        The
        <router-link to="/tou" class="underline">Price Index</router-link>
        is meant to be used as guidance for new data sellers. It shows the suggested data price based on the
        availability of datasets in a specific language pair, domain and content type.
      </p>
      <h2 class="font-medium text-2xl">
        Can I update the price of my listing?
      </h2>
      <p>
        Yes, you can update your price at any time.
      </p>
      <h2 class="font-medium text-2xl">
        How do I set the right price?
      </h2>
      <p>
        You can use the price index suggestion as the baseline and adjust the final rate based on your preference.
      </p>
      <h2 class="font-medium text-2xl">
        How will I know if my dataset is sold to anyone?
      </h2>
      <p>
        You will be notified by email when your listing is sold.
      </p>
      <h2 class="font-medium text-2xl">
        When will I be paid?
      </h2>
      <p>
        The Marketplace processes payments on a monthly basis. When your data gets sold, you will be notified about the
        date the payment will be processed. Please note that the minimum payable amount applies. You will be eligible
        for payout once your accrued revenue balance reaches 10 EUR. If you do not make over 10 EUR in a given calendar
        month, we will roll over the balance to the next month until this minimum threshold is met.
      </p>
      <h2 class="font-medium text-2xl">
        How can I receive the payment?
      </h2>
      <p>
        The available payment methods are Stripe and bank transfer. You can select your preferred payment method in your
        seller account settings.
      </p>
    </section>
    <section class="grid grid-cols-1 gap-4 mt-12">
      <SectionTitle title="General" position="left" />
      <h2 class="font-medium text-2xl">
        Will I know who the buyers are?
      </h2>
      <p>
        No, the buyer’s identity is not disclosed to the sellers.
      </p>
      <h2 class="font-medium text-2xl">
        Can I get a refund if I discover quality issues in the purchased datasets?
      </h2>
      <p>
        The Buyer can submit a complaint about the quality of the purchased datasets within two weeks after the
        purchase. TAUS will review the objections and offer a solution case by case.
      </p>
      <h2 class="font-medium text-2xl">
        Can I exchange my data for other data?
      </h2>
      <p>
        Currently the Marketplace does not support direct exchange of datasets. If you have data available for sale,
        please publish it on the Marketplace. You will be notified when someone purchases it. At the same time, you can
        explore other available datasets and purchase them at your convenience.
      </p>
      <h2 class="font-medium text-2xl">
        What are the supported file formats?
      </h2>
      <p>
        At the moment, the Marketplace supports data uploads in the TMX file format. The TMX files should follow the
        rules below:
      </p>
      <ul class="list-disc ml-4">
        <li>
          The file should be bilingual, i.e. all
          <code class="font-mono font-bold">&lt;tu/&gt;</code> tag should contain exactly two variants (<code
            class="font-mono font-bold"
            >&lt;tuv/&gt;</code
          >).
        </li>
        <li>
          The file should not define the value '<strong>all</strong>' for its
          <code class="font-mono font-bold">&lt;header/&gt;</code> tag
          <code class="font-mono font-bold">srclang</code>
          attribute.
        </li>
        <li>
          The file should not have
          <code class="font-mono font-bold">&lt;tu/&gt;</code> tags with
          <code class="font-mono font-bold">srclang</code> that differs from the
          <code class="font-mono font-bold">&lt;header/&gt;</code>.
        </li>
        <li>The file should be <strong>UTF-8</strong> encoded.</li>
        <li>
          The file should be in conformance with the
          <a href="https://www.gala-global.org/tmx-14b" target="_blank" class="underline"
            >TMX Gala specification TMX 1.4b.</a
          >
        </li>
      </ul>
      <h2 class="font-medium text-2xl">
        Can I remove my listing from the Marketplace if I no longer want to sell it?
      </h2>
      <p>
        Yes, you can. To remove your listing from the Marketplace, please contact the TAUS support team
        <a href="mailto:datamarketplace@taus.net" class="underline">datamarketplace@taus.net.</a>
      </p>
      <h2 class="font-medium text-2xl">
        Can I delete my seller’s account?
      </h2>
      <p>
        Yes, you can delete your account. This action will also remove all your listings from the Marketplace. To delete
        your account please contact the TAUS support team
        <a href="mailto:datamarketplace@taus.net" class="underline">datamarketplace@taus.net.</a>
      </p>
      <h2 class="font-medium text-2xl">
        What happens to the datasets that get uploaded to the Marketplace?
      </h2>
      <p>
        Upon upload the raw dataset is encrypted and proceeds to a quality assurance step, where we verify the file
        format, source and target languages text matching, text length matching, count the number of segments, check for
        duplicated segments and potential PII's and several other checks to separate high-quality segments from
        low-quality ones. After the analyzis, the seller reviews the QA results and decides if he/she wants to publish
        the dataset in the Marketplace.
      </p>
    </section>
  </article>
</template>

<script lang="ts">
import Vue from "vue";
import SectionTitle from "@/components/layout/SectionTitle.vue";

export default Vue.extend({
  name: "FAQ",
  components: {
    SectionTitle
  }
});
</script>

<style scoped></style>

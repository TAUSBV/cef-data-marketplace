package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.taus.data.language.api.model.search.UUIDUtil;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationVariant {
  UUID uid;
  String value;
  long wordCount;
  long hash;
  Language language;

  public TranslationVariant(final byte[] uid, final String value, final long wordCount, final long hash, final Language language) {
    this.uid = UUIDUtil.fromBytes(uid);
    this.value = value;
    this.wordCount = wordCount;
    this.hash = hash;
    this.language = language;
  }
}

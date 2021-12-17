package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.taus.data.language.api.model.search.UUIDUtil;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationUnit {

  Long ownerId;
  UUID documentUid;
  Long id;
  UUID uid;
  Long hash;
  Long sequence;
  TranslationVariant source;
  TranslationVariant target;
  Domain domain;
  ContentType contentType;

  public TranslationUnit(final UUID documentUid,
                         final Long id,
                         final UUID uid,
                         final Long hash,
                         final Long sequence,
                         final TranslationVariant source,
                         final TranslationVariant target) {
    this.documentUid = documentUid;
    this.id = id;
    this.uid = uid;
    this.hash = hash;
    this.sequence = sequence;
    this.source = source;
    this.target = target;
  }

  public TranslationUnit(final byte[] documentUid,
                         final Long id,
                         final byte[] uid,
                         final Long hash,
                         final Long sequence,
                         final TranslationVariant source,
                         final TranslationVariant target) {
    this.documentUid = UUIDUtil.fromBytes(documentUid);
    this.id = id;
    this.uid = UUIDUtil.fromBytes(uid);
    this.hash = hash;
    this.sequence = sequence;
    this.source = source;
    this.target = target;
  }

  public TranslationUnit(final Long ownerId,
                         final byte[] documentUid,
                         final Long id,
                         final byte[] uid,
                         final Long hash,
                         final Long sequence,
                         final TranslationVariant source,
                         final TranslationVariant target,
                         final Domain domain,
                         final ContentType contentType) {
    this.ownerId = ownerId;
    this.documentUid = UUIDUtil.fromBytes(documentUid);
    this.id = id;
    this.uid = UUIDUtil.fromBytes(uid);
    this.hash = hash;
    this.sequence = sequence;
    this.source = source;
    this.target = target;
    this.domain = domain;
    this.contentType = contentType;
  }

}

package net.taus.data.marketplace.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentPriceValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentPrice {


  String message() default "Must be a decimal number between 0.0005 and 0.15.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

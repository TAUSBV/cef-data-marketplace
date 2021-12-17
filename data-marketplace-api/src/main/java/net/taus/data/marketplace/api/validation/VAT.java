package net.taus.data.marketplace.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VATValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface VAT {


  String message() default "VAT given is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

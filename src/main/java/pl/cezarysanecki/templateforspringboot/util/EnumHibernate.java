package pl.cezarysanecki.templateforspringboot.util;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumHibernateValidator.class)
public @interface EnumHibernate {
  String message() default "{jakarta.validation.constraints.enum.message.default}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

class EnumHibernateValidator implements ConstraintValidator<EnumHibernate, CharSequence> {

  private String message;

  @Override
  public void initialize(EnumHibernate constraintAnnotation) {
    String acceptedValues = Stream.of(DefaultEnum.values())
        .map(Enum::name)
        .collect(Collectors.joining(", "));

    message = String.format("{jakarta.validation.constraints.enum.message}: %s", acceptedValues);
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if (DefaultEnum.contains(value.toString())) {
      return true;
    }
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    return false;
  }
}

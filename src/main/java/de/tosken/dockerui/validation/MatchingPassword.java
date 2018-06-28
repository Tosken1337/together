package de.tosken.dockerui.validation;

import de.tosken.dockerui.validation.validator.MatchingPasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * dockerui
 * User: Sebastian
 * Date: 17.06.2018
 * Time: 19:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchingPasswordValidator.class)
public @interface MatchingPassword {
    String message() default "Password's must match";
    Class[] groups() default {};
    Class[] payload() default {};
}

package de.tosken.dockerui.validation.validator;

import de.tosken.dockerui.validation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * dockerui
 * User: Sebastian
 * Date: 10.06.2018
 * Time: 11:05
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(value);
    }
}

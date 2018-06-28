package de.tosken.dockerui.validation.validator;

import de.tosken.dockerui.validation.MatchingPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * dockerui
 * User: Sebastian
 * Date: 17.06.2018
 * Time: 19:48
 */
public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, PasswordHolder> {
    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(PasswordHolder value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getMatchingPassword());
    }
}

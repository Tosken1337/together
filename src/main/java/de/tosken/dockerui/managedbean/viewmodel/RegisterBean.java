package de.tosken.dockerui.managedbean.viewmodel;

import de.tosken.dockerui.managedbean.user.UserBean;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.service.UserService;
import de.tosken.dockerui.validation.MatchingPassword;
import de.tosken.dockerui.validation.ValidEmail;
import de.tosken.dockerui.validation.group.PasswordValidationGroup;
import de.tosken.dockerui.validation.validator.PasswordHolder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * dockerui
 * User: Sebastian
 * Date: 08.06.2018
 * Time: 19:49
 */

@Named
@ViewScoped
@Log4j2
public class RegisterBean {
    @Getter
    @Setter
    private RegistrationForm registration;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBean userBean;

    public RegisterBean() {
        this.registration = new RegistrationForm();
    }

    public void onRequest() throws IOException {
        if (userBean.getCurrentUser().isPresent()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        }
    }

    public String onRegister() {
        System.out.println("Perform registration");

        // Check if the email is already registered
        final User user = userService.findByEmail(registration.getEmail());
        if (user != null) {
            Messages.addError("registrationForm:email", "eMail already registered");
            return null;
        }

        try {
            userService.registerNewUser(getRegistration().username, getRegistration().getEmail(), getRegistration().getPassword());
            return "/register.xhtml?faces-redirect=true&success=1";
        } catch (Exception e) {
            log.error("Unable to register user", e);
            Messages.addGlobalError("Unable to register!");
            return null;
        }
    }

    @Getter
    @Setter
    @MatchingPassword(groups = PasswordValidationGroup.class)
    public static class RegistrationForm implements PasswordHolder, Cloneable {

        @Length(min = 6, max = 64, message = "Username should have a minimum of 6 characters")
        private String username;

        @ValidEmail(message = "Not a valid email")
        private String email;

        @Size(min = 8, max = 16, message = "Password length not between 8 and 16 characters", groups = PasswordValidationGroup.class)
        private String password;

        @Size(min = 8, max = 16, message = "Password do not match", groups = PasswordValidationGroup.class)
        private String matchingPassword;

        @Override
        public Object clone() {
            final RegistrationForm clone = new RegistrationForm();
            clone.setPassword(this.getPassword());
            clone.setMatchingPassword(this.getMatchingPassword());
            clone.setEmail(this.getEmail());
            clone.setUsername(this.getUsername());
            return clone;
        }
    }
}

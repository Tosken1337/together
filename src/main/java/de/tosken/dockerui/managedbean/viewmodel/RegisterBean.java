package de.tosken.dockerui.managedbean.viewmodel;

import de.tosken.dockerui.managedbean.user.UserBean;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.service.UserService;
import de.tosken.dockerui.validation.ValidEmail;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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

        // Check if passwords are matching
        if (!registration.getPassword().equals(registration.getMatchingPassword())) {
            Messages.addError("registrationForm.matchingPassword", "Passwords don't not match");
            return null;
        }

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
    public static class RegistrationForm {

        @Length(min = 6, max = 128)
        private String username;

        @ValidEmail
        private String email;

        @Length(min = 8, max = 32)
        private String password;

        @Length(min = 8, max = 32)
        private String matchingPassword;
    }
}

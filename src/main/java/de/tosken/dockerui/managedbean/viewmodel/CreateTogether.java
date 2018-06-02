package de.tosken.dockerui.managedbean.viewmodel;

import com.google.common.base.Strings;
import de.tosken.dockerui.auth.AuthenticatedUser;
import de.tosken.dockerui.managedbean.user.UserBean;
import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.TogetherItem;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.persistance.util.TogetherUtils;
import de.tosken.dockerui.service.TogetherService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * dockerui
 * User: Sebastian
 * Date: 26.05.2018
 * Time: 15:27
 */
@Named
@ViewScoped
public class CreateTogether {

    private List<TogetherItem> items;

    private String title;

    private boolean extendable;

    @Future(message = "Expire date must be in the future")
    private Date expireDate;

    @Inject
    private UserBean userBean;

    @Inject
    private TogetherService togetherService;

    @PostConstruct
    public void init() {
        items = IntStream.range(1, 8).mapToObj(value -> new TogetherItem()).collect(Collectors.toList());
        expireDate = Date.from(LocalDateTime.now().plusDays(4).atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<TogetherItem> getItems() {
        return items;
    }

    public void setItems(List<TogetherItem> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String save() {
        final long entries = items.stream().filter(item -> !Strings.isNullOrEmpty(item.getTitle())).count();
        if (entries == 0) {
            final FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Together is empty", "Please add at least one item"));
            return null;
        } else {
            final Together together = saveTogether();
            return "create?faces-redirect=true&createdRef=" + together.getRef();
        }
    }

    private Together saveTogether() {
        final Optional<AuthenticatedUser> currentUser = userBean.getCurrentUser();
        final User user = (User) currentUser.get().getDetails();

        final Together together = new Together();
        together.setRef(TogetherUtils.generateRef());
        together.setExpires(getExpireDate());
        together.setTitle(getTitle());
        together.setExtendable(getExtendable());
        together.setCreator(user);


        getItems().stream()
                .filter(item -> !Strings.isNullOrEmpty(item.getTitle()))
                .forEach(item -> {
                    item.setCreator(user);
                    together.addItem(item);
                });

        return togetherService.save(together);
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public boolean getExtendable() {
        return extendable;
    }

    public void addRow() {
        items.add(new TogetherItem());
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}

package de.tosken.dockerui.managedbean.viewmodel;

import de.tosken.dockerui.managedbean.user.UserBean;
import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.TogetherItem;
import de.tosken.dockerui.service.TogetherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

/**
 * dockerui
 * User: Sebastian
 * Date: 02.06.2018
 * Time: 18:49
 */

@ManagedBean
@ViewScoped
@Log4j2
public class ViewTogether {

    private String togetherRef;

    private Together together;

    @Inject
    private TogetherService togetherService;

    @Inject
    private UserBean userBean;

    @NotBlank(message = "New item should not be empty")
    private String newEntryValue;


    public void onInit() {
        refreshData();
    }

    public void onEnroll(final TogetherItem item) {
        try {
            togetherService.enrollForItem(item, userBean.getCurrentUserModel());
        } catch (ObjectOptimisticLockingFailureException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "", "List has already changed and was updated now!"));
        } finally {
            refreshData();
        }
    }

    public void onEnrollOut(final TogetherItem item) {
        try {
            togetherService.enrollOutForItem(item, userBean.getCurrentUserModel());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "", "List has already changed and was updated now!"));
        } finally {
            refreshData();
        }
    }

    public void onAddNewItem() {
        togetherService.addNewItem(getTogether(), userBean.getCurrentUserModel(), newEntryValue);
        newEntryValue = null;
//        refreshData();
    }

    private void refreshData() {
        together = togetherService.findByRef(getTogetherRef());
    }

    public void setTogetherRef(String togetherRef) {
        this.togetherRef = togetherRef;
    }

    public String getTogetherRef() {
        return togetherRef;
    }

    public boolean isValidRef() {
        return together != null;
    }

    public Together getTogether() {
        return together;
    }

    public String getNewEntryValue() {
        return newEntryValue;
    }

    public void setNewEntryValue(String newEntryValue) {
        this.newEntryValue = newEntryValue;
    }
}

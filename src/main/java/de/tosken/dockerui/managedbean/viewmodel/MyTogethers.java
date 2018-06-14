package de.tosken.dockerui.managedbean.viewmodel;

import de.tosken.dockerui.managedbean.user.UserBean;
import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.service.TogetherService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * dockerui
 * User: Sebastian
 * Date: 02.06.2018
 * Time: 18:49
 */

@ManagedBean
@ViewScoped
public class MyTogethers {

    @Inject
    private TogetherService togetherService;

    @Inject
    private UserBean userBean;

    private List<Together> togetherList;

    @PostConstruct
    public void onInit() {
        load();
    }

    public List<Together> getTogetherList() {
        return togetherList;
    }

    public String delete(final Together together) {
        togetherService.delete(together);
        load();
        return null;
    }

    private void load() {
        togetherList = togetherService.findByUser(userBean.getCurrentUserModel());
    }

    public String open(final Together together) {
        return "together.xhtml?faces-redirect=true&together=" + together.getRef();
    }
}

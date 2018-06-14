package de.tosken.dockerui.managedbean.viewmodel;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.service.TogetherService;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 * dockerui
 * User: Sebastian
 * Date: 02.06.2018
 * Time: 18:49
 */

@ManagedBean
@ViewScoped
public class ViewTogether {

    private String togetherRef;

    private Together together;

    @Inject
    private TogetherService togetherService;


    public void onInit() {
        together = togetherService.findByRef(togetherRef);
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
}

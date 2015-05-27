package org.sample.controller;

import org.sample.model.Monument;
import org.sample.model.MonumentList;
import org.sample.service.InitService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MonumentMB implements Serializable {

    @Inject
    private InitService initService;

    private List<Monument> monuments;

    @PostConstruct
    public void init() {
        MonumentList monumentList = initService.getMonumentList();
        monuments = monumentList.getResult();
    }

    /* GETTER & SETTER */

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }
}

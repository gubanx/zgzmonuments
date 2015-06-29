package org.sample.controller;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.sample.model.Monument;
import org.sample.model.MonumentList;
import org.sample.service.InitService;
import org.sample.service.VisitService;

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

    @Inject
    private VisitService visitService;

    private List<Monument> monuments;
    private Monument selectedMonument;

    private MapModel mapModel;
    private String centerMap = "41.6516912,-0.8949809";
    private String zoom = "13";

    private Integer hits;

    @PostConstruct
    public void init() {
        MonumentList monumentList = initService.getMonumentList();
        monuments = monumentList.getResult();
        mapModel = new DefaultMapModel();
    }


    public void onSelectMonument(Monument monument) {
        selectedMonument = monument;
        String longitude = selectedMonument.getGeometry().getCoordinates().get(1);
        String latitude = selectedMonument.getGeometry().getCoordinates().get(0);
        centerMap = longitude + "," + latitude;
        LatLng coordinate = new LatLng(Double.parseDouble(longitude), Double.parseDouble(latitude));
        mapModel.getMarkers().clear();
        mapModel.addOverlay(new Marker(coordinate, selectedMonument.getTitle()));
        zoom = "17";

        hits = visitService.addVisit(monument.getId());
    }

    /* GETTER & SETTER */

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }

    public Monument getSelectedMonument() {
        return selectedMonument;
    }

    public void setSelectedMonument(Monument selectedMonument) {
        this.selectedMonument = selectedMonument;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public String getCenterMap() {
        return centerMap;
    }

    public void setCenterMap(String centerMap) {
        this.centerMap = centerMap;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
}

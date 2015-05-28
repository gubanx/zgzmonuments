package org.sample.service;

import org.sample.model.MonumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Startup
@Singleton
public class InitService {

    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);
    private static final String URLSTRING = "http://www.zaragoza.es/api/recurso/turismo/monumento?start=0&rows=50&distance=500";

    private MonumentList monumentList = new MonumentList();

    @PostConstruct
    private void init() {
        LOG.info("Inicio de la aplicación");
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URLSTRING);
        monumentList = webTarget.request(MediaType.APPLICATION_JSON).get(MonumentList.class);
    }

    public MonumentList getMonumentList() {
        return monumentList;
    }
}

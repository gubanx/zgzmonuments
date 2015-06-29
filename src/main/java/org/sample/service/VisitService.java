package org.sample.service;

import org.sample.model.Visit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class VisitService extends BaseService<Visit> {

    @PersistenceContext
    protected EntityManager entityManager;

    public VisitService() {
        super(Visit.class);
    }

    public int addVisit(String monumentId) {
        Visit visit = find(monumentId);
        if (visit != null) {
            visit.setHits(visit.getHits() + 1);
            visit.setLastVisit(new Date());
            getEntityManager().merge(visit);
        } else {
            visit = new Visit();
            visit.setId(monumentId);
            visit.setHits(1);
            visit.setLastVisit(new Date());
            create(visit);
        }
        return visit.getHits();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

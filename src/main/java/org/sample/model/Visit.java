package org.sample.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "VISITS")
public class Visit {

    @Id
    @Column(name = "MONUMENT_ID")
    private String id;
    private int hits;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_VISIT")
    private Date lastVisit;

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((id == null) ? 0 : id.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Visit other = (Visit) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* GETTER & SETTER */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }
}

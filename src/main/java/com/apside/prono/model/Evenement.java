package com.apside.prono.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "evenement_seq", allocationSize = 1)
public class Evenement {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evenement_seq")
    private long id;

    private String label;
    private Date eventDate;
    private Date openDate;
    private Date closeDate;
    private double coeff;

    @ManyToOne
    private Contest contest;

    public Evenement(long id, String label, Date eventDate, Date openDate, Date closeDate, double coeff, Contest contest) {
        this.id = id;
        this.label = label;
        this.eventDate = eventDate;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.coeff = coeff;
        this.contest = contest;
    }

    public Evenement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}

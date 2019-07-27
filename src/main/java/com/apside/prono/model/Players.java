package com.apside.prono.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "players_seq", allocationSize = 1)
public class Players {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="players_seq")
    private long id;

    private String firstName;
    private String lastName;
    private String mail;
    private Date subscriptionDate;

    public Players(long id, String firstName, String lastName, String mail, Date subscriptionDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.subscriptionDate = subscriptionDate;
    }

    public Players() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}

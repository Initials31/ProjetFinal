package com.apside.prono.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "contest_seq", allocationSize = 1)
public class Contest {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contest_seq")
    private long id;

    private String label;

    public Contest(long id, String label){
            this.id = id;
            this.label = label;
    }

    public Contest() {
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
}

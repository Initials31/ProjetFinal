package com.apside.prono.errors;

public class ContestDonneeNulle extends PronosException{

    private static final long serialVersionUID = 1L;

    public ContestDonneeNulle() {
        super("Le label ne peut pas être null");
    }
}

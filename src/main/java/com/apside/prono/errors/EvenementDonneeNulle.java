package com.apside.prono.errors;

public class EvenementDonneeNulle extends PronosException {

    private static final long serialVersionUID = 1L;

    public EvenementDonneeNulle() {
        super("Aucune donnée ne peut être nulle !");
    }
}

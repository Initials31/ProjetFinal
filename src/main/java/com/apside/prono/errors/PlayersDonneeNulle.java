package com.apside.prono.errors;

public class PlayersDonneeNulle extends PronosException {

    private static final long serialVersionUID = 1L;

    public PlayersDonneeNulle() {
        super("Aucune donnée ne peut être nulle !");
    }
}

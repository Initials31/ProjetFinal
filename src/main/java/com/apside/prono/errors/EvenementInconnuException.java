package com.apside.prono.errors;

public class EvenementInconnuException extends PronosException {


    private static final long serialVersionUID = 1L;

    public EvenementInconnuException(long idEvenement) {
        super("L'évènement avec l'ID " + idEvenement + " n'existe pas !");
    }
}

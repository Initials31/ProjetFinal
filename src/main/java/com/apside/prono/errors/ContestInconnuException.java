package com.apside.prono.errors;

public class ContestInconnuException extends PronosException {

    private static final long serialVersionUID = 1L;

    public ContestInconnuException(long idContest) {
        super("Le concours avec l'ID " + idContest + " n'existe pas !");
    }
}

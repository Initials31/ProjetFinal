package com.apside.prono.errors;

public class PlayersInconnuException extends PronosException{

    private static final long serialVersionUID = 1L;

    public PlayersInconnuException(long idPlayers) {
        super("Le players avec l'ID " + idPlayers + " n'existe pas !");
    }
}

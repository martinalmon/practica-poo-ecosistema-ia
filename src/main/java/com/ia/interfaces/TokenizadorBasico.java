package com.ia.interfaces;

public class TokenizadorBasico implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {
        return parrafo.split(" ");
    }
}
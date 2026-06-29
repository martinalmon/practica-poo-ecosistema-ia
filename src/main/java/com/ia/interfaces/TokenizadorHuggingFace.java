package com.ia.interfaces;

public class TokenizadorHuggingFace implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {
        return parrafo
                .toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .split(" ");
    }
}
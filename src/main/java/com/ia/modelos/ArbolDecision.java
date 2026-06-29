package com.ia.modelos;

public final class ArbolDecision extends ModeloIAAbstraccion {

    private final int profundidadMaxima;

    public ArbolDecision(String nombre, double tasaAprendizaje, int profundidadMaxima) {
        super(nombre, tasaAprendizaje);
        this.profundidadMaxima = profundidadMaxima;
    }

    public int getProfundidadMaxima() {
        return profundidadMaxima;
    }

    @Override
    public void entrenar() {
        incrementarEpocas();

        double incremento = getTasaAprendizaje() * (profundidadMaxima / 2.0);
        aumentarPrecision(incremento);
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Profundidad maxima: " + profundidadMaxima);
        System.out.println("-----------------------------------");
    }
}
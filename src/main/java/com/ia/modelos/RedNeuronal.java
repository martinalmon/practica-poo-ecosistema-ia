package com.ia.modelos;

public final class RedNeuronal extends ModeloIAAbstraccion {

    private final int capasOcultas;

    public RedNeuronal(String nombre, double tasaAprendizaje, int capasOcultas) {
        super(nombre, tasaAprendizaje);
        this.capasOcultas = capasOcultas;
    }

    public int getCapasOcultas() {
        return capasOcultas;
    }

    @Override
    public void entrenar() {
        incrementarEpocas();

        double incremento = getTasaAprendizaje() * capasOcultas;
        aumentarPrecision(incremento);
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Capas ocultas: " + capasOcultas);
        System.out.println("-----------------------------------");
    }
}
package com.ia.modelos;

public final class ModeloRegresion extends ModeloIAAbstraccion {

    private final double coeficienteRegularizacion;

    public ModeloRegresion(String nombre, double tasaAprendizaje, double coeficienteRegularizacion) {
        super(nombre, tasaAprendizaje);
        this.coeficienteRegularizacion = coeficienteRegularizacion;
    }

    public double getCoeficienteRegularizacion() {
        return coeficienteRegularizacion;
    }

    @Override
    public void entrenar() {
        incrementarEpocas();

        double incremento = (getTasaAprendizaje() * 10) - coeficienteRegularizacion;
        aumentarPrecision(incremento);
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Coeficiente de regularizacion: " + coeficienteRegularizacion);
        System.out.println("-----------------------------------");
    }
}
package com.ia.modelos;

public class ModeloRegresion extends ModeloIA {

    private double coeficienteRegularizacion;

    public ModeloRegresion(String nombre, double tasaAprendizaje, double coeficienteRegularizacion) {
        super(nombre, tasaAprendizaje);
        this.coeficienteRegularizacion = coeficienteRegularizacion;
    }

    public double getCoeficienteRegularizacion() {
        return coeficienteRegularizacion;
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Coeficiente de regularizacion: " + coeficienteRegularizacion);
        System.out.println("-----------------------------------");
    }
}
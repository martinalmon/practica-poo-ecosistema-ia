package com.ia.modelos;

public abstract class ModeloIAAbstraccion {

    private String nombre;
    private double precision;
    private int epocasEntrenadas;
    private double tasaAprendizaje;

    public ModeloIAAbstraccion(String nombre, double tasaAprendizaje) {
        this.nombre = nombre;
        this.precision = 50.0;
        this.epocasEntrenadas = 0;
        setTasaAprendizaje(tasaAprendizaje);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecision() {
        return precision;
    }

    public int getEpocasEntrenadas() {
        return epocasEntrenadas;
    }

    public double getTasaAprendizaje() {
        return tasaAprendizaje;
    }

    public void setTasaAprendizaje(double tasaAprendizaje) {
        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
            System.out.println("Tasa de aprendizaje actualizada correctamente para " + nombre + ": " + tasaAprendizaje);
        } else {
            System.out.println("Advertencia: tasa de aprendizaje invalida para " + nombre + ". Se mantiene el valor anterior: " + this.tasaAprendizaje);
        }
    }

    protected void incrementarEpocas() {
        this.epocasEntrenadas++;
    }

    protected void aumentarPrecision(double incremento) {
        this.precision += incremento;

        if (this.precision > 100.0) {
            this.precision = 100.0;
        }
    }

    public abstract void entrenar();

    public void mostrarMetricas() {
        System.out.println("Modelo: " + nombre);
        System.out.println("Precision: " + precision + "%");
        System.out.println("Epocas entrenadas: " + epocasEntrenadas);
        System.out.println("Tasa de aprendizaje: " + tasaAprendizaje);
    }
}
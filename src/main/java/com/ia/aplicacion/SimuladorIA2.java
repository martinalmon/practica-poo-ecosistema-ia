package com.ia.aplicacion;

import com.ia.modelos.RedNeuronal;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloRegresion;

public class SimuladorIA {

    public static void main(String[] args) {

        RedNeuronal redNeuronal = new RedNeuronal("RedNeuronal", 0.3, 5);
        ArbolDecision arbolDecision = new ArbolDecision("ArbolDecision", 0.2, 10);
        ModeloRegresion modeloRegresion = new ModeloRegresion("ModeloRegresion", 0.1, 0.01);

        System.out.println("=== Estado inicial de los modelos especializados ===");
        redNeuronal.mostrarMetricas();
        arbolDecision.mostrarMetricas();
        modeloRegresion.mostrarMetricas();

        System.out.println("=== Entrenamiento de los modelos especializados ===");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Epoca de simulacion " + i);

            redNeuronal.entrenar();
            arbolDecision.entrenar();
            modeloRegresion.entrenar();

            redNeuronal.mostrarMetricas();
            arbolDecision.mostrarMetricas();
            modeloRegresion.mostrarMetricas();
        }
    }
}
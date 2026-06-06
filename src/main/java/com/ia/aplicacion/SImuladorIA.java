package com.ia.aplicacion;
import com.ia.modelos.ModeloIA;

import com.ia.modelos.ModeloIA;

public class SImuladorIA {
    public static void main(String[] args) {

        ModeloIA redNeuronal = new ModeloIA("RedNeuronal", 0.3);
        ModeloIA arbolDecision = new ModeloIA("ArbolDecision", 0.2);

        System.out.println("=== Estado inicial de los modelos ===");
        redNeuronal.mostrarMetricas();
        arbolDecision.mostrarMetricas();

        System.out.println("=== Intento de asignar una tasa invalida ===");
        redNeuronal.setTasaAprendizaje(-0.5);
        arbolDecision.setTasaAprendizaje(1.5);

        System.out.println("=== Entrenamiento de los modelos ===");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Epoca de simulacion " + i);

            redNeuronal.entrenar();
            arbolDecision.entrenar();

            redNeuronal.mostrarMetricas();
            arbolDecision.mostrarMetricas();
        }
    }
}
}

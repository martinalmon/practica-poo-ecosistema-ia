package com.ia.aplicacion;

import com.ia.modelos.ModeloIAAbstraccion;
import com.ia.modelos.RedNeuronal;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloRegresion;
import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== Validacion de abstraccion ===");
        System.out.println("No se puede instanciar directamente ModeloIAAbstraccion porque es una clase abstracta.");

        // Esta linea no debe descomentarse porque provocaria error de compilacion:
        // ModeloIAAbstraccion modeloGenerico = new ModeloIAAbstraccion("ModeloGenerico", 0.3);

        System.out.println();

        ModeloIAAbstraccion[] modelos = new ModeloIAAbstraccion[3];

        modelos[0] = new RedNeuronal("RedNeuronal", 0.3, 5);
        modelos[1] = new ArbolDecision("ArbolDecision", 0.2, 10);
        modelos[2] = new ModeloRegresion("ModeloRegresion", 0.1, 0.01);

        System.out.println("=== Entrenamiento usando referencias abstractas ===");

        for (ModeloIAAbstraccion modelo : modelos) {
            modelo.entrenar();
            modelo.mostrarMetricas();
        }

        System.out.println();
        System.out.println("=== Procesamiento de texto con interfaz Tokenizador ===");

        String texto = "La inteligencia artificial aprende de los datos.";

        Tokenizador tokenizadorBasico = new TokenizadorBasico();
        Tokenizador tokenizadorHuggingFace = new TokenizadorHuggingFace();

        System.out.println("Tokenizador basico:");
        mostrarTokens(tokenizadorBasico.dividirTexto(texto));

        System.out.println("Tokenizador HuggingFace:");
        mostrarTokens(tokenizadorHuggingFace.dividirTexto(texto));
    }

    public static void mostrarTokens(String[] tokens) {
        for (String token : tokens) {
            System.out.println("- " + token);
        }
        System.out.println("-----------------------------------");
    }
}
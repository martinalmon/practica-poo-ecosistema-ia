package com.ia.aplicacion;

import com.ia.modelos.ModeloIAAbstraccion;
import com.ia.modelos.RedNeuronal;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloRegresion;

import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== Fase 6: Gestion dinamica con colecciones ===");
        System.out.println();

        List<ModeloIAAbstraccion> inventarioModelos = new ArrayList<>();

        inventarioModelos.add(new RedNeuronal("RedNeuronal", 0.3, 5));
        inventarioModelos.add(new ArbolDecision("ArbolDecision", 0.2, 10));
        inventarioModelos.add(new ModeloRegresion("ModeloRegresion", 0.1, 0.01));

        System.out.println("=== Inventario dinamico de modelos ===");

        for (ModeloIAAbstraccion modelo : inventarioModelos) {
            modelo.entrenar();
            modelo.entrenar();
            modelo.mostrarMetricas();
        }

        double umbralPrecision = 51.0;

        System.out.println();
        System.out.println("=== Modelos con precision mayor a " + umbralPrecision + "% ===");

        for (ModeloIAAbstraccion modelo : inventarioModelos) {
            if (modelo.getPrecision() > umbralPrecision) {
                System.out.println("Modelo aprobado: " + modelo.getNombre()
                        + " | Precision: " + modelo.getPrecision() + "%");
            }
        }

        System.out.println("-----------------------------------");

        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
        catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

        String parrafo = "La inteligencia artificial aprende de los datos.";

        System.out.println();
        System.out.println("=== Catalogo de tokenizadores ===");
        System.out.println("Tokenizadores registrados: " + catalogoTokenizadores.keySet());

        Tokenizador tokenizadorSeleccionado = catalogoTokenizadores.get("HUGGING_FACE");

        System.out.println();
        System.out.println("=== Procesamiento usando tokenizador HUGGING_FACE ===");

        String[] tokens = tokenizadorSeleccionado.dividirTexto(parrafo);

        for (String token : tokens) {
            System.out.println("- " + token);
        }

        System.out.println("-----------------------------------");

        Tokenizador tokenizadorBasico = catalogoTokenizadores.get("BASICO");

        System.out.println();
        System.out.println("=== Procesamiento usando tokenizador BASICO ===");

        String[] tokensBasicos = tokenizadorBasico.dividirTexto(parrafo);

        for (String token : tokensBasicos) {
            System.out.println("- " + token);
        }

        System.out.println("-----------------------------------");
    }
}
package com.ia.aplicacion;

import com.ia.excepciones.IAComponentException;
import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloIAAbstraccion;
import com.ia.modelos.ModeloRegresion;
import com.ia.modelos.RedNeuronal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== Fase 7: Manejo de excepciones personalizadas ===");
        System.out.println();

        try {
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

            System.out.println();
            System.out.println("=== Prueba de error con tasa invalida ===");

            RedNeuronal modeloInvalido = new RedNeuronal("RedNeuronalInvalida", -0.5, 4);
            inventarioModelos.add(modeloInvalido);

        } catch (IAComponentException error) {
            System.out.println("Excepcion capturada correctamente:");
            System.out.println(error.getMessage());
        } finally {
            System.out.println("Auditoria de modelos finalizada correctamente.");
            System.out.println("-----------------------------------");
        }

        try {
            Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

            catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
            catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

            String parrafo = "La inteligencia artificial aprende de los datos.";

            System.out.println();
            System.out.println("=== Catalogo de tokenizadores ===");
            System.out.println("Tokenizadores registrados: " + catalogoTokenizadores.keySet());

            Tokenizador tokenizadorSeleccionado = obtenerTokenizador(catalogoTokenizadores, "HUGGING_FACE");

            System.out.println();
            System.out.println("=== Procesamiento usando tokenizador HUGGING_FACE ===");

            String[] tokens = tokenizadorSeleccionado.dividirTexto(parrafo);

            for (String token : tokens) {
                System.out.println("- " + token);
            }

            System.out.println();
            System.out.println("=== Prueba de error con tokenizador inexistente ===");

            Tokenizador tokenizadorInexistente = obtenerTokenizador(catalogoTokenizadores, "OPEN_AI");
            String[] tokensError = tokenizadorInexistente.dividirTexto(parrafo);

            for (String token : tokensError) {
                System.out.println("- " + token);
            }

        } catch (IAComponentException error) {
            System.out.println("Excepcion capturada correctamente:");
            System.out.println(error.getMessage());
        } finally {
            System.out.println("Auditoria de tokenizadores finalizada correctamente.");
            System.out.println("-----------------------------------");
        }
    }

    public static Tokenizador obtenerTokenizador(Map<String, Tokenizador> catalogoTokenizadores, String clave) {
        Tokenizador tokenizador = catalogoTokenizadores.get(clave);

        if (tokenizador == null) {
            throw new IAComponentException(
                    "Error: El tokenizador solicitado con la clave '" + clave + "' no esta registrado en el catalogo."
            );
        }

        return tokenizador;
    }
}
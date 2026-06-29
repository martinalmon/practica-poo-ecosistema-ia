package com.ia.aplicacion;

import com.ia.dto.PromptInput;
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

    private static final String TOKENIZADOR_BASICO = "BASICO";
    private static final String TOKENIZADOR_HUGGING_FACE = "HUGGING_FACE";
    private static final String TOKENIZADOR_NO_REGISTRADO = "OPEN_AI";

    public static void main(String[] args) {
        System.out.println("=== Fase 8: Java 17 y auditoria de calidad ===");
        System.out.println();

        ejecutarSimulacionModelos();
        ejecutarProcesamientoTexto();
    }

    private static void ejecutarSimulacionModelos() {
        try {
            List<ModeloIAAbstraccion> inventarioModelos = crearInventarioModelos();

            System.out.println("=== Entrenamiento con jerarquia sealed ===");

            for (ModeloIAAbstraccion modelo : inventarioModelos) {
                modelo.entrenar();
                modelo.entrenar();
                modelo.mostrarMetricas();
            }

            System.out.println("=== Prueba controlada de excepcion ===");
            inventarioModelos.add(new RedNeuronal("RedNeuronalInvalida", -0.5, 3));

        } catch (IAComponentException error) {
            System.out.println("Excepcion capturada correctamente:");
            System.out.println(error.getMessage());
        } finally {
            System.out.println("Auditoria de modelos finalizada.");
            System.out.println("-----------------------------------");
        }
    }

    private static List<ModeloIAAbstraccion> crearInventarioModelos() {
        List<ModeloIAAbstraccion> inventarioModelos = new ArrayList<>();

        inventarioModelos.add(new RedNeuronal("RedNeuronal", 0.3, 5));
        inventarioModelos.add(new ArbolDecision("ArbolDecision", 0.2, 10));
        inventarioModelos.add(new ModeloRegresion("ModeloRegresion", 0.1, 0.01));

        return inventarioModelos;
    }

    private static void ejecutarProcesamientoTexto() {
        try {
            Map<String, Tokenizador> catalogoTokenizadores = crearCatalogoTokenizadores();

            PromptInput promptInput = new PromptInput(
                    "Eres un asistente de inteligencia artificial.",
                    "La inteligencia artificial aprende de los datos."
            );

            System.out.println();
            System.out.println("=== Prompt recibido mediante record ===");
            System.out.println(promptInput);

            Tokenizador tokenizador = seleccionarTokenizador(
                    TOKENIZADOR_HUGGING_FACE,
                    catalogoTokenizadores
            );

            System.out.println();
            System.out.println("=== Procesamiento usando switch expression ===");
            mostrarTokens(tokenizador.dividirTexto(promptInput.userQuery()));

            System.out.println("=== Prueba controlada con tokenizador inexistente ===");
            seleccionarTokenizador(TOKENIZADOR_NO_REGISTRADO, catalogoTokenizadores);

        } catch (IAComponentException error) {
            System.out.println("Excepcion capturada correctamente:");
            System.out.println(error.getMessage());
        } finally {
            System.out.println("Auditoria de tokenizadores finalizada.");
            System.out.println("-----------------------------------");
        }
    }

    private static Map<String, Tokenizador> crearCatalogoTokenizadores() {
        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        catalogoTokenizadores.put(TOKENIZADOR_BASICO, new TokenizadorBasico());
        catalogoTokenizadores.put(TOKENIZADOR_HUGGING_FACE, new TokenizadorHuggingFace());

        return catalogoTokenizadores;
    }

    private static Tokenizador seleccionarTokenizador(
            String clave,
            Map<String, Tokenizador> catalogoTokenizadores
    ) {
        Tokenizador tokenizador = switch (clave) {
            case TOKENIZADOR_BASICO -> catalogoTokenizadores.get(TOKENIZADOR_BASICO);
            case TOKENIZADOR_HUGGING_FACE -> catalogoTokenizadores.get(TOKENIZADOR_HUGGING_FACE);
            default -> throw new IAComponentException(
                    "Error: El tokenizador solicitado con la clave '" + clave
                            + "' no esta registrado en el catalogo."
            );
        };

        if (tokenizador == null) {
            throw new IAComponentException(
                    "Error: El tokenizador solicitado con la clave '" + clave
                            + "' no esta disponible en memoria."
            );
        }

        return tokenizador;
    }

    private static void mostrarTokens(String[] tokens) {
        for (String token : tokens) {
            System.out.println("- " + token);
        }

        System.out.println("-----------------------------------");
    }
}
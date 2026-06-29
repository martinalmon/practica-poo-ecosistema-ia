# CONCLUSIONES Y LECCIONES APRENDIDAS

## Portada y Datos Generales

**Proyecto:** Simulador de Ecosistema de Inteligencia Artificial
**Asignatura:** Programación Orientada a Objetos
**Fase:** Fase 8 - Java 17, Refactorización y SonarQube
**Repositorio:** practica-poo-ecosistema-ia
**Rama de trabajo:** feature/sonarqube-java17-refactor
**Alumno:** Jose Martin Alvarez Monreal

---

## Resumen Ejecutivo

El proyecto evolucionó desde una estructura inicial básica hasta convertirse en un simulador de inteligencia artificial con una arquitectura más robusta, mantenible y cercana a estándares profesionales de ingeniería de software.

En la Fase 2 se aplicó encapsulación para proteger los atributos internos de los modelos de IA, evitando modificaciones directas sobre valores críticos como la precisión, las épocas entrenadas y la tasa de aprendizaje. En la Fase 3 se implementó herencia mediante clases especializadas como `RedNeuronal`, `ArbolDecision` y `ModeloRegresion`, reutilizando la lógica general del modelo base.

Posteriormente, se integraron abstracciones mediante una clase abstracta y una interfaz de tokenización, permitiendo separar contratos de implementación. En la Fase 6 se sustituyeron arreglos estáticos por colecciones dinámicas como `List` y `Map`, lo que permitió administrar modelos y tokenizadores de forma más flexible. En la Fase 7 se incorporó manejo de excepciones personalizadas para evitar que configuraciones inválidas provocaran fallos no controlados.

Finalmente, en la Fase 8 se modernizó el proyecto a Java 17 mediante `records`, `sealed classes` y `switch expressions`. También se preparó el código para análisis estático con SonarQube, buscando reducir deuda técnica y mejorar la calidad general del sistema.

---

## Reporte de Calidad SonarQube

### Antes del refactor

En el primer análisis de SonarQube se identificaron oportunidades de mejora relacionadas con duplicación de código, uso de estructuras tradicionales, posibles variables o clases no utilizadas y necesidad de mejorar la claridad del flujo de control.

**Espacio para captura del reporte inicial:**

![Reporte inicial SonarQube](docs/sonarqube_antes.png)

### Después del refactor

Después de aplicar la refactorización a Java 17, el código quedó más organizado, con mejor separación de responsabilidades, uso de estructuras modernas del lenguaje y mejor control de la jerarquía de clases.

**Espacio para captura del Quality Gate aprobado:**

![Quality Gate aprobado](docs/sonarqube_despues.png)

---

## Cuestionario de Evaluación de Lecciones Aprendidas

###  Sobre la Modernización del Lenguaje

La implementación de Java 17 mediante `records` y `sealed classes` optimizó el diseño del simulador porque permitió reducir código repetitivo y controlar mejor la herencia.

El `record` `PromptInput` permitió representar datos de entrada para el tokenizador de forma inmutable. Esto fortalece la encapsulación porque sus valores no pueden modificarse después de ser creados. En comparación con un diseño tradicional de Java 8, no fue necesario escribir manualmente constructores, getters, `equals()`, `hashCode()` o `toString()`.

Por otro lado, las `sealed classes` permitieron restringir qué clases pueden heredar de `ModeloIAAbstraccion`. Esto mejora el control de la jerarquía porque solo `RedNeuronal`, `ArbolDecision` y `ModeloRegresion` pueden extender el modelo abstracto. En un diseño tradicional, cualquier clase podría heredar de la superclase si tuviera acceso, aumentando el riesgo de modelos incompletos o no autorizados.

###  Sobre la Deuda Técnica

En un primer escaneo de SonarQube, los tres problemas más relevantes que pueden aparecer en este tipo de proyecto son:

1. **Duplicación de lógica en el simulador:** Se corrigió separando responsabilidades en métodos como `crearInventarioModelos()`, `crearCatalogoTokenizadores()` y `seleccionarTokenizador()`.
2. **Uso de cadenas repetidas:** Se corrigió declarando constantes como `TOKENIZADOR_BASICO`, `TOKENIZADOR_HUGGING_FACE` y `TOKENIZADOR_NO_REGISTRADO`.
3. **Manejo de errores poco estructurado:** Se corrigió utilizando la excepción personalizada `IAComponentException`, evitando mensajes sueltos y centralizando el control de fallos.

Los principios aplicados fueron separación de responsabilidades, reducción de duplicación, claridad semántica y manejo explícito de errores.

###  Sobre el Flujo de Control y Resiliencia

El manejo de excepciones de la Fase 7 interactúa directamente con la confiabilidad del sistema porque permite capturar fallos esperados, como tasas de aprendizaje inválidas o tokenizadores inexistentes, sin detener bruscamente el programa.

En lugar de dejar bloques `catch` vacíos, el simulador muestra mensajes claros mediante `error.getMessage()`. Esto evita una mala práctica penalizada por herramientas como SonarQube, ya que los errores no se silencian. Además, el bloque `finally` garantiza que las tareas de auditoría se ejecuten tanto si ocurre una excepción como si no ocurre.

###  Sobre la Flexibilidad de las Abstracciones

El uso combinado de colecciones dinámicas y abstracciones polimórficas permitió extender el sistema sin romper la arquitectura base.

La lista `List<ModeloIAAbstraccion>` puede almacenar distintos modelos concretos, como redes neuronales, árboles de decisión y modelos de regresión, tratándolos mediante una referencia común. Esto permite recorrerlos y ejecutar `entrenar()` sin conocer el tipo exacto del objeto.

El mapa `Map<String, Tokenizador>` permitió registrar diferentes estrategias de tokenización y recuperarlas mediante claves semánticas. Gracias a esto, la incorporación de características de Java 17, como `switch expressions`, se hizo sin modificar la lógica interna de los tokenizadores ni romper el flujo principal del simulador.

###  Sobre el Impacto en la Ingeniería

Desde la perspectiva de un Ingeniero en Inteligencia Artificial, es crítico someter un software de simulación o pipelines de LLM a análisis estático antes de producción porque estos sistemas suelen procesar datos externos, configuraciones variables y flujos de información sensibles.

SonarQube ayuda a detectar deuda técnica, errores potenciales, duplicación de código, problemas de mantenibilidad y vulnerabilidades. En un sistema de IA, un error pequeño en el manejo de parámetros, tokens o modelos puede provocar resultados incorrectos, fallos de ejecución o decisiones poco confiables.

Por eso, auditar el código antes del despliegue permite aumentar la seguridad, la mantenibilidad y la confianza en el sistema. Además, promueve buenas prácticas de ingeniería, como código limpio, control de errores, modularidad y trazabilidad técnica.

---

Conclusión General

La evolución del simulador permitió aplicar de forma progresiva los pilares de la programación orientada a objetos y prácticas modernas de ingeniería de software. El proyecto inició con encapsulación básica y terminó con una arquitectura modernizada a Java 17, preparada para análisis de calidad con SonarQube.

El uso de abstracciones, colecciones, excepciones personalizadas, records, sealed classes y switch expressions permitió construir un sistema más robusto, extensible y mantenible.

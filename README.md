📱 IMC Calculator (Calculadora de Índice de Masa Corporal)

Una aplicación sencilla e interactiva desarrollada para Android usando Jetpack Compose. La app permite calcular el Índice de Masa Corporal (IMC) en tiempo real a partir del peso y la altura ingresados por el usuario.

Aprendizajes Clave & Conceptos de Jetpack Compose

Este proyecto fue construido con el objetivo de consolidar la gestión de estado, la validación de entradas en tiempo real y el manejo seguro de datos en Compose:

*Estado y Observabilidad (State y MutableState)**
  * `MutableState`:** Uso de `mutableStateOf()` para crear valores observables que notifican a Compose cuando el peso o la altura cambian.
  * `remember`:** Preservación del estado a través de las recomposiciones de la interfaz gráfica.
  * Delegados de Kotlin:** Implementación de `by remember { mutableStateOf("") }` para un manejo de variables más limpio e intuitivo.

* Reutilización de Componentes & Elevación de Estado
  * Creación de un composable genérico `EditNumberField` para los campos de entrada.
  * Parametrización de propiedades de `TextField` (`keyboardOptions`, `label`, `singleLine`) para adaptar cada input a su propósito.

* Validación en Tiempo Real & Programación Defensiva
  * Manejo Seguro de Nulos: Uso de `.toDoubleOrNull() ?: 0.0` para evitar fallos de la aplicación (*crashes*) al manipular cadenas vacías o textos inválidos.
  * Lógica de Control en Expresiones: Evaluación de condiciones en tiempo real (como validar la cantidad de dígitos ingresados o limitar rangos de peso/IMC) para evitar cálculos prematuros o mostrar resultados desorbitados mientras el usuario escribe.

* Formatos y Presentación de Datos
  * Formateo de cadenas y limitación de decimales para mostrar resultados limpios y legibles.
  * Aplicación de jerarquías tipográficas (`FontWeight`, `sp`) e integración de tablas informativas sobre rangos de composición corporal.

Tecnologías y Herramientas Usadas

* Lenguaje: Kotlin
* UI Framework: Jetpack Compose (Material Design 3)
* IDE: Android Studio
* Manejo de Recursos: Extracción de cadenas de texto mediante `strings.xml`


# 💸 Conversor de Monedas

Este es un proyecto en Java para convertir entre diversas monedas utilizando una API de tasas de cambio en tiempo real. El programa obtiene las tasas de cambio desde una API y permite al usuario realizar conversiones entre diferentes monedas de manera sencilla e interactiva. 🌐💵

## Características ✨

- **Conversión entre monedas populares**: ARS (Peso argentino), BOB (Boliviano), BRL (Real brasileño), CLP (Peso chileno), COP (Peso colombiano) y USD (Dólar estadounidense).
- **Interfaz intuitiva**: El programa proporciona un menú interactivo donde el usuario puede elegir entre las opciones disponibles. ✅
- **Uso de API en tiempo real**: Se conecta a una API para obtener las tasas de cambio actualizadas. 🔄
- **Manejo de errores y registros**: El programa gestiona adecuadamente los errores y utiliza un sistema de log para depurar posibles fallos. 🛠️
- **Modularidad**: El código está estructurado para ser fácil de modificar y entender, con métodos separados para cada tarea. 📦

## Requisitos 📝

Antes de ejecutar el proyecto, asegúrate de tener lo siguiente:

- Java 11 o superior. ☕
- Una clave API de `ExchangeRate-API` para obtener las tasas de cambio. Puedes obtenerla [aquí](https://www.exchangerate-api.com/). 🔑
- La biblioteca Gson para procesar la respuesta JSON. 

## Instalación 🚀

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/FabiCasC/ConversorMonedas.git
   ```

2. **Cambia a la carpeta del proyecto:**
   ```bash
   cd currency-converter
   ```

3. **Agrega tu clave API**:
   - Sustituye `La API` por tu clave personal de la API en el código.

4. **Compila y ejecuta el proyecto:**
   - Si estás usando un IDE como IntelliJ IDEA, abre el proyecto y ejecútalo directamente. 🚀
   - Si usas la terminal, compílalo con:
   
   ```bash
   javac ConversorMonedas.java
   java ConversarMonedas
   ```

## Uso 🖥️

1. **Ejecuta el programa:**
   Una vez que inicies el programa, el sistema mostrará un menú con las opciones de conversión entre monedas.

2. **Selecciona la moneda de origen:**
   Elige la moneda desde la que deseas convertir. Las opciones disponibles son:
   - ARS (Peso argentino)
   - BOB (Boliviano)
   - BRL (Real brasileño)
   - CLP (Peso chileno)
   - COP (Peso colombiano)
   - USD (Dólar estadounidense)

3. **Ingresa la cantidad:**
   El sistema te pedirá que ingreses la cantidad que deseas convertir.

4. **Selecciona la moneda de destino:**
   A continuación, selecciona la moneda a la que deseas convertir la cantidad ingresada.

5. **Resultados:**
   El programa mostrará el monto convertido en la moneda seleccionada.

6. **Realizar más conversiones:**
   Después de cada conversión, el programa te preguntará si deseas hacer otra conversión o salir.

7. **Salir:**
   Si eliges "Salir", el programa terminará.

## Ejemplo de ejecución 👇

```
Tasas de cambio actuales:
1. ARS - Peso argentino: 75.28
2. BOB - Boliviano boliviano: 6.91
3. BRL - Real brasileño: 5.23
4. CLP - Peso chileno: 800.45
5. COP - Peso colombiano: 3900.75
6. USD - Dólar estadounidense: 1.00

¿Qué moneda deseas convertir?
1. ARS - Peso argentino
2. BOB - Boliviano boliviano
3. BRL - Real brasileño
4. CLP - Peso chileno
5. COP - Peso colombiano
6. USD - Dólar estadounidense
7. Salir
Ingrese la opción (1-7): 1

Ingrese la cantidad que desea convertir: 100

Selecciona la moneda a la que deseas convertir:
1. ARS - Peso argentino
2. BOB - Boliviano boliviano
3. BRL - Real brasileño
4. CLP - Peso chileno
5. COP - Peso colombiano
6. USD - Dólar estadounidense
Ingrese la opción (1-6): 2

100.00 ARS equivale a 9.69 BOB

¿Quieres realizar otra conversión? (S/N): S
---------------------------------------------------
```

## Contribuciones 🤝

Si deseas contribuir al proyecto, siéntete libre de abrir un *pull request* o reportar problemas en el [repositorio](https://github.com/FabiCasC/ConversorMonedas.git).

---


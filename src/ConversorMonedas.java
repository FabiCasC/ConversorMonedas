import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConversorMonedas {

    // Logger para manejar los logs
    private static final Logger logger = Logger.getLogger(ConversorMonedas.class.getName());

    // Método para realizar la solicitud a la API y obtener la respuesta
    public static HttpResponse<String> getExchangeRates(String apiKey) throws Exception {
        String urlBase = "https://api.exchangerate-api.com/v4/latest/USD";
        String url = urlBase + "?apikey=" + apiKey;

        // Usar try-with-resources para manejar automáticamente el HttpClient
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("User-Agent", "ConversorMonedasApp/1.0")
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
    }

    // Método para convertir la cantidad usando las tasas de cambio
    public static double convertCurrency(double amount, double fromRate, double toRate) {
        return (amount / fromRate) * toRate;
    }

    // Método para obtener la tasa de una moneda específica
    public static double getRate(String currencyCode, JsonObject rates) {
        if (rates.has(currencyCode)) {
            return rates.get(currencyCode).getAsDouble();
        } else {
            logger.warning("Moneda no encontrada: " + currencyCode);
            return -1;  // Retorna -1 si no se encuentra la moneda
        }
    }

    // Método para mostrar todas las tasas de cambio disponibles
    public static void showExchangeRates(JsonObject rates) {
        System.out.println("\nTasas de cambio actuales:");
        System.out.println("1. ARS - Peso argentino: " + rates.get("ARS").getAsDouble());
        System.out.println("2. BOB - Boliviano boliviano: " + rates.get("BOB").getAsDouble());
        System.out.println("3. BRL - Real brasileño: " + rates.get("BRL").getAsDouble());
        System.out.println("4. CLP - Peso chileno: " + rates.get("CLP").getAsDouble());
        System.out.println("5. COP - Peso colombiano: " + rates.get("COP").getAsDouble());
        System.out.println("6. USD - Dólar estadounidense: " + rates.get("USD").getAsDouble());
    }

    // Método para mostrar el menú de opciones y manejar la entrada del usuario
    public static void showMenu() {
        System.out.println("\n¿Qué moneda deseas convertir?");
        System.out.println("1. ARS - Peso argentino");
        System.out.println("2. BOB - Boliviano boliviano");
        System.out.println("3. BRL - Real brasileño");
        System.out.println("4. CLP - Peso chileno");
        System.out.println("5. COP - Peso colombiano");
        System.out.println("6. USD - Dólar estadounidense");
        System.out.println("7. Salir");
    }

    // Método para preguntar si el usuario quiere continuar o salir
    public static boolean continueConversion(Scanner scanner) {
        System.out.print("\n¿Quieres realizar otra conversión? (S/N): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("S");
    }

    public static void main(String[] args) {
        String apiKey = "_";  // API
        Scanner scanner = new Scanner(System.in);

        try {
            // Realizar la solicitud a la API y obtener la respuesta
            HttpResponse<String> response = getExchangeRates(apiKey);

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

                JsonObject rates = jsonResponse.getAsJsonObject("rates");

                // Mostrar tasas de cambio al inicio
                showExchangeRates(rates);

                // Bucle para el menú
                while (true) {
                    showMenu();

                    // Leer la opción seleccionada por el usuario
                    int option = scanner.nextInt();

                    if (option == 7) {
                        System.out.println("\nGracias por usar el Conversor de Monedas. ¡Hasta luego!");
                        break;
                    }

                    // Leer la cantidad a convertir
                    System.out.print("\nIngrese la cantidad que desea convertir: ");
                    double amount = scanner.nextDouble();

                    double fromRate = 0;
                    double toRate = 0;
                    String fromCurrency = null;
                    String toCurrency = null;

                    // Configurar la moneda de origen según la opción seleccionada
                    switch (option) {
                        case 1:
                            fromCurrency = "ARS";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        case 2:
                            fromCurrency = "BOB";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        case 3:
                            fromCurrency = "BRL";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        case 4:
                            fromCurrency = "CLP";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        case 5:
                            fromCurrency = "COP";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        case 6:
                            fromCurrency = "USD";
                            fromRate = getRate(fromCurrency, rates);
                            break;
                        default:
                            System.out.println("Opción inválida. Intenta nuevamente.");
                            continue;
                    }

                    // Asegurarse de que la tasa sea válida
                    if (fromRate != -1) {
                        // Preguntar a qué moneda convertir
                        System.out.println("\nSelecciona la moneda a la que deseas convertir:");
                        showMenu();

                        int toOption = scanner.nextInt();

                        // Configurar la moneda de destino
                        switch (toOption) {
                            case 1:
                                toCurrency = "ARS";
                                toRate = getRate(toCurrency, rates);
                                break;
                            case 2:
                                toCurrency = "BOB";
                                toRate = getRate(toCurrency, rates);
                                break;
                            case 3:
                                toCurrency = "BRL";
                                toRate = getRate(toCurrency, rates);
                                break;
                            case 4:
                                toCurrency = "CLP";
                                toRate = getRate(toCurrency, rates);
                                break;
                            case 5:
                                toCurrency = "COP";
                                toRate = getRate(toCurrency, rates);
                                break;
                            case 6:
                                toCurrency = "USD";
                                toRate = getRate(toCurrency, rates);
                                break;
                            default:
                                System.out.println("Opción inválida. Intenta nuevamente.");
                                continue;
                        }

                        // Asegurarse de que la tasa de destino sea válida
                        if (toRate != -1) {
                            // Realizar la conversión
                            double convertedAmount = convertCurrency(amount, fromRate, toRate);
                            System.out.printf("\n%.2f %s equivale a %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
                        }
                    }

                    // Preguntar si el usuario desea hacer otra conversión
                    if (!continueConversion(scanner)) {
                        System.out.println("\nGracias por usar el Conversor de Monedas. ¡Hasta luego!");
                        break;
                    }
                    // Separación
                    System.out.println("\n---------------------------------------------------");
                }
            } else {
                logger.warning("Error al obtener las tasas de cambio. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al procesar la conversión de monedas.", e);
        }
    }
}

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Scanner;

public class CurrencyConverter {

    // Método para realizar la solicitud a la API y obtener la respuesta
    public static HttpResponse<String> getExchangeRates(String apiKey) throws Exception {
        String urlBase = "https://api.exchangerate-api.com/v4/latest/USD";
        String url = urlBase + "?apikey=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", "CurrencyConverterApp/1.0")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // Método para convertir la cantidad usando las tasas de cambio
    public static double convertCurrency(double amount, double fromRate, double toRate) {
        return (amount / fromRate) * toRate;
    }

    // Método para filtrar y obtener la tasa de una moneda específica
    public static double getRate(String currencyCode, JsonObject rates) {
        if (rates.has(currencyCode)) {
            return rates.get(currencyCode).getAsDouble();
        } else {
            System.out.println("Moneda no encontrada.");
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

    public static void main(String[] args) {
        String apiKey = "9f8500d9a508af1185d3f31d";  // Reemplaza con tu clave API real
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
                        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                        break;
                    }

                    // Leer la cantidad a convertir
                    System.out.print("Ingrese la cantidad que desea convertir: ");
                    double amount = scanner.nextDouble();

                    double fromRate = 0;
                    double toRate = 0;

                    // Configurar la moneda de origen según la opción seleccionada
                    switch (option) {
                        case 1:
                            fromRate = getRate("ARS", rates);
                            break;
                        case 2:
                            fromRate = getRate("BOB", rates);
                            break;
                        case 3:
                            fromRate = getRate("BRL", rates);
                            break;
                        case 4:
                            fromRate = getRate("CLP", rates);
                            break;
                        case 5:
                            fromRate = getRate("COP", rates);
                            break;
                        case 6:
                            fromRate = getRate("USD", rates);
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
                                toRate = getRate("ARS", rates);
                                break;
                            case 2:
                                toRate = getRate("BOB", rates);
                                break;
                            case 3:
                                toRate = getRate("BRL", rates);
                                break;
                            case 4:
                                toRate = getRate("CLP", rates);
                                break;
                            case 5:
                                toRate = getRate("COP", rates);
                                break;
                            case 6:
                                toRate = getRate("USD", rates);
                                break;
                            default:
                                System.out.println("Opción inválida. Intenta nuevamente.");
                                continue;
                        }

                        // Asegurarse de que la tasa de destino sea válida
                        if (toRate != -1) {
                            // Realizar la conversión
                            double convertedAmount = convertCurrency(amount, fromRate, toRate);
                            System.out.printf("\n%.2f %s equivale a %.2f %s\n", amount, getCurrencyCode(fromRate), convertedAmount, getCurrencyCode(toRate));
                        }
                    }

                }
            } else {
                System.out.println("Error al obtener datos de la API. Código de estado: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Método para obtener el código de la moneda basado en la tasa
    public static String getCurrencyCode(double rate) {
        if (rate == 0.000009) return "ARS";
        if (rate == 0.000145) return "BOB";
        if (rate == 0.1922) return "BRL";
        if (rate == 0.000746) return "CLP";
        if (rate == 0.00026) return "COP";
        if (rate == 1.0) return "USD";
        return "Desconocido";
    }
}

import com.google.gson.annotations.SerializedName;
import java.util.Map;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Controller {


    private String apiKey = "672a6c4c918a4a45418baa5c"; // Tu API key
    private String baseUrl = "https://v6.exchangerate-api.com/v6/";

    Responses responses = new Responses();
    Gson gson = new Gson();


    public void convertirMoneda(String monedaBase, String monedaConvert, double cantidad) {
        try {
            // Construir la URL para la API
            String url = baseUrl + apiKey + "/latest/" + monedaBase.toUpperCase();

            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Enviar solicitud y obtener respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON con GSON
            Responses apiResponse = gson.fromJson(response.body(), Responses.class);

            // Verificar si la solicitud fue exitosa
            if (apiResponse.isSuccess()) {
                Map<String, Double> rates = apiResponse.getConversionRates();

                if (rates.containsKey(monedaConvert.toUpperCase())) {
                    // Obtener la tasa de conversión para la moneda objetivo
                    double tasaConversion = rates.get(monedaConvert.toUpperCase());

                    // Calcular el resultado
                    double resultado = cantidad * tasaConversion;

                    // Mostrar resultado formateado
                    mostrarResultado(cantidad, monedaBase, resultado, monedaConvert,
                            tasaConversion, apiResponse.getTimeLastUpdateUtc());
                } else {
                    System.out.println("Error: La moneda '" + monedaConvert + "' no está disponible.");
                    System.out.println("Monedas disponibles: " + String.join(", ", rates.keySet()));
                }
            } else {
                System.out.println("Error en la respuesta de la API");
            }

        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarResultado(double cantidad, String monedaBase, double resultado,
                                  String monedaConvert, double tasaConversion, String ultimaActualizacion) {
        System.out.println("                  RESULTADO DE CONVERSIÓN");
        System.out.printf("  %.2f %s = %.2f %s%n", cantidad, monedaBase.toUpperCase(),
                resultado, monedaConvert.toUpperCase());
    }

    public void deDolarAPesoArgentino(double cantidad) {
        convertirMoneda("USD", "ARS", cantidad);
    }

    public void dePesoArgentinoADolar(double cantidad) {
        convertirMoneda("ARS", "USD", cantidad);
    }

    public void deEuroAPesoArgentino(double cantidad) {
        convertirMoneda("EUR", "ARS", cantidad);
    }

    public void dePesoArgentinoAEuro(double cantidad) {
        convertirMoneda("ARS", "EUR", cantidad);
    }

    public void deDolarAReal(double cantidad) {
        convertirMoneda("USD", "BRL", cantidad);
    }

    public void deEuroADolar(double cantidad) {
        convertirMoneda("EUR", "USD", cantidad);
    }

}
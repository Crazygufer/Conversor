package conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIExchangeService {
    private final String apiKey = " 63ec4b8462358770aca6b06a"; 

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws Exception {
        // URL de la API con la moneda de origen
        String url = "https://api.exchangerate-api.com/v4/latest/" + monedaOrigen;
        
        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        
        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        
        // Enviar solicitud y obtener respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Procesar respuesta 
        String json = response.body();
        
        // Ejemplo simple (esto luego lo harás con Gson)
        if (json.contains(monedaDestino)) {
            int index = json.indexOf(monedaDestino) + monedaDestino.length() + 2;
            String tasa = json.substring(index, json.indexOf(",", index));
            return Double.parseDouble(tasa);
        } else {
            throw new Exception("Moneda no encontrada en la respuesta de la API.");
        }
    }
}

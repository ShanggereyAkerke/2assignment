import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleServer  {
    // Твой исходный список (БД)
    private static List<String> properties = new ArrayList<>(List.of(
            "{\"id\":1, \"type\":\"APARTMENT\", \"address\":\"Qabanbay 23\", \"price\":200000, \"sqft\":120, \"tax\":2000}",
            "{\"id\":2, \"type\":\"HOUSE\", \"address\":\"Turan 55\", \"price\":350000, \"sqft\":250, \"tax\":5250}",
            "{\"id\":3, \"type\":\"APARTMENT\", \"address\":\"Syganak 16/1\", \"price\":180000, \"sqft\":95, \"tax\":1800}",
            "{\"id\":4, \"type\":\"HOUSE\", \"address\":\"Kabanbay Batyr 11\", \"price\":500000, \"sqft\":300, \"tax\":7500}",
            "{\"id\":5, \"type\":\"APARTMENT\", \"address\":\"Mangilik El 28\", \"price\":280000, \"sqft\":110, \"tax\":2800}"
    ));

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/properties", exchange -> {
            // 1. CORS Headers (Обязательно для работы с браузером)
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            String method = exchange.getRequestMethod();

            // Обработка предварительного запроса браузера
            if ("OPTIONS".equalsIgnoreCase(method)) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String response = "";
            int statusCode = 200;

            try {
                // ЛОГИКА УДАЛЕНИЯ (DELETE)
                if ("DELETE".equalsIgnoreCase(method)) {
                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.startsWith("id=")) {
                        String idStr = query.substring(3);
                        // Удаляем строку из списка, которая содержит этот ID
                        properties.removeIf(p -> p.contains("\"id\":" + idStr + ",") || p.contains("\"id\":" + idStr + "}"));
                        response = "{\"status\":\"deleted\"}";
                    }
                }
                // ЛОГИКА ПОЛУЧЕНИЯ (GET)
                else if ("GET".equalsIgnoreCase(method)) {
                    response = "[" + String.join(",", properties) + "]";
                }
                // ЛОГИКА ДОБАВЛЕНИЯ (POST)
                else if ("POST".equalsIgnoreCase(method)) {
                    InputStream is = exchange.getRequestBody();
                    String body = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
                    properties.add(body);
                    response = "{\"status\":\"added\"}";
                }
            } catch (Exception e) {
                statusCode = 500;
                response = "{\"error\":\"" + e.getMessage() + "\"}";
            }

            // ОТПРАВКА ОТВЕТА
            byte[] resp = response.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, resp.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(resp);
            }
        });

        server.start();
        System.out.println("🚀 Backend running on http://localhost:8080");
    }
}
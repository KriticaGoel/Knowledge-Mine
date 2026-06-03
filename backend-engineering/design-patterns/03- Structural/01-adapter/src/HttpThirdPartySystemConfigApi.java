package designPattern.structural.adapter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpThirdPartySystemConfigApi implements ThirdPartySystemConfigApi {

    private final String url;
    private final String authHeader;
    private final HttpClient httpClient;

    public HttpThirdPartySystemConfigApi(String url, String token) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("url must be provided");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("token must be provided");
        }
        this.url = url;
        this.authHeader = token.startsWith("Bearer ") ? token : "Bearer " + token;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public String fetchConfig() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", authHeader)
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                throw new IllegalStateException("Third-party API returned status: " + response.statusCode());
            }
            return response.body();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Third-party API call interrupted", ex);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to call third-party API", ex);
        }
    }
}

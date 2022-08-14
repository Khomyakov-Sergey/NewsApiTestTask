package ru.clevertec.newsapi.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("WireMock Test for feature class Authorization")
public class AuthorizationWireMockTest {
    WireMockServer wireMockServer = new WireMockServer();

    @Test
    @DisplayName("WireMock test, which check response from remote api, when authorization successful")
    void authorizationWireMockTest() throws IOException {

        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo("/authorization"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("\"Login\": \"Test\""))
                .withRequestBody(containing("\"Password\": \"123sd3\""))
                .willReturn(aResponse()
                        .withStatus(200)));

        String json = "{"
                + "      \"Login\": \"Test\","
                + "      \"Password\": \"123sd3\""
                + "}";

        StringEntity entity = new StringEntity(json);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/authorization");
        request.addHeader("Content-Type", "application/json");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        verify(postRequestedFor(urlEqualTo("/authorization"))
                .withHeader("Content-Type", equalTo("application/json")));
        assertEquals(200, response.getStatusLine().getStatusCode());
    }


}

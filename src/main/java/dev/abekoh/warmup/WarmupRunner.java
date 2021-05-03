package dev.abekoh.warmup;

import com.fasterxml.jackson.databind.JsonNode;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WarmupRunner implements ApplicationRunner {

  private final WebClient webClient;

  public WarmupRunner(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/users").build();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var request =
        WebApiUserAddRequest.builder()
            .firstName("Taro")
            .lastName("Yamada")
            .birthYear(1970)
            .birthMonth(1)
            .birthDate(1)
            .isDummy(true)
            .build();
    webClient
        .post()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(JsonNode.class)
        .repeat(30000)
        .blockLast();
  }
}
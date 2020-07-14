package academy.devdojo.webflux.integration;

import academy.devdojo.webflux.domain.Anime;
import academy.devdojo.webflux.repository.AnimeRepository;
import academy.devdojo.webflux.service.AnimeService;
import academy.devdojo.webflux.stub.AnimeStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(AnimeService.class)
public class AnimeControllerIT {

    @MockBean
    private AnimeRepository animeRepository;

    @Autowired
    private WebTestClient testClient;

    private final Anime anime = AnimeStub.createValidAnime();
    @BeforeEach
    public void setup(){
        when(animeRepository.findAll()).thenReturn(Flux.just(anime));
//        when(animeService.findById(anyInt())).thenReturn(Mono.just(AnimeStub.createValidAnime()));
//        when(animeService.save(AnimeStub.createAnimeToBeSaved())).thenReturn(Mono.just(anime));
//        when(animeService.update(AnimeStub.createValidAnime())).thenReturn(Mono.empty());
//        when(animeService.delete(anyInt())).thenReturn(Mono.empty());
}

    @Test
    @DisplayName("ListAll return a flux of anime")
    public void listAll_ReturnFluxOfAnime_WhenSuccessful(){
        testClient
                .get()
                .uri("/animes")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(anime.getId())
                .jsonPath("$.[0].name").isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("listAll returns a flux of anime")
    public void listAll_Flavor2_ReturnOfAnime_WhenSuccessful(){
        testClient
                .get()
                .uri("/animes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Anime.class)
                .hasSize(1)
                .contains(anime);
    }


}

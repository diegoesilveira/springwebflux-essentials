package academy.devdojo.webflux.controller;

import academy.devdojo.webflux.domain.Anime;
import academy.devdojo.webflux.service.AnimeService;
import academy.devdojo.webflux.stub.AnimeStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeService;

    private final Anime anime = AnimeStub.createValidAnime();

    AnimeControllerTest() {
    }


    @BeforeEach
    public void setup(){
        when(animeService.findAll()).thenReturn(Flux.just(anime));
        when(animeService.findById(anyInt())).thenReturn(Mono.just(AnimeStub.createValidAnime()));
        when(animeService.save(AnimeStub.createAnimeToBeSaved())).thenReturn(Mono.just(anime));
        when(animeService.update(AnimeStub.createValidAnime())).thenReturn(Mono.empty());
        when(animeService.delete(anyInt())).thenReturn(Mono.empty());


    }


    @Test
    @DisplayName("findAll return a flux of anime")
    public void findAll_ReturnFluxOfAnime_WhenSuccessful(){
        StepVerifier.create(animeController.listAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }
    @Test
    @DisplayName("findById returns a Mono with anime when it exists")
    public void findById_ReturnMonoAnime_WhenSuccessful(){
        StepVerifier.create(animeController.findById(1))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("save creates an anime when successful")
    public void save_CreatesAnime_WhenSuccess(){
        Anime animeToBeSaved = AnimeStub.createAnimeToBeSaved();
        System.out.println(animeToBeSaved);
        StepVerifier.create(animeController.save(animeToBeSaved))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("Update anime when successful")
    public void update_Anime_WhenSuccess(){
        StepVerifier.create(animeController.update(AnimeStub.createValidAnime()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete anime when successful")
    public void delete_Anime_WhenSuccess(){
        StepVerifier.create(animeController.delete(1))
                .expectSubscription()
                .verifyComplete();
    }





}
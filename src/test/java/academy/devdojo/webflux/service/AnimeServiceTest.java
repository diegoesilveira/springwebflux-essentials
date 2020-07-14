package academy.devdojo.webflux.service;

import academy.devdojo.webflux.domain.Anime;
import academy.devdojo.webflux.repository.AnimeRepository;
import academy.devdojo.webflux.stub.AnimeStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.when;


@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepository;

    private final Anime anime = AnimeStub.createValidAnime();


    @BeforeEach
    public void setup(){
        when(animeRepository.findAll()).thenReturn(Flux.just(anime));
        when(animeRepository.findById(anyInt())).thenReturn(Mono.just(anime));
        when(animeRepository.save(AnimeStub.createAnimeToBeSaved())).thenReturn(Mono.just(anime));
        when(animeRepository.delete(any(Anime.class)))
                .thenReturn(Mono.empty());
        when(animeRepository.save(AnimeStub.createValidAnime()))
                .thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("FindAll return a flux of anime")
    public void findAll_ReturnFluxOfAnime_whenSuccessful(){

        StepVerifier.create(animeService.findAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById return Mono with anime when it exists")
    public void findAll_ReturnMonoAnime_WhenSuccessful(){
        StepVerifier.create(animeService.findById(1))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById return Mono error when anime does not exists")
    public void findAll_ReturnMonoError_WhenEmptyMonoIsReturned(){
        when(animeRepository.findById(anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(animeService.findById(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Save creates an anime when Successful")
    public void Save_CreatesAnime_WhenSuccessful(){
        Anime animeToBeSaved = AnimeStub.createAnimeToBeSaved();
        StepVerifier.create(animeService.save(animeToBeSaved))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete removes the anime when successful")
    public void delete_RemovesAnime_WhenSuccessful(){
        StepVerifier.create(animeService.delete(1))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete returns Mono error when anime does not exist")
    public void delete_ReturnMonoError_WhenEmptyMonoIsReturned(){
        when(animeRepository.findById(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(animeService.delete(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("update_save_update anime and return empty mono when successful")
    public void update_SaveUpdateAnime_WhenSuccessful(){
        StepVerifier.create(animeService.update(AnimeStub.createValidAnime()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("update returns Mono error when anime does not exist")
    public void update_ReturnMonoError_WhenEmptyMonoIsReturned(){
        when(animeRepository.findById(anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(animeService.update((AnimeStub.createValidAnime())))
            .expectSubscription()
            .expectError(ResponseStatusException.class)
            .verify();
    }







}
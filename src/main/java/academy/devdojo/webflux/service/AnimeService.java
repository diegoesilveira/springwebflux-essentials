package academy.devdojo.webflux.service;

import academy.devdojo.webflux.domain.Anime;
import academy.devdojo.webflux.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@XSlf4j
@RequiredArgsConstructor
public class AnimeService {

    private AnimeRepository animeRepository;

    public Flux<Anime> findAll(){
        return animeRepository.findAll();
    }

    public Mono<Anime> findById(int id){
        return animeRepository.findById(id);
    }

    public Mono<Anime> save(Anime anime){
        return animeRepository.save(anime);
    }

    public Mono<Void> update(Anime anime){
        return findById(anime.getId())
                .flatMap(animeRepository::save)
                .then();
    }

    public Mono<Void> delete(Integer id){
        return findById(id)
                .flatMap(animeRepository::delete);
    }

}

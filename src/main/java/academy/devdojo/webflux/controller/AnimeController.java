package academy.devdojo.webflux.controller;

import academy.devdojo.webflux.domain.Anime;
import academy.devdojo.webflux.repository.AnimeRepository;
import academy.devdojo.webflux.service.AnimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/animes")
@XSlf4j
public class AnimeController {

    private AnimeService animeService;


    @GetMapping
    public Flux<Anime> listAll(){
        return animeService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Anime> findById(@PathVariable int id){
         return animeService.findById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Anime> save(@RequestBody Anime anime){
        return animeService.save(anime);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@RequestBody Anime anime){
        return animeService.update(anime);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable int id){
        return animeService.delete(id);

    }

    public <T> Mono<T> monoResponseStatusNotFoundException(){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND"));
    }

}

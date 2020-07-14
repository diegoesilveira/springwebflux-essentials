package academy.devdojo.webflux.stub;


import academy.devdojo.webflux.domain.Anime;

public class AnimeStub {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Diego")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .id(1)
                .name("Diego")
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .id(1)
                .name("João Pedro")
                .build();
    }
}

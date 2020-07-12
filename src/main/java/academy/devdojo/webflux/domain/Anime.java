package academy.devdojo.webflux.domain;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Builder
@AllArgsConstructor
@With
public class Anime {

    @Id
    private Integer id;

    private String name;

}

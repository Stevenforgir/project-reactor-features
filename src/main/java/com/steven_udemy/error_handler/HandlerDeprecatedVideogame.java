package com.steven_udemy.error_handler;

import com.steven_udemy.database.Database;
import com.steven_udemy.models.Console;
import com.steven_udemy.models.Videogame;
import reactor.core.publisher.Flux;

import java.util.List;

public class HandlerDeprecatedVideogame {

    private static final Videogame DEFAULT_VIDEOGAME =
            Videogame.builder()
                    .name("Default")
                    .price(0.0)
                    .console(Console.ALL)
                    .reviews(List.of())
                    .officialWebsite("https://www.default.com/")
                    .isDiscount(true)
                    .totalSold(0)
                    .build();

    public static Flux<Videogame> handleDeprecatedVideogame() {
        return Database.getDataAsFlux()
                .handle((videogame, sink) -> {
                    if(Console.DEPRECATED == videogame.getConsole()){
                        sink.error(new RuntimeException("Videogame is deprecated"));
                        return;
                    }
                    sink.next(videogame);
                })
                .onErrorResume(error -> {
                    System.out.println("Error handled: " + error.getMessage());
                    return Flux.merge(
                            Database.getDataAsFlux(),
                            Database.fluxAssassinsDefault
                    );
                })
                .cast(Videogame.class)
                .distinct(Videogame::getName);
    }

    public static Flux<Videogame> handleDeprecatedVideogameDefault() {
        return Database.getDataAsFlux()
                .handle((videogame, sink) -> {
                    if(Console.DEPRECATED == videogame.getConsole()){
                        sink.error(new RuntimeException("Videogame is deprecated"));
                        return;
                    }
                    sink.next(videogame);
                })
                .onErrorReturn(DEFAULT_VIDEOGAME)
                .cast(Videogame.class);
    }

}

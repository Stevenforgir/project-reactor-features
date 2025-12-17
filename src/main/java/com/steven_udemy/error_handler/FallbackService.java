package com.steven_udemy.error_handler;

import com.steven_udemy.database.Database;
import com.steven_udemy.models.Console;
import com.steven_udemy.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FallbackService {

    public static Flux<Videogame> callFallback() {
        return Database.getDataAsFlux()
                .handle((videogame, sink) -> {
                    if(Console.DEPRECATED == videogame.getConsole()){
                        sink.error(new RuntimeException("Videogame is deprecated"));
                        return;
                    }
                    sink.next(videogame);
                })
                .retry(5)
                .onErrorResume(error -> {
                    log.error("Database is failing");
                    return Database.fluxFallback;
                })
                .repeat(1)
                .cast(Videogame.class);
    }
}

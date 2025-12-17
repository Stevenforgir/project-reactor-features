package com.steven_udemy.callback;

import com.steven_udemy.database.Database;
import com.steven_udemy.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class CallbacksExample {

    /**
     *
     * Todos los callbacks comienzan con do...
     * @return
     */
    public static Flux<Videogame> callbacks(){
        return Database.getDataAsFlux()
                .doOnSubscribe(subs -> log.info("[doonSubscribe]"))
                .doOnRequest(n -> log.info("[doOnRequest]: {}", n))
                .doOnNext(v -> log.info("[doOnNext]: {}", v.getName()))
                .doOnCancel(() -> log.warn("[doOnCancel]"))
                .doOnError(error -> log.error("[doOnError]: {}", error.getMessage()))
                .doOnComplete(() -> log.info("[doOnComplete]: Completed successfully"))
                .doOnTerminate(() -> log.info("[doOnTerminate]: Terminated"))
                .doFinally(signalType -> log.warn("[doFinally]: {}", signalType));
    }
}

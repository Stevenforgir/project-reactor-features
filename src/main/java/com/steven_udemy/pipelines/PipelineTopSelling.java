package com.steven_udemy.pipelines;

import com.steven_udemy.database.Database;
import com.steven_udemy.models.Videogame;
import reactor.core.publisher.Flux;

public class PipelineTopSelling {

    /*
        return all names of videogames that have sold > 80
     */
    public static Flux<String> getTopSellingVideoGames() {
        return Database.getDataAsFlux()
                .filter(videogame -> videogame.getTotalSold() > 80)
                .map(Videogame::getName);
    }
}

package com.steven_udemy.pipelines;

import com.steven_udemy.database.Database;
import reactor.core.publisher.Mono;

import javax.xml.crypto.Data;
import java.util.Optional;

public class PipelineSumAllPricesInDiscount {

    /*
          Sum all prices of each videogame in discount
     */
    public static Mono<Double> getSumAllPricesInDiscount() {
        return Database.getDataAsFlux()
                .filter(videogame -> videogame.getIsDiscount())
                .map(videogame -> videogame.getPrice())
                .reduce(0.0, (a,b) -> a + b);
    }

}

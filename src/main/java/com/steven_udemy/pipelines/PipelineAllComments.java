package com.steven_udemy.pipelines;

import com.steven_udemy.database.Database;
import reactor.core.publisher.Flux;

public class PipelineAllComments {

    /*
        box -> [item1, item2, item3], box -> [item1, item2]
           .map box -> boxWithLabel = 2 boxes      //El operador map no abre la caja
           .flatMap: item -> useItem(item)   //5 items  Abre cada caja y obtiene lo que tiene cada una
     */
    public static Flux<String> getAllReviewsComments() {
        return Database.getDataAsFlux()
                .flatMap(videogame -> Flux.fromIterable(videogame.getReviews())) //videogame -> review
                .map(review -> review.getComment());
    }
}

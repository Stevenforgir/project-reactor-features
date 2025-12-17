package com.steven_udemy;

import com.steven_udemy.error_handler.FallbackService;
import com.steven_udemy.error_handler.HandlerDeprecatedVideogame;
import com.steven_udemy.pipelines.PipelineAllComments;
import com.steven_udemy.pipelines.PipelineSumAllPricesInDiscount;
import com.steven_udemy.pipelines.PipelineTopSelling;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class Main {
    public static void main(String[] args) {

        /*//publisher
        Mono<String> mono = Mono.just("Hello world")
                .doOnNext(value -> log.info("[doOnNext] " + value))
                .doOnSuccess(value -> log.info("[doOnSuccess] " + value))
                .doOnError(err -> log.info(err.getMessage()));

        //Consumer
        mono.subscribe(
                data -> log.info("receivind data: " + data),
                err -> log.info("Error: " + err.getMessage()),
                () -> log.info("Complete successfully")
        );

        log.info("---------------------");

        //Publisher
        Flux<String> flux = Flux.just("Java", "Spring", "Reactor")
                .doOnNext(value -> log.info("[doOnNext]: " + value))
                .doOnComplete(() -> log.info("[doOnComplete]: Completed successfully"));

        //Consumer
        flux.subscribe(
                data -> log.info("receiving data: " + data),
                err -> log.info("Error: " + err.getMessage()),
                () -> log.info("Stream processing complete")
        );*/


        /*PipelineTopSelling.getTopSellingVideoGames()
                .subscribe(System.out::println);*/

        /*PipelineSumAllPricesInDiscount.getSumAllPricesInDiscount()
                .subscribe(System.out::println);*/

        /*PipelineAllComments.getAllReviewsComments()
                .subscribe(System.out::println);*/

        /*Flux<String> fluxA = Flux.just("1", "2"); //From reactive mongo
        Flux<String> fluxB = Flux.just("A", "B"); //From Webclient

        Flux<String> combinedFlux = fluxA.flatMap(strA ->
                fluxB.map(strB -> strA + " - " + strB));
        //combinedFlux.subscribe(System.out::println);
        combinedFlux
                .map(String::toLowerCase)
                .doOnNext(System.out::println)
                .subscribe();*/

        /*Flux<String> fluxA = Flux.just("1", "2", "3").delayElements(Duration.ofMillis(100)); //From reactive mongo
        Flux<String> fluxB = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(50)); //From Webclient

        Flux<String> combinedFlux = Flux.merge(fluxA, fluxB); //Merge intercalara los dos flujos conforme vayan llegando los datos
        //Flux<String> combinedFlux = Flux.concat(fluxA, fluxB); //Concat espera a que termine el primer flujo para iniciar el segundo
        combinedFlux
                .doOnNext(System.out::println)
                .blockLast();*/

        /*Flux<String> fluxShipments = Flux.just("Shipment1", "Shipment2", "Shipment3", "Shipment4").delayElements(Duration.ofMillis(120));
        Flux<String> fluxWarehouse = Flux.just("stock1", "stock2", "stock3", "stock4").delayElements(Duration.ofMillis(50));
        Flux<String> fluxPayments = Flux.just("payment1", "payment2", "payment3").delayElements(Duration.ofMillis(150));
        Flux<String> fluxConfirm = Flux.just("confirm1", "confirm2", "confirm3").delayElements(Duration.ofMillis(20));

        //Flux<String> reportFlux = Flux.zip(fluxShipments, fluxWarehouse, (shipment, stock) -> shipment + " " + stock); //Combina dos flujos
        Flux<String> reportFlux = Flux.zip(fluxShipments, fluxWarehouse, fluxPayments, fluxConfirm)
                        .map(tuple ->
                                tuple.getT1() + " " +
                                tuple.getT2() + " " +
                                tuple.getT3() + " " +
                                tuple.getT4());

        reportFlux
                .doOnNext(System.out::println)
                .blockLast();*/

        /*HandlerDeprecatedVideogame.handleDeprecatedVideogame()
                .subscribe(System.out::println);*/

        /*HandlerDeprecatedVideogame.handleDeprecatedVideogameDefault()
                .subscribe(System.out::println);*/

        FallbackService.callFallback()
                .subscribe(v-> log.info(v.toString()));

    }
}
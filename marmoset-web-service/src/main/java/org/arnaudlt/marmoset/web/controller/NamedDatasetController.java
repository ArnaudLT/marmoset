package org.arnaudlt.marmoset.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arnaudlt.marmoset.core.NamedDatasetService;
import org.arnaudlt.marmoset.core.model.Catalog;
import org.arnaudlt.marmoset.core.model.MDataset;
import org.arnaudlt.marmoset.core.model.SqlQueryOutput;
import org.arnaudlt.marmoset.web.api.*;
import org.arnaudlt.marmoset.web.utils.Converters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Stream;


@Slf4j
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/datasets")
public class NamedDatasetController {

    private NamedDatasetService namedDatasetService;


    @GetMapping(value = "test")
    public Flux<MDatasetDto> test() {

        log.info("Hello Test !");

        return Flux.fromStream(Stream.generate(() -> new MDatasetDto(
                        new DatasetNameDto(UUID.randomUUID().toString()), new SchemaDto(Collections.emptySet()))))
                .take(10)
                .log()
                .delayElements(Duration.ofMillis(100));
    }

    @PostMapping(value = "load")
    public Mono<MDatasetDto> load(@RequestBody DatasetImportSettingsDto datasetImportSettingsDto) {

        log.info("Request to load dataset {}", datasetImportSettingsDto);
        MDataset dataset = namedDatasetService.load(Converters.toBusinessObject(datasetImportSettingsDto));
        return Mono.just(Converters.toDataTransferObject(dataset));
    }

    @DeleteMapping(value = "unload")
    public Mono<Void> unload(@RequestBody DatasetNameDto datasetNameDto) {

        log.info("Request to unload dataset {}", datasetNameDto);
        namedDatasetService.unload(Converters.toBusinessObject(datasetNameDto));
        return Mono.empty();
    }

    @PostMapping(value = "run-query")
    public Mono<SqlQueryOutputDto> runQuery(@RequestBody SqlQueryDto sqlQueryDto) {

        log.info("Request to run query {}", sqlQueryDto);
        SqlQueryOutput output = namedDatasetService.runQuery(Converters.toBusinessObject(sqlQueryDto));
        return Mono.just(Converters.toDataTransferObject(output));
    }

    @GetMapping(value = "catalog")
    public Mono<CatalogDto> catalog() {

        log.info("Request catalog");
        Catalog catalog = namedDatasetService.catalog();
        return Mono.just(Converters.toDataTransferObject(catalog));
    }
}

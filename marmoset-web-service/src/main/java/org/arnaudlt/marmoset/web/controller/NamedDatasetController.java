package org.arnaudlt.marmoset.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arnaudlt.marmoset.core.NamedDatasetService;
import org.arnaudlt.marmoset.core.model.MDataset;
import org.arnaudlt.marmoset.core.model.SqlQueryOutput;
import org.arnaudlt.marmoset.web.api.*;
import org.arnaudlt.marmoset.web.utils.Converters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@AllArgsConstructor
@RestController(value = "/api/v1/datasets")
public class NamedDatasetController {

    private NamedDatasetService namedDatasetService;


    @PostMapping(value = "load")
    public Mono<MDatasetDto> load(@RequestBody DatasetImportSettingsDto datasetImportSettingsDto) {

        log.info("Request to load dataset {}", datasetImportSettingsDto);
        MDataset dataset = namedDatasetService.load(Converters.toBusinessObject(datasetImportSettingsDto));
        return Mono.just(Converters.toDataTransferObject(dataset));
    }

    @PostMapping(value = "unload")
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
}

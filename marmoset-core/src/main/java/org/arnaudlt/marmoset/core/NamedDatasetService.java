package org.arnaudlt.marmoset.core;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.arnaudlt.marmoset.core.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class NamedDatasetService {

    private final SparkSession sparkSession;


    @SneakyThrows
    public MDataset load(DatasetImportSettings datasetImportSettings) {

        log.info("Starting to load {}", datasetImportSettings);
        Dataset<Row> dataset = sparkSession
                .read()
                .json(datasetImportSettings.getUri()); // "data\\sample.json"

        final String temporaryViewName = datasetImportSettings.getRequestedName();
        dataset.createTempView(temporaryViewName);

        return new MDataset(new DatasetName(temporaryViewName), dataset);
    }

    public void unload(DatasetName datasetName) {

        log.info("Starting to unload {}", datasetName);
        sparkSession.catalog().dropTempView(datasetName.getName());
    }

    public SqlQueryOutput runQuery(SqlQuery sqlQuery) {

        log.info("Starting to run query {}", sqlQuery);
        Dataset<Row> outputDataset = sparkSession.sqlContext().sql(sqlQuery.getQuery());
        OutputRows outputRows = new OutputRows(outputDataset.takeAsList(20));

        return new SqlQueryOutput(sqlQuery, outputDataset, outputRows);
    }

    public List<MDataset> catalog() {

        log.info("Starting to retrieve catalog");
        return sparkSession.catalog()
                .listTables()
                .collectAsList()
                .stream()
                .map(table -> {
                    Dataset<Row> dataset = sparkSession.sqlContext().sql("SELECT * FROM " + table.name());
                    return new MDataset(new DatasetName(table.name()), dataset);})
                .collect(Collectors.toList());
    }

}

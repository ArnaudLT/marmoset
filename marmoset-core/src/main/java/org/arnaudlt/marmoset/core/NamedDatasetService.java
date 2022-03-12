package org.arnaudlt.marmoset.core;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.arnaudlt.marmoset.core.model.*;
import org.springframework.stereotype.Component;

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
                .json("data\\sample.json");

        final String temporaryViewName = "covid"; //datasetImportSettings.getRequestedName();
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
        OutputRows outputRows = new OutputRows(outputDataset.takeAsList(10));

        return new SqlQueryOutput(sqlQuery, outputDataset, outputRows);
    }

}

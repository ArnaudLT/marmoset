package org.arnaudlt.marmoset.core.model;

import lombok.Data;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

@Data
public class MDataset {

    private final DatasetName datasetName;

    private final Dataset<Row> dataset;

}

package org.arnaudlt.marmoset.core.model;

import lombok.Data;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


@Data
public class SqlQueryOutput {

    private final SqlQuery sqlQuery;

    private final Dataset<Row> outputDataset;

    private final OutputRows outputRows;

}

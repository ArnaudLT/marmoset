package org.arnaudlt.marmoset.core.model;

import lombok.Data;
import org.apache.spark.sql.Row;

import java.util.List;

@Data
public class OutputRows {

    private final List<Row> rows;

}

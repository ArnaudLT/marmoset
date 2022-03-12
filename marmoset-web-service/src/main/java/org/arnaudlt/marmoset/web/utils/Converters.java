package org.arnaudlt.marmoset.web.utils;

import lombok.experimental.UtilityClass;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.arnaudlt.marmoset.web.api.*;
import org.arnaudlt.marmoset.core.model.*;

import java.util.*;

@UtilityClass
public class Converters {


    public static DatasetImportSettings toBusinessObject(DatasetImportSettingsDto datasetImportSettingsDto) {

        return new DatasetImportSettings(
                datasetImportSettingsDto.getUri(),
                datasetImportSettingsDto.getBasePath(),
                datasetImportSettingsDto.getRequestedName());
    }

    public static MDatasetDto toDataTransferObject(MDataset dataset) {

        return new MDatasetDto(
                new DatasetNameDto(dataset.getDatasetName().getName()),
                new SchemaDto(convert2(dataset.getDataset().schema())));
    }

    public static SqlQuery toBusinessObject(SqlQueryDto sqlQueryDto) {

        return new SqlQuery(sqlQueryDto.getQuery());
    }

    public static SqlQueryDto toDataTransferObject(SqlQuery sqlQuery) {

        return new SqlQueryDto(sqlQuery.getQuery());
    }

    public static SqlQueryOutputDto toDataTransferObject(SqlQueryOutput sqlQueryOutput) {

        return new SqlQueryOutputDto(
                toDataTransferObject(sqlQueryOutput.getSqlQuery()),
                convert(sqlQueryOutput.getOutputRows().getRows(), sqlQueryOutput.getOutputDataset().schema().fields())
        );
    }

    public static DatasetName toBusinessObject(DatasetNameDto datasetNameDto) {

        return new DatasetName(datasetNameDto.getName());
    }

    private static OutputRowsDto convert(List<Row> rows, StructField[] fields) {

        List<Map<String, String>> convertedRows = new ArrayList<>();
        for (Row row : rows) {

            Map<String, String> convertedRow = new TreeMap<>();
            for (StructField field : fields) {

                convertedRow.put(field.name(), row.getAs(field.name()));
            }
            convertedRows.add(convertedRow);
        }
        return new OutputRowsDto(convertedRows);
    }

    private static Map<String, FieldDto> convert2(StructType schema) {

        Map<String, FieldDto> schemaDto = new TreeMap<>();
        for (StructField structField : schema.fields()) {

            // TODO fill inner fields
            FieldDto fieldDto = new FieldDto(structField.name(), structField.dataType().typeName(), Collections.emptyMap());
            schemaDto.put(fieldDto.getName(), fieldDto);
        }
        return schemaDto;
    }

}

package org.arnaudlt.marmoset.web.utils;

import lombok.experimental.UtilityClass;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.*;
import org.arnaudlt.marmoset.core.model.*;
import org.arnaudlt.marmoset.web.api.*;

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
                new SchemaDto(schemaToFieldsMap(dataset.getDataset().schema())));
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

    private static Map<String, FieldDto> schemaToFieldsMap(StructType schema) {

        Map<String, FieldDto> schemaDto = new TreeMap<>();
        for (StructField structField : schema.fields()) {

            TreeMap<String, FieldDto> innerFields = new TreeMap<>();
            FieldDto fieldDto = new FieldDto(structField.name(), structField.dataType().typeName(), innerFields);
            addInnerFields(fieldDto, structField.dataType());

            schemaDto.put(fieldDto.getName(), fieldDto);
        }
        return schemaDto;
    }

    private static void addInnerFields(FieldDto parentFieldDto, DataType dataType) {

        switch (dataType.typeName()) {

            case "struct":

                scala.collection.Iterator<StructField> structFieldsIterator = ((StructType) dataType).iterator();
                while (structFieldsIterator.hasNext()) {

                    StructField field = structFieldsIterator.next();
                    TreeMap<String, FieldDto> innerFields = new TreeMap<>();
                    FieldDto fieldDto = new FieldDto(field.name(), field.dataType().typeName(), innerFields);
                    addInnerFields(fieldDto, field.dataType());
                    parentFieldDto.getInnerFields().put(fieldDto.getName(), fieldDto);
                }
                break;
            case "map":

                MapType mapType = (MapType) dataType;
                addInnerFields(parentFieldDto, mapType.valueType());
                break;
            case "array":

                ArrayType arrayType = (ArrayType) dataType;
                addInnerFields(parentFieldDto, arrayType.elementType());
                break;
            default:
        }
    }


}

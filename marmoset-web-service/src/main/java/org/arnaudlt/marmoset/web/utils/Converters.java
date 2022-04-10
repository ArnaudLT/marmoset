package org.arnaudlt.marmoset.web.utils;

import lombok.experimental.UtilityClass;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.*;
import org.arnaudlt.marmoset.core.model.*;
import org.arnaudlt.marmoset.web.api.*;

import java.util.*;
import java.util.stream.Collectors;

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
                new SchemaDto(schemaToFields(dataset.getDataset().schema())));
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
                toOutputRowsDto(sqlQueryOutput)
        );
    }

    public static CatalogDto toDataTransferObject(Catalog catalog) {

        List<DatasetNameDto> datasetNames = catalog.getDatasetNames()
                .stream()
                .map(datasetName -> new DatasetNameDto(datasetName.getName()))
                .collect(Collectors.toList());
        return new CatalogDto(datasetNames);
    }

    public static DatasetName toBusinessObject(DatasetNameDto datasetNameDto) {

        return new DatasetName(datasetNameDto.getName());
    }

    private static OutputRowsDto toOutputRowsDto(SqlQueryOutput sqlQueryOutput) {

        List<Row> rows = sqlQueryOutput.getOutputRows().getRows();
        StructField[] fields = sqlQueryOutput.getOutputDataset().schema().fields();

        List<Map<String, String>> convertedRows = new ArrayList<>();
        for (Row row : rows) {

            Map<String, String> convertedRow = new LinkedHashMap<>();
            for (StructField field : fields) {

                convertedRow.put(field.name(), row.getAs(field.name()).toString());
            }
            convertedRows.add(convertedRow);
        }
        return new OutputRowsDto(convertedRows);
    }

    private static Set<FieldDto> schemaToFields(StructType schema) {

        Set<FieldDto> schemaDto = new LinkedHashSet<>();
        for (StructField structField : schema.fields()) {

            Set<FieldDto> innerFields = new LinkedHashSet<>();
            FieldDto fieldDto = new FieldDto(structField.name(), structField.dataType().typeName(), innerFields);
            addInnerFields(fieldDto, structField.dataType());

            schemaDto.add(fieldDto);
        }
        return schemaDto;
    }

    private static void addInnerFields(FieldDto parentFieldDto, DataType dataType) {

        switch (dataType.typeName()) {

            case "struct":

                scala.collection.Iterator<StructField> structFieldsIterator = ((StructType) dataType).iterator();
                while (structFieldsIterator.hasNext()) {

                    StructField field = structFieldsIterator.next();
                    Set<FieldDto> innerFields = new LinkedHashSet<>();
                    FieldDto fieldDto = new FieldDto(field.name(), field.dataType().typeName(), innerFields);
                    addInnerFields(fieldDto, field.dataType());
                    parentFieldDto.getInnerFields().add(fieldDto);
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

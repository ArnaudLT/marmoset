package org.arnaudlt.marmoset.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlQueryOutputDto {

    private SqlQueryDto sqlQuery;

    private SchemaDto schema;

    private OutputRowsDto outputRows;

}

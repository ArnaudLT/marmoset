package org.arnaudlt.marmoset.web.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MDatasetDto {

    private DatasetNameDto datasetName;

    private SchemaDto schema;

}

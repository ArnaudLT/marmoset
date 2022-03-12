package org.arnaudlt.marmoset.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemaDto {

    private Map<String, FieldDto> schema;

}

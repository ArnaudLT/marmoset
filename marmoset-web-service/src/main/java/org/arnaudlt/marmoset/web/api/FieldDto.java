package org.arnaudlt.marmoset.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto {

    private String name;

    private String type;

    private Set<FieldDto> innerFields;

}

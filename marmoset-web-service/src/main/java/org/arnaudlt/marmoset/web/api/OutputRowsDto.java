package org.arnaudlt.marmoset.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputRowsDto {

    private List<Map<String, String>> rows;

}

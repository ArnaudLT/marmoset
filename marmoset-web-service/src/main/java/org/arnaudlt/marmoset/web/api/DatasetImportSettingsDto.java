package org.arnaudlt.marmoset.web.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasetImportSettingsDto {

    private String uri;

    private String basePath;

    private String requestedName;

}

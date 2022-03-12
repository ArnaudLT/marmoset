package org.arnaudlt.marmoset.core.model;

import lombok.Data;

@Data
public class DatasetImportSettings {

    private final String uri;

    private final String basePath;

    private final String requestedName;

}

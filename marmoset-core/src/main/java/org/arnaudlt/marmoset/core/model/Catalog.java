package org.arnaudlt.marmoset.core.model;


import lombok.Data;

import java.util.List;

@Data
public class Catalog {

    private final List<DatasetName> datasetNames;

}

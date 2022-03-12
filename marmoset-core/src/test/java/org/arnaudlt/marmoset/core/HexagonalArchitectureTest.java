package org.arnaudlt.marmoset.core;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;


@AnalyzeClasses(packages = "org.arnaudlt.marmoset", importOptions = {ImportOption.DoNotIncludeTests.class})
class HexagonalArchitectureTest {

    @ArchTest
    static final ArchRule business_is_isolated =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("org.arnaudlt.marmoset.business..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "org.arnaudlt.marmoset.business..",
                            "org.apache.spark..",
                            "org.slf4j..",
                            "java.util..",
                            "java.lang..");

}

package org.arnaudlt.marmoset.web;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;


@AnalyzeClasses(packages = "org.arnaudlt.marmoset", importOptions = {ImportOption.DoNotIncludeTests.class})
class WebServiceModuleArcTest {

    @ArchTest
    static final ArchRule web_api =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("org.arnaudlt.marmoset.web.api..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "org.arnaudlt.marmoset.web.api..",
                            "org.slf4j..",
                            "java.util..",
                            "java.lang..");

}

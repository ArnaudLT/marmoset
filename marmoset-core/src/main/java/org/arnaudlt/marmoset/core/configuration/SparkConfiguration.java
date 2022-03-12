package org.arnaudlt.marmoset.core.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfiguration {

    @Bean
    public SparkSession getSparkSession() {

        return SparkSession
                .builder()
                .appName("Marmoset")
                .master("local[2]")
                .config("spark.ui.enabled", false)
                .enableHiveSupport()
                .getOrCreate();
    }
}

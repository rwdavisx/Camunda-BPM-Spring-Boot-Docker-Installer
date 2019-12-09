package com.infarmbureau.${rootArtifactId}.bpm;

import com.infarmbureau.${rootArtifactId}.bpm.util.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class ProcessEngineConfiguration {

    @Autowired
    AppProperties properties;

////    Uncomment to activate external database. (Be sure to configure .properties correctly)
//    @Bean
//    DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource()
//        dataSource.setDriverClass(properties.jdbc.driver)
//        dataSource.setUrl(properties.jdbc.url)
//        dataSource.setUsername(properties.jdbc.username)
//        dataSource.setPassword(properties.jdbc.password)
//        return dataSource
//    }
}

package com.infarmbureau.${rootArtifactId}.bpm

import com.infarmbureau.${rootArtifactId}.bpm.util.AppProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessEngineConfiguration {

    @Autowired
    AppProperties properties

//    @Bean
//    // Uncomment this bean to configure a persistent database solution.
//    DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource()
//        dataSource.setDriverClass(properties.jdbc.driver)
//        dataSource.setUrl(properties.jdbc.url)
//        dataSource.setUsername(properties.jdbc.username)
//        dataSource.setPassword(properties.jdbc.password)
//        return dataSource
//    }
}

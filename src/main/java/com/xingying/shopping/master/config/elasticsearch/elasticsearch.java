package com.xingying.shopping.master.config.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author SiletFlower
 * @date 2021/5/31 09:07:33
 * @description
 */
@Configuration
@EnableElasticsearchRepositories
public class elasticsearch extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("194.233.70.142:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}

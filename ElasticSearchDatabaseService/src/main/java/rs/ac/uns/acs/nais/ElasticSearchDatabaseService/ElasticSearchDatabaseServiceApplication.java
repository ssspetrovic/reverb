package rs.ac.uns.acs.nais.ElasticSearchDatabaseService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository")
public class ElasticSearchDatabaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchDatabaseServiceApplication.class, args);
	}

}

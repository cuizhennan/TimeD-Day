package mkcoding.services.solr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;

import javax.annotation.Resource;

/**
 * Created by mx on 16/3/27.
 */

@Configuration
@EnableSolrRepositories("mkcoding.services.solr")
@PropertySource("classpath:config.properties")
public class HttpSolrContext {

    @Resource
    private Environment environment;

    public HttpSolrServerFactoryBean solrServerFactoryBean() {

        HttpSolrServerFactoryBean factoryBean = new HttpSolrServerFactoryBean();
        factoryBean.setUrl(environment.getRequiredProperty("solr.server.url"));

        return factoryBean;
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {

        return new SolrTemplate(solrServerFactoryBean().getObject());
    }
}

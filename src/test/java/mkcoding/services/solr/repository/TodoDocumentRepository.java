package mkcoding.services.solr.repository;

import mkcoding.services.solr.pojo.TodoDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created by mx on 16/3/27.
 */
public interface TodoDocumentRepository extends SolrCrudRepository<TodoDocument, String> {

    public List<TodoDocument> findByTitleContainsOrDescriptionContains(String title, String description);
}

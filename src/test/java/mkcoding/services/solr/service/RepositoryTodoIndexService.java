package mkcoding.services.solr.service;

import mkcoding.services.solr.pojo.TodoDocument;
import mkcoding.services.solr.repository.TodoDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mx on 16/3/27.
 */
@Service
public class RepositoryTodoIndexService implements TodoIndexService {

    @Resource
    private TodoDocumentRepository repository;

    @Transactional
    @Override
    public void addToIndex(TodoDocument todoEntity) {
        TodoDocument document = TodoDocument.getBuilder(todoEntity.getId(), todoEntity.getTitle())
                .description(todoEntity.getDescription())
                .build();

        repository.save(document);
    }

    @Transactional
    @Override
    public void deleteFromIndex(Long id) {
        repository.delete(id.toString());
    }

    @Override
    public List<TodoDocument> search(String searchTerm) {

        return repository.findByTitleContainsOrDescriptionContains(searchTerm, searchTerm);
    }

}

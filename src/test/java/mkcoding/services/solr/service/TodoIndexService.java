package mkcoding.services.solr.service;

import com.sun.tools.javac.comp.Todo;
import mkcoding.services.solr.pojo.TodoDocument;

import java.util.List;

/**
 * Created by mx on 16/3/27.
 */
public interface TodoIndexService {

    public void addToIndex(TodoDocument todoEntity);

    public void deleteFromIndex(Long id);

    public List<TodoDocument> search(String searchTerm);
}

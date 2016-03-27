package mkcoding.services.solr.pojo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

/**
 * Created by mx on 16/3/27.
 */
public class TodoDocument {

    @Id
    @Field
    private String id;

    @Field
    private String description;

    @Field
    private String title;

    public TodoDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Builder getBuilder(String id, String title) {
        return new Builder(id, title);
    }

    public static class Builder {
        private TodoDocument build;

        public Builder(String id, String title) {
            build = new TodoDocument();
            build.id = id;
            build.title = title;
        }

        public Builder description(String description) {
            build.description = description;

            return this;
        }

        public TodoDocument build() {
            return build;
        }
    }
}

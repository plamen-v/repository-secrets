package org.lab.secrets.core.model;

import java.util.HashSet;
import java.util.Set;

public class Record {

    private Long id;
    private String url;
    private Set<String> secrets = new HashSet<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Set<String> getSecrets() {
        return secrets;
    }
    public void setSecrets(Set<String> secrets) {
        this.secrets = secrets;
    }
}

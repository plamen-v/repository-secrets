package org.lab.secrets.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "RECORDS")
public class Record {
    @Id
    @SequenceGenerator(name = "records_seq", sequenceName= "records_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "records_seq", strategy=GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="url")
    private String url;

    @ElementCollection
    @CollectionTable(name = "RECORDS_SECRETS",
            joinColumns = {@JoinColumn(name = "record_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "secret_key")
    @Column(name = "secret_key")
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

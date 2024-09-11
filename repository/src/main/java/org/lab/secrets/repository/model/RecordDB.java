package org.lab.secrets.repository.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RECORDS")
public class RecordDB {
    @Id
    @SequenceGenerator(name = "records_seq", sequenceName= "records_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "records_seq", strategy=GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="url")
    private String url;

    @ElementCollection(fetch = FetchType.EAGER)
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

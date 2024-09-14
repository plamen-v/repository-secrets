package org.lab.secrets.core.model;

import java.util.Objects;

public class Secret {
    private Long recordId;
    private String key;
    private String value;

    public Secret(Long recordId, String key, String value){
        this.recordId = recordId;
        this.key = key;
        this.value = value;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getKey() {
        return !Objects.isNull(key) ? (key.trim().isEmpty() ? null : key) : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return !Objects.isNull(value) ? (value.trim().isEmpty() ? null : value) : value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

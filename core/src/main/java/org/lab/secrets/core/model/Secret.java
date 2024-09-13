package org.lab.secrets.core.model;

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
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

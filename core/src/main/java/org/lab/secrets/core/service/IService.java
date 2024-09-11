package org.lab.secrets.core.service;

import java.util.List;

public interface IService<T> {
    List<T> getAllRecords();
    T saveRecord(T element);
    void deleteRecord(Long id);
    void saveSecret(Long id, String key, String value);
    void deleteSecret(Long id, String key);
}

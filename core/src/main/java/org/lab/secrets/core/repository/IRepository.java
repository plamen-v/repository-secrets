package org.lab.secrets.core.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> getAllRecords();
    T saveRecord(T element);
    void deleteRecord(Long id);
    void saveSecret(Long id, String key, String value);
    void deleteSecret(Long id, String key);
}

package org.lab.secrets.core.repository;

import java.util.List;
import org.lab.secrets.core.model.Record;

public interface IRecordRepository {
    List<Record> getAllRecords();
    Record saveRecord(Record element);
    void deleteRecord(Long id);
    void saveSecret(Long id, String key, String value);
    void deleteSecret(Long id, String key);
}

package org.lab.secrets.core.service;

import java.util.List;
import org.lab.secrets.core.model.Record;

public interface IRecordService {
    List<Record> getAllRecords();
    Record saveRecord(Record record);
    void deleteRecord(Long id);
    void saveSecret(Long id, String key, String value);
    void deleteSecret(Long id, String key);
}

package org.lab.secrets.repository;

import org.lab.secrets.core.repository.IRepository;
import org.lab.secrets.repository.model.Record;

import java.util.List;

public class RecordRepository  implements IRepository<Record> {

    @Override
    public List<Record> getAllRecords() {
        return List.of();
    }

    @Override
    public Record saveRecord(Record element) {
        return null;
    }

    @Override
    public void deleteRecord(Long id) {

    }

    @Override
    public void saveSecret(Long id, String key, String value) {

    }

    @Override
    public void deleteSecret(Long id, String key) {

    }
}

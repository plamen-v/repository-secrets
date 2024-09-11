package org.lab.secrets.service;

import org.lab.secrets.core.repository.IRepository;
import org.lab.secrets.repository.model.Record;
import org.lab.secrets.core.service.IService;

import java.util.List;

public class RecordService implements IService<Record> {
    private final IRepository<Record> repository;
    public RecordService(IRepository<Record> repository){
        this.repository = repository;
    }

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

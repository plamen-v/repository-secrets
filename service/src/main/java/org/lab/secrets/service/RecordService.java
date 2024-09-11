package org.lab.secrets.service;

import org.lab.secrets.core.repository.IRecordRepository;
import org.lab.secrets.core.model.Record;
import org.lab.secrets.core.service.IRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService implements IRecordService {
    private final IRecordRepository repository;
    public RecordService(IRecordRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Record> getAllRecords() {
        return List.of();
    }

    @Override
    public Record saveRecord(Record record) {
        repository.saveRecord(null);
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

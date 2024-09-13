package org.lab.secrets.service;

import org.lab.secrets.core.model.Secret;
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
        return repository.getAllRecords();
    }

    @Override
    public Record saveRecord(Record record) {
        record = repository.saveRecord(record);
        return record;
    }

    @Override
    public Record deleteRecord(Long recordId) {
        return repository.deleteRecord(recordId);
    }

    @Override
    public Secret saveSecret(Long recordId, String secretKey, String secretValue) {
        return repository.saveSecret(recordId, secretKey, secretValue);
    }

    @Override
    public Secret deleteSecret(Long recorId, String secretKey) {
        return repository.deleteSecret(recorId, secretKey);
    }

    @Override
    public Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue) {
        return repository.isSecretCorrect(recordId, secretKey, secretValue);
    }
}

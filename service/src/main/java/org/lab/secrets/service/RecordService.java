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
    public List<Record> getAllRecords(){
        return repository.getAllRecords();
    }

    @Override
    public Record saveRecord(Record record) throws Exception {
        record = repository.saveRecord(record);
        if(record == null){
            throw new Exception();
        }
        return record;
    }

    @Override
    public Record deleteRecord(Long recordId) throws Exception {
        Record result =  repository.deleteRecord(recordId);
        if(result == null){
            throw new Exception();
        }
        return result;
    }

    @Override
    public Secret saveSecret(Long recordId, String secretKey, String secretValue) throws Exception {
        Secret result = repository.saveSecret(recordId, secretKey, secretValue);
        if(result == null){
            throw new Exception();
        }
        return result;
    }

    @Override
    public Secret deleteSecret(Long recorId, String secretKey) throws Exception {
        Secret result = repository.deleteSecret(recorId, secretKey);
        if(result == null){
            throw new Exception();
        }
        return result;
    }

    @Override
    public Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue) {
        return repository.isSecretCorrect(recordId, secretKey, secretValue);
    }
}

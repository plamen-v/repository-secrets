package org.lab.secrets.core.service;

import java.util.List;
import org.lab.secrets.core.model.Record;
import org.lab.secrets.core.model.Secret;

public interface IRecordService {
    List<Record> getAllRecords();
    Record saveRecord(Record record) throws Exception;
    Record deleteRecord(Long recordId) throws Exception;
    Secret saveSecret(Long recordId, String secretKey, String secretValue) throws Exception;
    Secret deleteSecret(Long recordId, String secretKey) throws Exception;
    Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue);
}

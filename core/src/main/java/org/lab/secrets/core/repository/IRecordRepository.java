package org.lab.secrets.core.repository;

import java.util.List;
import org.lab.secrets.core.model.Record;
import org.lab.secrets.core.model.Secret;

public interface IRecordRepository {
    List<Record> getAllRecords();
    Record saveRecord(Record element);
    Record deleteRecord(Long recordId);
    Secret saveSecret(Long recordId, String secretKey, String secretValue);
    Secret deleteSecret(Long recordId, String secretKey);
    Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue);
}

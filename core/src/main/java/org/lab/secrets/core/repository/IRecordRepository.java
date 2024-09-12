package org.lab.secrets.core.repository;

import java.util.List;
import org.lab.secrets.core.model.Record;

public interface IRecordRepository {
    List<Record> getAllRecords();
    Record saveRecord(Record element);
    Boolean deleteRecord(Long recordId);
    Boolean saveSecret(Long recordId, String secretKey, String secretValue);
    Boolean deleteSecret(Long recordId, String secretKey);
    Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue);
}

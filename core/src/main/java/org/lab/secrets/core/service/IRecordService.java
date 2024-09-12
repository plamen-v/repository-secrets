package org.lab.secrets.core.service;

import java.util.List;
import org.lab.secrets.core.model.Record;

public interface IRecordService {
    List<Record> getAllRecords();
    Record saveRecord(Record record);
    Boolean deleteRecord(Long recordId);
    Boolean saveSecret(Long recordId, String secretKey, String secretValue);
    Boolean deleteSecret(Long recordId, String secretKey);
    Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue);
}

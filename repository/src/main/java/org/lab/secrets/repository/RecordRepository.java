package org.lab.secrets.repository;

import jakarta.persistence.EntityManager;
import org.lab.secrets.core.repository.IRecordRepository;
import org.lab.secrets.core.model.Record;
import org.lab.secrets.repository.model.RecordDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RecordRepository  implements IRecordRepository {
    @Autowired
    private EntityManager em;
    public RecordRepository() {
    }

    @Override
    public List<Record> getAllRecords() {
        return List.of();
    }

    @Override
    @Transactional
    public Record saveRecord(Record record) {
        //RecordDB objNew = em.find(RecordDB.class, 0 );

        RecordDB dbRecord = new RecordDB();
        LocalDateTime dt = LocalDateTime.now();
        dbRecord.setUrl(dt.toString());
        em.persist(dbRecord);
        return new Record();
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {

    }

    @Override
    @Transactional
    public void saveSecret(Long id, String key, String value) {

    }

    @Override
    @Transactional
    public void deleteSecret(Long id, String key) {

    }
}

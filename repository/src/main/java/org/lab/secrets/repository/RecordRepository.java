package org.lab.secrets.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.lab.secrets.core.model.Secret;
import org.lab.secrets.core.repository.IRecordRepository;
import org.lab.secrets.core.model.Record;
import org.lab.secrets.repository.model.RecordDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RecordRepository  implements IRecordRepository {
    @Autowired
    private EntityManager em;
    public RecordRepository() {
    }

    @Override
    public List<Record> getAllRecords() {
        CriteriaBuilder criteriaBuilder =  em.getCriteriaBuilder();
        CriteriaQuery<RecordDB> criteriaQuery = criteriaBuilder.createQuery(RecordDB.class);

        Root<RecordDB> root = criteriaQuery.from(RecordDB.class);
        criteriaQuery.select(root);
        TypedQuery<RecordDB> query = em.createQuery(criteriaQuery);
        List<RecordDB> rulesets = query.getResultList();

        return rulesets.stream().map(x -> x.toRecord()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Record saveRecord(Record record) {
        RecordDB recordDB = em.find(RecordDB.class, record.getId() == null ? 0 : record.getId());
        if(Objects.isNull(recordDB)){
            recordDB = new RecordDB();
        }
        recordDB.setUrl(record.getUrl());
        em.persist(recordDB);

        return recordDB.toRecord();
    }

    @Override
    @Transactional
    public Record deleteRecord(Long recordId) {
        RecordDB recordDB = em.find(RecordDB.class, recordId == null ? 0 : recordId);
        if(!Objects.isNull(recordDB)) {
            em.remove(recordDB);
            return recordDB.toRecord();
        }
        return null;
    }

    @Override
    @Transactional
    public Secret saveSecret(Long recordId, String secretKey, String secretValue) {
        RecordDB recordDB = em.find(RecordDB.class, recordId == null ? 0 : recordId);

        if(!Objects.isNull(recordDB)) {
            //update
            Query query = em.createNativeQuery("UPDATE RECORDS_SECRETS SET secret_key = :secret_key, secret_value = :secret_value WHERE record_id = :record_id AND secret_key = :secret_key");
            query.setParameter("record_id", recordId);
            query.setParameter("secret_key", secretKey);
            query.setParameter("secret_value", secretValue);

            int cnt = query.executeUpdate();
            if(cnt == 0){
                //insert
                query = em.createNativeQuery("INSERT INTO RECORDS_SECRETS(record_id, secret_key, secret_value) VALUES(:record_id, :secret_key, :secret_value)");
                query.setParameter("record_id", recordId);
                query.setParameter("secret_key", secretKey);
                query.setParameter("secret_value", secretValue);
                cnt = query.executeUpdate();
            }

            return new Secret(recordId, secretKey, null);
        }
        return null;
    }

    @Override
    @Transactional
    public Secret deleteSecret(Long recordId, String secretKey) {
        RecordDB recordDB = em.find(RecordDB.class, recordId == null ? 0 : recordId );
        if(!Objects.isNull(recordDB)) {

            if(recordDB.getSecrets().contains(secretKey)){
                recordDB.getSecrets().remove(secretKey);
                em.persist(recordDB);
                return new Secret(recordId, secretKey, null);
            }else{
                return null;
            }
        }

        return null;
    }

    @Override
    public Boolean isSecretCorrect(Long recordId, String secretKey, String secretValue) {
        Query query = em.createNativeQuery("SELECT record_id FROM RECORDS_SECRETS WHERE record_id = :record_id AND secret_key = :secret_key AND secret_value = :secret_value");
        query.setParameter("record_id", recordId);
        query.setParameter("secret_key", secretKey);
        query.setParameter("secret_value", secretValue);
        List<Tuple> result = query.getResultList();

        return result.size() != 0;
    }
}

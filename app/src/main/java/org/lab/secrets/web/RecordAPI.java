package org.lab.secrets.web;

import org.lab.secrets.core.model.Record;
import org.lab.secrets.core.model.Secret;
import org.lab.secrets.core.service.IRecordService;
import org.lab.secrets.core.web.BaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class RecordAPI extends BaseAPI {
    @Autowired
    private IRecordService service;

    public RecordAPI() {
    }

    @GetMapping("/records")
    public List<Record> getAll()  throws Exception {
        return service.getAllRecords();
    }

    @PostMapping("/records")
    public Record create(@RequestBody Record record) throws Exception {
        return service.saveRecord(record);
    }

    @PutMapping("/records")
    public Record update(@RequestBody Record record) throws Exception {
        return service.saveRecord(record);
    }

    @DeleteMapping("/records/{id}")
    public Record delete(@PathVariable("id") Long recordId) throws Exception {
        return service.deleteRecord(recordId);
    }

    @PostMapping("/secrets")
    public Secret createSecret(@RequestBody Secret secret) throws Exception {
        if(!Objects.isNull(secret)) {
            return service.saveSecret(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return null;
    }

    @PutMapping("/secrets")
    public Secret updateSecret(@RequestBody Secret secret) throws Exception {
        if(!Objects.isNull(secret)) {
            return service.saveSecret(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return null;
    }

    @DeleteMapping("/secrets")
    public Secret deleteSecret(@RequestBody Secret secret ) throws Exception {
        if(!Objects.isNull(secret)) {
            return service.deleteSecret(secret.getRecordId(), secret.getKey());
        }
        return null;
    }

    @PostMapping("/secrets/check")
    public Boolean isSecretCorrect(@RequestBody Secret secret ) throws Exception  {
        if(!Objects.isNull(secret)) {
            return service.isSecretCorrect(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return false;
    }
}



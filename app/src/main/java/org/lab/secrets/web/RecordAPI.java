package org.lab.secrets.web;

import org.lab.secrets.core.model.Record;
import org.lab.secrets.core.service.IRecordService;
import org.lab.secrets.web.model.SecretRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class RecordAPI {
    @Autowired
    private IRecordService service;

    public RecordAPI() {
    }

    @GetMapping("/records")
    public List<Record> getAll() {
        return service.getAllRecords();
    }

    @PostMapping("/records")
    public Record create(@RequestBody Record record) {
        return service.saveRecord(record);
    }

    @PutMapping("/records")
    public Boolean update(@RequestBody Record record) {
        service.saveRecord(record);
        return true;
    }

    @DeleteMapping("/records/{id}")
    public Boolean delete(@PathVariable("id") Long recordId) {
        return service.deleteRecord(recordId);
    }

    @PostMapping("/secrets")
    public Boolean createSecret(@RequestBody SecretRequest secret) {
        if(!Objects.isNull(secret)) {
            return service.saveSecret(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return false;
    }

    @PutMapping("/secrets")
    public Boolean updateSecret(@RequestBody SecretRequest secret) {
        if(!Objects.isNull(secret)) {
            return service.saveSecret(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return false;
    }

    @DeleteMapping("/secrets")
    public Boolean deleteSecret(@RequestBody SecretRequest secret ) {
        if(!Objects.isNull(secret)) {
            return service.deleteSecret(secret.getRecordId(), secret.getKey());
        }
        return false;
    }

    @PostMapping("/secrets/check")
    public Boolean isSecretCorrect(@RequestBody SecretRequest secret ) {
        if(!Objects.isNull(secret)) {
            return service.isSecretCorrect(secret.getRecordId(), secret.getKey(), secret.getValue());
        }
        return false;
    }
}



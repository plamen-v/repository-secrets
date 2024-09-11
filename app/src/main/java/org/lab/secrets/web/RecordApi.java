package org.lab.secrets.web;

import org.lab.secrets.core.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RecordApi {
    private final IService<org.lab.secrets.repository.model.Record> service;
    public RecordApi(IService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<String> getAll() {
        List<String> lst = new ArrayList<>();
        lst.add("ABC");
        lst.add("EFG");

        service.saveRecord(null);
        return lst;
    }
}



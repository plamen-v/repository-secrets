package org.lab.secrets;

import org.lab.secrets.core.repository.IRepository;
import org.lab.secrets.core.service.IService;
import org.lab.secrets.repository.RecordRepository;
import org.lab.secrets.repository.model.Record;
import org.lab.secrets.service.RecordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    IRepository<Record> repositoryBuilder(){
        return new RecordRepository();
    }

    @Bean
    IService<Record> serviceBuilder(IRepository<Record> repository){
        return new RecordService(repository);
    }
}

package com.interview.trackuint.config;

import com.interview.trackuint.dao.CartDao;
import com.interview.trackuint.dao.impl.HashMapBackedDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public CartDao dao() {
        return new HashMapBackedDao();
    }

}

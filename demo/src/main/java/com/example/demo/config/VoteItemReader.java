package com.example.demo.config;

import com.example.demo.model.Vote;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.hibernate.SessionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VoteItemReader implements ItemReader<Vote> {

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateCursorItemReader<Vote> reader;

    @Bean
    public ItemReader<Vote> hibernateVoteReader() {
        HibernateCursorItemReader<Vote> reader = new HibernateCursorItemReader<>();
        reader.setSessionFactory(sessionFactory);
        reader.setQueryString("FROM Vote WHERE processed == FALSE"); // Adjust your HQL query as needed
        reader.setFetchSize(100);
        reader.setUseStatelessSession(true);
        this.reader = reader;
        return this.reader;
    }

    @Override
    public Vote read() throws Exception {
        return reader.read();
    }
}

package com.example.demo.config;

import com.example.demo.model.Vote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
@Component
public class VoteWriter {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager transactionManager;

    @Bean
    public ItemWriter<Vote> hibernateVoteWriter() {
        return new ItemWriter<Vote>() {
            @Override
            @Transactional
            public void write(List<? extends Vote> votes) throws Exception {
                var session = sessionFactory.getCurrentSession();
                for (Vote vote : votes) {
                    session.saveOrUpdate(vote);
                    log.info("Saved vote: " + vote);
                }
            }
        };
    }
}

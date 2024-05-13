package com.example.demo.config;

import com.example.demo.model.Vote;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.step.tasklet.Tasklet;

import java.util.List;

@Configuration
public class VoteProcessingJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Bean
    public Job voteProcessingJob(){
        return jobBuilders.get("voteProcessingJob").
                start(taskletStep())
                .next(chunkStep())
                .build();
    }

    @Bean
    public Step taskletStep() {
        return stepBuilders.get("taskletStep")
                .tasklet(tasklet())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilders.get("chunkStep")
                .<Vote, Vote>chunk(20)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Vote> reader() {
        return new VoteItemReader();
    }

    @StepScope
    @Bean
    public ItemProcessor<Vote, Vote> processor() {
        return new VoteProcessor();
    }



    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Executing tasklet step");
            return RepeatStatus.FINISHED;
        };
    }
}

package com.example.demo.config;

import com.example.demo.model.Vote;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class VoteProcessor implements ItemProcessor<Vote, Vote> {
    @Override
    public Vote process(final Vote vote) throws Exception {
        // Example transformation: mark the vote as processed

        //crear una entidad que contenga la nota media calculada y una lista con los votos procesados,
        // para actualizarlos a "processed"
        vote.setProcessed(true);
        return vote;
    }
}

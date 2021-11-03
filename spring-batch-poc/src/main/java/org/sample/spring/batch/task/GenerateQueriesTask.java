package org.sample.spring.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class GenerateQueriesTask implements Tasklet {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(GenerateQueriesTask.class);
    private String directory;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.debug("Generates queries in {}.", directory);
        
        return null;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}

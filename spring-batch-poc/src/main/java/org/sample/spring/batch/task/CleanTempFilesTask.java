package org.sample.spring.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class CleanTempFilesTask implements Tasklet {
    
    private Resource[] resources;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (final Resource resource : resources) {
            resource.getFile().delete();
        }
        
        return null;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }
}

package org.sample.spring.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class SendByFTPTask implements Tasklet {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SendByFTPTask.class);
    private Resource dataFile;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.debug("Send file {} by FTP.", dataFile);
        return null;
    }

    public void setDataFile(Resource dataFile) {
        this.dataFile = dataFile;
    }
}

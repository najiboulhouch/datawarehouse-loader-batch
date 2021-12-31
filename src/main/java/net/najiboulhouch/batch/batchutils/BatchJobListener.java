package net.najiboulhouch.batch.batchutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchJobListener implements JobExecutionListener {
	
	Logger log = LoggerFactory.getLogger(BatchJobListener.class);

	@Override
	public void afterJob(JobExecution arg0) {
		log.info("Begining of job  " + arg0.getJobInstance().getJobName() + " at " + arg0.getStartTime());
		
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		String exitCode = arg0.getExitStatus().getExitCode();
		log.info("End of job  " + arg0.getJobInstance().getJobName() + " at " + arg0.getEndTime() + " with status " + exitCode);
		
	}

}

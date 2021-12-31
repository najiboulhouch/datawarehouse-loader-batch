package net.najiboulhouch.batch.batchutils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchStepSkipper implements SkipPolicy{
	
	Logger log = LoggerFactory.getLogger(BatchStepSkipper.class);
	
	private static Integer SKIP_THRESHOLD = 3;
	
	@Override
	public boolean shouldSkip(Throwable arg0, int arg1) throws SkipLimitExceededException {
		if(arg0 instanceof FlatFileParseException && arg1 < SKIP_THRESHOLD ){
			log.info(((FlatFileParseException) arg0).getLineNumber() + ": " + ((FlatFileParseException) arg0 ).getInput() + " -- " + ((FlatFileParseException) arg0).getMessage());
			return true;
		}
		return false;
	}

	
}

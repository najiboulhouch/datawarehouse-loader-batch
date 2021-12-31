package net.najiboulhouch.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import net.najiboulhouch.batch.batchutils.BatchJobListener;
import net.najiboulhouch.batch.batchutils.BatchStepSkipper;
import net.najiboulhouch.batch.dto.ConvertedInputData;
import net.najiboulhouch.batch.dto.InputData;
import net.najiboulhouch.batch.processor.BatchProcessor;
import net.najiboulhouch.batch.reader.BatchReader;
import net.najiboulhouch.batch.writer.BatchWriter;

@SpringBootApplication
@EnableBatchProcessing
public class BatchingAppApplication {

	@Value("${path.to.the.work.dir}")
	private String workDirPath;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean 
	public BatchReader batchReader(){
			return new BatchReader(workDirPath);
	}
	
	@Bean
	public BatchProcessor batchProcessor(){
		return new BatchProcessor();
	}
	
	@Bean
	public BatchWriter batchWriter(){
		return new BatchWriter();
	}
	
	@Bean
	public BatchJobListener batchJobListener(){
		return new BatchJobListener();
	}
	
	@Bean
	public BatchStepSkipper batchStepSkipper(){
		return new BatchStepSkipper();
	}
	
	
	@Bean
	public Step batchStep(){
		//return stepBuilderFactory.get("stepDatawarehouseLoader").transactionManager(transactionManager)
			//	.<InputData , ConvertedInputData>chunk(1).reader(batchReader()).processor(batchProcessor())
				//.writer(batchWriter()).faultTolerant().skipPolicy(batchStepSkipper()).build();
		return null;
	}
	
	
	@Bean
	public Job jobStep() throws Exception {
		return jobBuilderFactory.get("jobDatawarehouseLoader").repository(jobRepositoryObj()).incrementer(new RunIdIncrementer()).listener(batchJobListener())
				.flow(batchStep()).end().build();
	}
	
	@Bean
	public JobRepository jobRepositoryObj() throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDataSource(dataSource);
		return jobRepositoryFactoryBean.getObject();
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BatchingAppApplication.class, args);
	}

}

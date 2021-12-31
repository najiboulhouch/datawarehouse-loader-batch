package net.najiboulhouch.batch.reader;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import lombok.extern.slf4j.Slf4j;
import net.najiboulhouch.batch.dto.InputData;

@Slf4j
public class BatchReader extends MultiResourceItemReader<InputData> {

	Logger log = LoggerFactory.getLogger(BatchReader.class);

	public BatchReader(String workDirPath) {
		log.info("Batch reader starting to read input data in repository : " + workDirPath);
		this.setResources(getInputResources(workDirPath));
		this.setDelegate(readOneFile());
	}

	/**
	 * 
	 * @return
	 */
	private FlatFileItemReader<InputData> readOneFile() {
		FlatFileItemReader<InputData> resourceReader = new FlatFileItemReader<InputData>();
		
		resourceReader.setLinesToSkip(1);
		
		resourceReader.setLineMapper(new DefaultLineMapper<InputData>() {
			private DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
			private FieldSetMapper<InputData> fieldSetMapper = new BeanWrapperFieldSetMapper<InputData>(){

				@Override
				public InputData mapFieldSet(FieldSet fs) throws BindException {
					InputData inputData = new InputData();
					inputData.setProductName(fs.readString("productName"));
					inputData.setProductEanCode(fs.readString("productEanCode"));
					inputData.setProductType(fs.readString("productType"));
					inputData.setProductAmount(fs.readDouble("productAmount"));
					inputData.setProductQuantity(fs.readInt("productQuantity"));
					inputData.setSupplierName(fs.readString("supplierName"));
					inputData.setSupplierAddress(fs.readString("supplierAddress"));
					inputData.setPurchaserEmail(fs.readString("purchaserEmail"));
					inputData.setPurchaserFirstName(fs.readString("purchaserFirstName"));
					inputData.setPurchaserLastName(fs.readString("purchaserLastName"));
					inputData.setTransactionDate(fs.readDate("transactionDate"));
					return inputData;
				}
				
			};
			@Override
			public InputData mapLine(String line, int lineNumber) throws Exception {
				lineTokenizer.setNames(new String[]{"productName", "productEanCode", "productType", "productQuantity", 
                        "productAmount", "supplierName", "supplierAddress", "purchaserFirstName", 
                        "purchaserLastName", "purchaserEmail", "transactionDate"});
				return fieldSetMapper.mapFieldSet(lineTokenizer.tokenize(line));
			}
			
			
		});
		return resourceReader;
	}

	
	/**
	 * 
	 * @param workDirPath
	 * @return
	 */
	private FileSystemResource[] getInputResources(String workDirPath) {
		File inputDir = new File(workDirPath);
		File[] files = inputDir.listFiles();
		List<File> filesList = Arrays.asList(files);
		
		List<FileSystemResource> inputResources = filesList.stream().filter(file -> file != null && file.isFile())
			.peek(file-> log.info("Reading File : " + file.getAbsolutePath()))
			.map(file -> new FileSystemResource(file))
			.collect(Collectors.toList());
		
		return inputResources.toArray(new FileSystemResource[inputResources.size()]);
	}
	
	
	
	
}

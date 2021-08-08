package com.demo.JobSearchApp.service;

import com.demo.JobSearchApp.dto.JobResponse;
import com.demo.JobSearchApp.util.CustomException;

/**
 * @author ravi
 *
 */
public interface JobSearchService {

	/**
	 * @param emailInfo
	 * @throws CustomException
	 */
	public JobResponse processWorker(Integer workerId) throws CustomException;
}

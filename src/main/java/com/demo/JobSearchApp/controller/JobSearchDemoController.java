package com.demo.JobSearchApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.JobSearchApp.dto.JobResponse;
import com.demo.JobSearchApp.dto.job.JobDTO;
import com.demo.JobSearchApp.dto.worker.WorkerDTO;
import com.demo.JobSearchApp.service.JobSearchService;
import com.demo.JobSearchApp.util.CustomException;

/**
 * @author ravi
 *
 */
@RestController
@RequestMapping("/jobMappingDemo")
public class JobSearchDemoController {

	@Autowired
	JobSearchService jobSearchSrvc;
	
	@GetMapping("/worker/{workerId}")
	public JobResponse getJobMapping( @PathVariable Integer workerId) throws CustomException  {
		JobResponse jobInfo = jobSearchSrvc.processWorker(workerId);	
		return jobInfo;
	}	
}

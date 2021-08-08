package com.demo.JobSearchApp.dto;

import java.util.List;

import com.demo.JobSearchApp.dto.job.JobDTO;
import com.demo.JobSearchApp.dto.worker.WorkerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

public class JobResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
	String statusMessage;
    
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<JobDTO> jobDetails;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
	WorkerDTO workerDetails;
    
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public WorkerDTO getWorkerDetails() {
		return workerDetails;
	}

	public void setWorkerDetails(WorkerDTO workerDetails) {
		this.workerDetails = workerDetails;
	}

	public List<JobDTO> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(List<JobDTO> jobDetails) {
		this.jobDetails = jobDetails;
	}

    
    
}

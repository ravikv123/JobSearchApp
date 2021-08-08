package com.demo.JobSearchApp.service;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.JobSearchApp.dto.JobResponse;
import com.demo.JobSearchApp.dto.job.JobDTO;
import com.demo.JobSearchApp.dto.worker.WorkerDTO;
import com.demo.JobSearchApp.util.CustomException;


/**
 * @author ravi
 * 
 *  */
@Service
public class JobSearchServiceImpl implements JobSearchService {

	
	@Value("${worker.url}")
	private String workerUrl;

	@Value("${jobs.url}")
	private String jobsUrl;

	@Value("${maxJobList}")
	private Integer maxJobList;

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public JobResponse processWorker(Integer workerId) throws CustomException {

		JobResponse jobInfo = new JobResponse();
		WorkerDTO worker = getWorker(workerId);
		if(worker == null)
		{
			throw new CustomException(1, "Worker not found. WorkerId:"+workerId);
		}
		
		if(!worker.getIsActive())
		{
			throw new CustomException(2, "Worker not active. WorkerId:"+workerId);
		}
		jobInfo.setWorkerDetails(worker);
		JobDTO[] jobs = getJobs();
		
		List<JobDTO> matchJob = matchWorkerWithJob(worker, jobs);
		jobInfo.setJobDetails(matchJob);
		
		return jobInfo;
	}
	
	
	/**
	 * @param worker
	 * @param jobs
	 * @return JobDTO
	 * 
	 * Key Algorithm that filters jobs for given worker and limits to the number of jobs
	 * 
	 * Filter criteria 
	 * 1. On Driving License requirement
	 * 2. Distance limitation
	 * 3. Required Certification Match
	 * 
	 * Sort by nearest distance
	 */
	private List<JobDTO> matchWorkerWithJob(WorkerDTO worker, JobDTO[] jobs) {
		
		 List<JobDTO> jobSelected = Arrays.asList(jobs).stream()
		//Filter on Driving lic
		.filter(job -> {
			if(job.isDriverLicenseRequired())
			{
				if(!worker.isHasDriversLicense())
				{
					return false;
				}
			}
			return true;
		})
		//Filter on distance
		.filter(job -> {
			if(distanceCalc(job.getLocation().getLatitude(),job.getLocation().getLongitude(),worker.getJobSearchAddress().getLatitude(),worker.getJobSearchAddress().getLongitude(),worker.getJobSearchAddress().getUnit()) 
					  <= worker.getJobSearchAddress().getMaxJobDistance() )  			
			return true;
			
			return false;
		})
		//Filter on Certificate
		.filter(job -> {
		long count = job.getRequiredCertificates().stream().filter(cert -> worker.getCertificates().indexOf(cert) > -1).count();
		
		if(count == job.getRequiredCertificates().size())
			return true;

		return false;
			
		})
		//Sort by near distance
		.sorted((o1,o2) ->
		{
			if(distanceCalc(o1.getLocation().getLatitude(),o1.getLocation().getLongitude(),worker.getJobSearchAddress().getLatitude(),worker.getJobSearchAddress().getLongitude(),worker.getJobSearchAddress().getUnit())
			>= distanceCalc(o2.getLocation().getLatitude(),o2.getLocation().getLongitude(),worker.getJobSearchAddress().getLatitude(),worker.getJobSearchAddress().getLongitude(),worker.getJobSearchAddress().getUnit()))
				return 1;
			else
				return -1;
		})
		//Limit the results as required
		.limit(maxJobList)
		//Collect all Data
		.collect(Collectors.toList());
		 
		 return jobSelected;
	}

	/**
	 * @param workerId
	 * @return WorkerDTO
	 *  Query the worker Rest service and return the selected workerId
	 */
	public WorkerDTO getWorker(final Integer workerId) {
		ResponseEntity<WorkerDTO[]> response = restTemplate.getForEntity(workerUrl, WorkerDTO[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			Optional<WorkerDTO> workerDTO = Arrays.asList(response.getBody())
					.stream().filter(worker -> workerId.equals(worker.getUserId())).findFirst();
			if(workerDTO.isPresent())
			{
				return workerDTO.get();
			}
		}
		return null;
	}

	/**
	 * @return JobDTO[]
	 * Get all Jobs from Rest Service
	 */
	private JobDTO[] getJobs() {
		ResponseEntity<JobDTO[]> response = restTemplate.getForEntity(jobsUrl, JobDTO[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			JobDTO[] jobDTOList = response.getBody();
			return jobDTOList;
		}
		return null;

	}
	
	
	private double distanceCalc(String lat1S, String lon1S, String lat2S, String lon2S, String unit) {
		
		  double lat1=Double.parseDouble(lat1S);
		  double lon1=Double.parseDouble(lon1S);
		  double lat2=Double.parseDouble(lat2S);
		  double lon2=Double.parseDouble(lon2S);
	      
		  double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, lon1, lat2, lon2);
		  if(unit.equalsIgnoreCase("km"))
			  dist = dist/1000;
	      return (dist);
	    }
}

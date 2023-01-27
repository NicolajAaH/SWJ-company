package dk.sdu.mmmi.companyservice.service;

import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Job;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImplementation implements JobService {

    @Value("${url.jobservice}")
    private String JOB_SERVICE_URL;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Job createJob(Job job) {
        log.info("--> createJob: {}", job);
        System.out.println(JOB_SERVICE_URL);
        ResponseEntity<Job> response = restTemplate.postForEntity(JOB_SERVICE_URL, job, Job.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error creating job: {}", response.getStatusCode());
            return null;
        }
        return response.getBody();
    }

    @Override
    public Job getJob(long id) {
        log.info("--> getJob: {}", id);
        ResponseEntity<Job> response = restTemplate.getForEntity(JOB_SERVICE_URL + "/" + id, Job.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error getting job: {}", response.getStatusCode());
            return null;
        }
        return response.getBody();
    }

    @Override
    public Job updateJob(long id, Job job) {
        log.info("--> updateJob: {}", job);
        ResponseEntity<Job> response = restTemplate.exchange(JOB_SERVICE_URL + "/" + id, HttpMethod.PUT, new HttpEntity<>(job), Job.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error updating job: {}", response.getStatusCode());
            return null;
        }
        return response.getBody();
    }

    @Override
    public void deleteJob(long id) {
        log.info("--> deleteJob: {}", id);
        ResponseEntity<Void> response = restTemplate.exchange(JOB_SERVICE_URL + "/" + id, HttpMethod.DELETE, null, Void.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error deleting job: {}", response.getStatusCode());
        }
    }

}

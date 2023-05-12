package dk.sdu.mmmi.companyservice.outbound;

import dk.sdu.mmmi.companyservice.TestObjects;
import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JobServiceImplementationTest {

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private JobService jobService = new JobServiceImplementation();

    @BeforeEach
    public void setup(){
        ReflectionTestUtils.setField(jobService, "JOB_SERVICE_URL", "http://localhost:8080/api/job");
    }

    @Test
    void createJob() {
        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(new ResponseEntity<>(TestObjects.createMockJob(), HttpStatus.CREATED));
        Job job = jobService.createJob(TestObjects.createMockJob());
        assertThat(job).isNotNull();
    }

    @Test
    void getJob() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(TestObjects.createMockJob(), HttpStatus.OK));
        Job job = jobService.getJob(1L);
        assertThat(job).isNotNull();
    }

    @Test
    void deleteJob() {
        when(restTemplate.exchange(anyString(), any(), any(), any(Class.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        jobService.deleteJob(1L);
    }

    @Test
    void update() {
        when(restTemplate.exchange(anyString(), any(), any(), any(Class.class))).thenReturn(new ResponseEntity<>(TestObjects.createMockJob(), HttpStatus.OK));
        jobService.updateJob(1L, TestObjects.createMockJob());
    }

    @Test
    void getJobsByCompanyId() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(new Job[]{
                TestObjects.createMockJob()
        }, HttpStatus.OK));
        List<Job> jobs = jobService.getJobsByCompanyId(1L);
        assertThat(jobs).isNotNull();
        assertThat(jobs.size()).isEqualTo(1);
    }
}
package dk.sdu.mmmi.companyservice.service.interfaces;

import dk.sdu.mmmi.companyservice.service.model.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);

    Job getJob(long id);

    Job updateJob(long id, Job job);

    void deleteJob(long id);

    List<Job> getJobsByCompanyId(long id);
}

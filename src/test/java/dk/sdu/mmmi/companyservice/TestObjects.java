package dk.sdu.mmmi.companyservice;

import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;
import dk.sdu.mmmi.companyservice.service.model.JobType;

public class TestObjects {

    public static Job createMockJob(){
        Job job = new Job();
        job.setTitle("Test Title");
        job.setDescription("This is a test job");
        job.setLocation("Test City");
        job.setSalary(100000.00);
        job.setJobType(JobType.BACKEND);
        job.setCreatedAt(new java.util.Date());
        job.setCompany(createMockCompany());
        job.setId(1L);
        job.setExpiresAt(new java.util.Date());
        job.setUpdatedAt(new java.util.Date());
        return job;
    }

    public static Company createMockCompany(){
        Company company = new Company();
        company.setName("Mock Company");
        company.setWebsite("https://test.dk");
        company.setCreatedAt(new java.util.Date());
        company.setUpdatedAt(new java.util.Date());
        company.setId(1L);
        company.setPhone(12345678);
        company.setEmail("testIT@test.dk");
        return company;
    }
}

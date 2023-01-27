package dk.sdu.mmmi.companyservice.inbound;

import dk.sdu.mmmi.companyservice.service.interfaces.CompanyService;
import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;
import dk.sdu.mmmi.companyservice.service.model.LoginRequest;
import dk.sdu.mmmi.companyservice.service.model.LogoutRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    private final JobService jobService;

    @PostMapping("/register")
    public void registerCompany(@RequestBody Company company) {
        log.info("Company registered: " + company);
        companyService.create(company);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        log.info("Company logged in: " + loginRequest);
        //call authentication service
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        log.info("Company logged out: " + logoutRequest);
        //call authentication service
    }

    @PostMapping("/postjob")
    public void postJob(@RequestBody Job job) {
        log.info("Job posted: " + job);
        jobService.createJob(job);
    }

    @PutMapping("/{id}")
    public void updateCompany(@RequestBody Company company, @PathVariable Long id) {
        log.info("Company updated: " + company);
        companyService.update(id, company);
    }
}

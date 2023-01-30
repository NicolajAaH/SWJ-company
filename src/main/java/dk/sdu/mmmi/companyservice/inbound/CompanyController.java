package dk.sdu.mmmi.companyservice.inbound;

import dk.sdu.mmmi.companyservice.service.interfaces.AuthenticationService;
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

import java.util.HashSet;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    private final JobService jobService;

    private final AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") long id) {
        log.info("Get company: " + id);
        Company company = companyService.findById(id);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setJobs(new HashSet<>(jobService.getJobsByCompanyId(id)));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Company> getCompany(@PathVariable("email") String email) {
        log.info("Get company: " + email);
        Company company = companyService.findByEmail(email);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setJobs(new HashSet<>(jobService.getJobsByCompanyId(company.getId())));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/register")
    public void registerCompany(@RequestBody Company company) {
        log.info("Company registered: " + company);
        companyService.create(company);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        log.info("Company logged in: " + loginRequest);
        authenticationService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        log.info("Company logged out: " + logoutRequest);
        authenticationService.logout(logoutRequest);
    }


    @PutMapping("/{id}")
    public void updateCompany(@RequestBody Company company, @PathVariable Long id) {
        log.info("Company updated: " + company);
        companyService.update(id, company);
    }
}

package dk.sdu.mmmi.companyservice.inbound;

import dk.sdu.mmmi.companyservice.service.interfaces.CompanyService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    /**
     * Get company by id
     * @param id company id
     * @return the found company wrapped in a ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") long id) {
        log.info("Get company: " + id);
        Company company = companyService.findById(id);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setJobs(new HashSet<>(companyService.getJobsByCompanyId(id)));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    /**
     * Get company by email
     * @param email company email
     * @return the found company wrapped in a ResponseEntity
     */
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Company> getCompany(@PathVariable("email") String email) {
        log.info("Get company: " + email);
        Company company = companyService.findByEmail(email);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Job> jobList = companyService.getJobsByCompanyId(company.getId());
        if(jobList == null || jobList.isEmpty())
            company.setJobs(new HashSet<>());
        else
            company.setJobs(new HashSet<>(jobList));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    /**
     * Get all companies
     * @param email company email
     * @param company the company to update
     * @return OK if the company was updated, NOT_FOUND if the company was not found
     */
    @PutMapping("/byEmail/{email}")
    public ResponseEntity<Void> updateCompany(@PathVariable("email") String email, @RequestBody Company company) {
        log.info("Update company: " + email);
        Company originalCompany = companyService.findByEmail(email);

        if (originalCompany == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyService.update(originalCompany.getId(), company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Register a company
     * @param company the company to create
     * @return the created company wrapped in a ResponseEntity
     */
    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody Company company) {
        log.info("Company registered: " + company);
        Company createdCompany = companyService.create(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    /**
     * Update a company by id
     * @param company the company to update
     * @param id company id
     * @return OK if the company was updated, NOT_FOUND if the company was not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable Long id) {
        log.info("Company updated: " + company);
        Company updatedCompany = companyService.update(id, company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }
}

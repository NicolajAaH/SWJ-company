package dk.sdu.mmmi.companyservice.service;

import dk.sdu.mmmi.companyservice.service.interfaces.CompanyService;
import dk.sdu.mmmi.companyservice.service.interfaces.DatabaseService;
import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImplementation implements CompanyService {

    private final DatabaseService databaseService;

    private final JobService jobService;

    @Override
    public List<Company> findAll() {
        log.info("--> findAll");
        return databaseService.findAllCompanies();
    }

    @Override
    public Company create(Company company) {
        log.info("--> create: {}", company);
        return databaseService.createCompany(company);
    }

    @Override
    public Company findById(Long id) {
        log.info("--> findById: {}", id);
        return databaseService.findCompanyById(id);
    }

    @Override
    public Company update(Long id, Company company) {
        log.info("--> update: {}", company);
        company.setUpdatedAt(new Date());
        company.setId(id);
        return databaseService.updateCompany(id, company);
    }

    @Override
    public void delete(Long id) {
        log.info("--> delete: {}", id);
        databaseService.deleteCompany(id);
    }

    @Override
    public Company findByEmail(String email) {
        log.info("--> findByEmail: {}", email);
        return databaseService.findCompanyByEmail(email);
    }

    @Override
    public List<Job> getJobsByCompanyId(long id) {
        log.info("--> getJobsByCompanyId: {}", id);
        return jobService.getJobsByCompanyId(id);
    }
}

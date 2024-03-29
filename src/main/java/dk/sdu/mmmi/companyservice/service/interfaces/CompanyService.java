package dk.sdu.mmmi.companyservice.service.interfaces;

import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company create(Company company);

    Company findById(Long id);

    Company update(Long id, Company company);

    void delete(Long id);

    Company findByEmail(String email);

    List<Job> getJobsByCompanyId(long id);
}

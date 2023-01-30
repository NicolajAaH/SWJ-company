package dk.sdu.mmmi.companyservice.service.interfaces;

import dk.sdu.mmmi.companyservice.service.model.Company;

import java.util.List;

public interface DatabaseService {

    List<Company> findAllCompanies();

    Company createCompany(Company company);

    Company findCompanyById(Long id);

    Company updateCompany(Long id, Company company);

    void deleteCompany(Long id);

    Company findCompanyByEmail(String email);
}

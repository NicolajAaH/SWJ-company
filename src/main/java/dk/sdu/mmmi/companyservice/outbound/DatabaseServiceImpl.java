package dk.sdu.mmmi.companyservice.outbound;

import dk.sdu.mmmi.companyservice.outbound.repository.CompanyRepository;
import dk.sdu.mmmi.companyservice.service.interfaces.DatabaseService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseServiceImpl implements DatabaseService {

    private final CompanyRepository companyRepository;


    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}

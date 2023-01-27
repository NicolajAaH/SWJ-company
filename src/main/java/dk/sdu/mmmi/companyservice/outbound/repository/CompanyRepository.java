package dk.sdu.mmmi.companyservice.outbound.repository;

import dk.sdu.mmmi.companyservice.service.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

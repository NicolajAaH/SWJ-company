package dk.sdu.mmmi.companyservice.service;

import dk.sdu.mmmi.companyservice.TestObjects;
import dk.sdu.mmmi.companyservice.service.interfaces.CompanyService;
import dk.sdu.mmmi.companyservice.service.interfaces.DatabaseService;
import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import dk.sdu.mmmi.companyservice.service.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class CompanyServiceImplementationTest {

    private CompanyService companyService;

    @Mock
    private DatabaseService databaseService;

    @Mock
    private JobService jobService;

    @BeforeEach
    public void setUp() {
        companyService = new CompanyServiceImplementation(databaseService, jobService);
    }

    @Test
    public void testFindAll() {
        List<Company> companies = new ArrayList<>(){{
            add(TestObjects.createMockCompany());
        }};
        when(databaseService.findAllCompanies()).thenReturn(companies);

        List<Company> result = companyService.findAll();
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    public void testCreate() {
        Company company = TestObjects.createMockCompany();
        when(databaseService.createCompany(any(Company.class))).thenReturn(company);

        Company result = companyService.create(company);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(company);
    }

    @Test
    public void testFindById() {
        Company company = TestObjects.createMockCompany();
        when(databaseService.findCompanyById(anyLong())).thenReturn(company);

        Company result = companyService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(company);
    }

    @Test
    public void testUpdate() {
        Company company = TestObjects.createMockCompany();
        when(databaseService.updateCompany(anyLong(), any(Company.class))).thenReturn(company);

        Company result = companyService.update(1L, company);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(company);
    }

    @Test
    public void testDelete() {
        doNothing().when(databaseService).deleteCompany(anyLong());
        companyService.delete(1L);
    }

    @Test
    public void testFindByEmail() {
        Company company = TestObjects.createMockCompany();
        when(databaseService.findCompanyByEmail(anyString())).thenReturn(company);

        Company result = companyService.findByEmail("test@test.com");
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(company);
    }

    @Test
    public void testGetJobsByCompanyId() {
        List<Job> jobs = new ArrayList<>(){{
            add(TestObjects.createMockJob());
        }};
        when(databaseService.findCompanyByEmail(anyString())).thenReturn(TestObjects.createMockCompany());
        when(jobService.getJobsByCompanyId(anyLong())).thenReturn(jobs);

        List<Job> result = companyService.getJobsByCompanyId(1L);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

}

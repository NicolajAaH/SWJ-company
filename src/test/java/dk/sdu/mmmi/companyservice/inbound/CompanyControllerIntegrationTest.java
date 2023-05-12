package dk.sdu.mmmi.companyservice.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.sdu.mmmi.companyservice.TestObjects;
import dk.sdu.mmmi.companyservice.service.application.CompanyServiceApplication;
import dk.sdu.mmmi.companyservice.service.interfaces.JobService;
import dk.sdu.mmmi.companyservice.service.model.Company;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompanyServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;


    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testGetCompanyById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
    }

    @Test
    public void testGetCompanyByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/byEmail/test@test.dk"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()));
    }

    @Test
    @DirtiesContext
    public void testUpdateCompanyByEmail() throws Exception {
        Company updatedCompany = TestObjects.createMockCompany();
        updatedCompany.setName("Updated Company");
        updatedCompany.setEmail("updated@test.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/companies/byEmail/test@test.dk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCompany)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DirtiesContext
    public void testRegisterCompany() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/companies/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestObjects.createMockCompany())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DirtiesContext
    public void testUpdateCompanyById() throws Exception {
        Company updatedCompany = TestObjects.createMockCompany();
        updatedCompany.setName("Updated Company");
        updatedCompany.setEmail("updated@test.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/companies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCompany)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

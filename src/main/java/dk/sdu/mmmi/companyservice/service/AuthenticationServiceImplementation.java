package dk.sdu.mmmi.companyservice.service;

import dk.sdu.mmmi.companyservice.service.interfaces.AuthenticationService;
import dk.sdu.mmmi.companyservice.service.model.LoginRequest;
import dk.sdu.mmmi.companyservice.service.model.LogoutRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    @Value("${url.authenticationservice}")
    private String JOB_SERVICE_URL;

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public void login(LoginRequest loginRequest) {
        log.info("--> login: {}", loginRequest);
        ResponseEntity<Void> response = restTemplate.postForEntity(JOB_SERVICE_URL + "/login", loginRequest, Void.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error logging in: {}", response.getStatusCode());
        }
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        log.info("--> logout: {}", logoutRequest);
        ResponseEntity<Void> response = restTemplate.postForEntity(JOB_SERVICE_URL + "/logout", logoutRequest, Void.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("Error logging out: {}", response.getStatusCode());
        }
    }
}

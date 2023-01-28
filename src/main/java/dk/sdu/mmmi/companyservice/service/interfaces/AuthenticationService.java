package dk.sdu.mmmi.companyservice.service.interfaces;

import dk.sdu.mmmi.companyservice.service.model.LoginRequest;
import dk.sdu.mmmi.companyservice.service.model.LogoutRequest;

public interface AuthenticationService {
    void login(LoginRequest loginRequest);

    void logout(LogoutRequest logoutRequest);
}

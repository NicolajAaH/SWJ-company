package dk.sdu.mmmi.companyservice.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequest {

    private String email;

    @ToString.Exclude
    private String password;

}

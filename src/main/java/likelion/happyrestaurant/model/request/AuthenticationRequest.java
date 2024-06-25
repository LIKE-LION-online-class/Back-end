package likelion.happyrestaurant.model.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String userName;
    String password;
}


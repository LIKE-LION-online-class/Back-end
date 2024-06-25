package likelion.happyrestaurant.controller;

import com.nimbusds.jose.JOSEException;
import likelion.happyrestaurant.model.request.AuthenticationRequest;
import likelion.happyrestaurant.model.request.CheckTokenRequest;
import likelion.happyrestaurant.model.response.ApiResponse;
import likelion.happyrestaurant.model.response.AuthenticationResponse;
import likelion.happyrestaurant.model.response.CheckTokenResponse;
import likelion.happyrestaurant.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/checkToken")
    ApiResponse<CheckTokenResponse> checkToken(@RequestBody CheckTokenRequest request) throws ParseException, JOSEException {
        CheckTokenResponse response = authenticationService.checkToken(request);
        return ApiResponse.<CheckTokenResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/refreshToken")
    ApiResponse<CheckTokenResponse> refreshToken(@RequestBody CheckTokenRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<CheckTokenResponse>builder().result(result).build();
    }
}

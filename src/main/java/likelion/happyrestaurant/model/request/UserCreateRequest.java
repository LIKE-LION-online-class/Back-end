package likelion.happyrestaurant.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 8, max = 20, message = "username must be between 2 and 30 characters")
    String userName;
    @Size(min = 8, max = 20, message = "password must be between 2 and 30 characters")
    String password;
    @Email(message = "wrong format email")
    String email;
    String fullName;
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    String phoneNumber;
    @Past(message = "date of bith date must be in the past")
    LocalDate dob;
    Set<String> roles;
}
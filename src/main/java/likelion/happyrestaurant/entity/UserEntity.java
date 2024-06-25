package likelion.happyrestaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false, unique = true)
    String userName;
    @Column(nullable = false)
    String password;
    String fullName;
    String email;
    String phoneNumber;
    LocalDate dob;
    String address;
    Set<String> roles;
}

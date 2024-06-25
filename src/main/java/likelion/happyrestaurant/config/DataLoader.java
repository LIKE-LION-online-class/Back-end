package likelion.happyrestaurant.config;

import jakarta.transaction.Transactional;
import likelion.happyrestaurant.entity.UserEntity;
import likelion.happyrestaurant.enums.Role;
import likelion.happyrestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataLoader implements CommandLineRunner {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        var adminRole = new HashSet<String>();
        adminRole.add(Role.ADMIN.name());
        var businessRole = new HashSet<String>();
        businessRole.add(Role.BUSINESS.name());
        var userRole = new HashSet<String>();
        userRole.add(Role.USER.name());
        UserEntity adminUser = UserEntity.builder().userName("admin").password(passwordEncoder.encode("admin")).
                roles(adminRole).build();
        UserEntity businessUser = UserEntity.builder().userName("business").password(passwordEncoder.encode("business"))
                .roles(businessRole).build();
        UserEntity user = UserEntity.builder().userName("user").password(passwordEncoder.encode("user"))
                .roles(userRole).build();
        if (userRepository.findByUserName("admin").isEmpty()) {

            adminUser = userRepository.save(adminUser);
        }
        if (userRepository.findByUserName("business").isEmpty()) {
            businessUser = userRepository.save(businessUser);
        }
        if (userRepository.findByUserName("user").isEmpty()) {
            user = userRepository.save(user);
        }
        log.warn("user created by default password");
    }
}

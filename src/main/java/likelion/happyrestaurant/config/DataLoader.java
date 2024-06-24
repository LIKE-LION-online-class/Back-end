package likelion.happyrestaurant.config;

import jakarta.transaction.Transactional;
import likelion.happyrestaurant.entity.RoleEntity;
import likelion.happyrestaurant.entity.UserEntity;
import likelion.happyrestaurant.repository.RoleRepository;
import likelion.happyrestaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        // Tạo mới RoleEntity
//        RoleEntity adminRole = RoleEntity.builder()
//                .name("ADMIN")
//                .build();
//        adminRole = roleRepository.save(adminRole); // Lưu và quản lý RoleEntity
//
//        // Tạo mới UserEntity
//        UserEntity user = UserEntity.builder()
//                .userName("johndoe")
//                .password("password")
//                .build();
//
//        // Thêm RoleEntity đã được quản lý vào UserEntity
//        user.getRoles().add(adminRole);
//
//        // Lưu UserEntity vào cơ sở dữ liệu
//        userRepository.save(user);
        RoleEntity businessRole = RoleEntity.builder().name("BUSINESS").build();
        RoleEntity userRole = RoleEntity.builder().name("USER").build();
        if (roleRepository.findByName("BUSINESS").isEmpty()) {
            businessRole = roleRepository.save(businessRole);
        }
        if (roleRepository.findByName("USER").isEmpty()) {
            userRole = roleRepository.save(userRole);
        }
        UserEntity businessUser = UserEntity.builder().userName("business").password("business").build();
        businessUser.getRoles().add(businessRole);
        UserEntity user = UserEntity.builder().userName("user").password("user").build();
        businessUser.getRoles().add(userRole);
        if (userRepository.findByUserName("business").isEmpty()) {
            userRepository.save(businessUser);
        }
        if (userRepository.findByUserName("user").isEmpty()) {
            userRepository.save(user);
        }
        log.warn("user created by default password");
    }
}

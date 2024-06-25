package likelion.happyrestaurant.service;

import likelion.happyrestaurant.entity.UserEntity;
import likelion.happyrestaurant.exception.AppException;
import likelion.happyrestaurant.exception.ErrorCode;
import likelion.happyrestaurant.mapper.UserMapper;
import likelion.happyrestaurant.model.request.UserCreateRequest;
import likelion.happyrestaurant.model.request.UserUpdateRequest;
import likelion.happyrestaurant.model.response.UserResponse;
import likelion.happyrestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;


    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(request);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(findUserById(id));
    }

    public UserResponse updateUser(UserUpdateRequest request) {
        UserEntity userEntity = findUserById(request.getId());
        userMapper.updateUser(userEntity, request);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }


    public UserResponse deleteUser(String id) {
        UserEntity userEntity = findUserById(id);
        userEntity.setDeleteDate(LocalDate.now());
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    UserEntity findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public UserResponse getInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(userEntity);
    }
}



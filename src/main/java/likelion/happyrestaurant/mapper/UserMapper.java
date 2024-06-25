package likelion.happyrestaurant.mapper;


import likelion.happyrestaurant.entity.UserEntity;
import likelion.happyrestaurant.model.request.UserCreateRequest;
import likelion.happyrestaurant.model.request.UserUpdateRequest;
import likelion.happyrestaurant.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    UserEntity toUserEntity(UserCreateRequest request);

    @Mapping(target = "password", ignore = true)
    UserResponse toUserResponse(UserEntity entity);

    void updateUser(@MappingTarget UserEntity userEntity, UserUpdateRequest request);

}

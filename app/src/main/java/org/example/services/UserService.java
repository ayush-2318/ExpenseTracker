package org.example.services;

import org.example.entities.UserInfo;
import org.example.entities.UserInfoDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){
        Function<UserInfo,UserInfo> updatingUser = user->{
            return userRepository.save(user);
        };
        Supplier<UserInfo> createUser=()->{
            return userRepository.save(userInfoDto.transformToUserInfo());
        };
        UserInfo userInfo=userRepository.findByUserId(userInfoDto.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);

        return UserInfoDto.builder()
                .userId(userInfoDto.getUserId())
                .firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .phoneNumber(userInfoDto.getPhoneNumber())
                .profilePicture(userInfoDto.getProfilePicture())
                .build();
    }

    public UserInfoDto getUser(UserInfoDto userInfoDto) throws Exception{
        Optional<UserInfo> userInfoOpt=userRepository.findByUserId(userInfoDto.getUserId());
        if(userInfoOpt.isEmpty()){
            throw new Exception("User not found");
        }

        UserInfo userInfo=userInfoOpt.get();
        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getEmail(),
                userInfo.getPhoneNumber(),
                userInfo.getProfilePicture()
        );
    }

}

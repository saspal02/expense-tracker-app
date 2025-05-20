package userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userservice.entities.UserInfo;
import userservice.entities.UserInfoDto;
import userservice.repository.UserRepository;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto) {
        UnaryOperator<UserInfo> updatingUser = User -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);
        return mapToDto(userInfo);
    }

    public UserInfoDto getUser(String userId) throws Exception {
        Optional<UserInfo> userInfoDtoOpt = userRepository.findByUserId(userId);
        if (userInfoDtoOpt.isEmpty()) {
            throw new Exception("User not found");
        }
        UserInfo userInfo = userInfoDtoOpt.get();
        return mapToDto(userInfo);

    }

    private UserInfoDto mapToDto(UserInfo userInfo) {
        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getEmail()

        );
    }


}

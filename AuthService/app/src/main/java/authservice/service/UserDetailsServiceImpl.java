package authservice.service;

import authservice.entities.UserInfo;
import authservice.eventProducer.UserInfoEvent;
import authservice.eventProducer.UserInfoProducer;
import authservice.model.UserInfoDto;
import authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Data
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserInfoProducer userInfoProducer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExists(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUsername());

    }

    public String signupUser(UserInfoDto userInfoDto) {
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (Objects.nonNull(checkIfUserAlreadyExists(userInfoDto))) {
            return null;
        }

        String userId = UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(userId,userInfoDto.getUsername(),userInfoDto.getPassword(),new HashSet<>());
        userRepository.save(userInfo);
        userInfoProducer.sendEventToKafka(userInfoEventToPublish(userInfoDto,userId));
        return userId;

    }

    public String getUserByUsername(String userName) {
        return Optional.of(userRepository.findByUsername(userName)).map(UserInfo::getUserId).orElse(null);
    }

    private UserInfoEvent userInfoEventToPublish(UserInfoDto userInfoDto,String userId) {
        return UserInfoEvent.builder()
                .userId(userId)
                .firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .build();
    }
}

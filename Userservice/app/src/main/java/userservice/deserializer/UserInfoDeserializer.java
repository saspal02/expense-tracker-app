package userservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import userservice.entities.UserInfoDto;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public UserInfoDto deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        UserInfoDto user = null;
        try {
          user = mapper.readValue(bytes, UserInfoDto.class);
        } catch (Exception ex) {
            System.out.println("Can not deserialize");
        }
        return user;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}

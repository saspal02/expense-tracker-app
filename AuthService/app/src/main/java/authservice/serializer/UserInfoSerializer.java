package authservice.serializer;

import authservice.model.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;

import java.io.OutputStream;

public class UserInfoSerializer implements Serializer<UserInfoDto> {

    @Override
    public byte[] serialize(UserInfoDto userInfoDto) throws SerializationException {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(userInfoDto).getBytes();
        } catch (Exception ex) {
            System.out.println("Can't Serialize");
        }

        return retVal;

    }

    @Override
    public void serialize(UserInfoDto userInfoDto, OutputStream out) throws SerializationException {

    }


}

package org.example.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.entities.UserInfoDto;

import java.nio.ByteBuffer;
import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDto> {
    private final ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1) {

        UserInfoDto user=null;
        try {
            user=objectMapper.readValue(arg1,UserInfoDto.class);
        }catch (Exception ex){
            System.out.println("Not able to deserialize");
        }
        return user;
    }


    @Override
    public void close() {

    }
}

package org.example.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.eventProducer.UserInfoEvent;
import org.example.model.UserInfoDto;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoEvent> {

    private final ObjectMapper objectMapper;

    public UserInfoSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String arg0, UserInfoEvent arg1) {
        byte[] retVal=null;
       // ObjectMapper objectMapper=new ObjectMapper();
        //objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        try {
            retVal=objectMapper.writeValueAsBytes(arg1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return retVal ;
    }



    @Override
    public void close() {

    }
}

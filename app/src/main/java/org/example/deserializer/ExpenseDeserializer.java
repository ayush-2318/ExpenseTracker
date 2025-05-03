package org.example.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.model.ExpenseDto;

import java.nio.ByteBuffer;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    private final ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public ExpenseDto deserialize(String arg0, byte[] arg1) {
        ExpenseDto expense=null;
        try{
            expense=objectMapper.readValue(arg1,ExpenseDto.class);
        }catch (Exception ex){
            System.out.println("Not able to serialise the expense");
        }
        return  expense;
    }


    @Override
    public void close() {

    }
}

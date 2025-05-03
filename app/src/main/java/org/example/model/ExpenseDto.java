package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {
    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("merchant")
    private String merchant;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    public ExpenseDto(String json){
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            ExpenseDto expenseDto=objectMapper.readValue(json,ExpenseDto.class);
            this.externalId=expenseDto.externalId;
            this.amount=expenseDto.amount;
            this.userId=expenseDto.userId;
            this.merchant=expenseDto.merchant;
            this.currency=expenseDto.currency;
            this.createdAt=expenseDto.createdAt;
        }catch (Exception ex){
            throw new RuntimeException("Failed to deserialised");
        }
    }
}

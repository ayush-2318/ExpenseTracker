package org.example.consumer;

import org.example.model.ExpenseDto;
import org.example.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseConsumer {

    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    @KafkaListener(topics = "expenseinfo" ,groupId = "expense-info-group" ,containerFactory = "kafkaListenerContainerFactory")
    public void listen(ExpenseDto expenseDto){
        try {
            expenseService.createExpense(expenseDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

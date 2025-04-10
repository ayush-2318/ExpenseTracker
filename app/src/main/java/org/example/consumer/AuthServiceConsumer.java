package org.example.consumer;

import org.apache.catalina.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {


    private UserRepository userRepository;

    @Autowired
    AuthServiceConsumer(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @KafkaListener(topics = "testing2", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Object eventData){
        try {
                System.out.println("eventdata"+eventData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

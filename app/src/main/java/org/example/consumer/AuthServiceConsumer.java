package org.example.consumer;

import org.apache.catalina.User;
import org.example.entities.UserInfo;
import org.example.entities.UserInfoDto;
import org.example.repository.UserRepository;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {


    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "testing2", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(UserInfoDto eventData){
        try {
            // to make it transactional
            userService.createOrUpdateUser(eventData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

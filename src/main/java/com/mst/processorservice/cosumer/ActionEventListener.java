package com.mst.processorservice.cosumer;
import com.mst.processorservice.model.ActionEvent;
import com.mst.processorservice.service.ActionExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ActionEventListener {

    @Autowired
    private ActionExecutionService actionExecutionService;

    @KafkaListener(
            topics="actions_to_process",
            groupId ="${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void onActionReceived(ActionEvent actionEvent ) {

        actionExecutionService.executeAction(actionEvent);


    }




}

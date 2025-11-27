package com.mst.processorservice.service;

import com.mst.processorservice.condition.ConditionEvaluator;
import com.mst.processorservice.model.ActionEvent;
import com.mst.processorservice.model.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionExecutionService {

    @Autowired
    ConditionEvaluator conditionEvaluator;

    @Autowired
    KafkaNotificationProducer  notificationProducer;


    public void executeAction(ActionEvent actionEvent) {


        boolean passed=conditionEvaluator.evaluateCondition(actionEvent.conditions());
        if(!passed){
            return;
        }
        if (actionEvent.action_type()==ActionType.EMAIL)
        {
            notificationProducer.sendEmailNotification(actionEvent);
        }
        else if (actionEvent.action_type()==ActionType.SMS)
        {
            notificationProducer.sendSmsNotification(actionEvent);
        }
    }

}

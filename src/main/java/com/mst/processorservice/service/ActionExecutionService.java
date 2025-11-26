package com.mst.processorservice.service;

//import com.mst.processorservice.client.MetricServiceClient;
import com.mst.processorservice.client.MetricClient;
import com.mst.processorservice.condition.ConditionEvaluator;
import com.mst.processorservice.model.ActionEvent;
import com.mst.processorservice.model.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionExecutionService {

    @Autowired
    ConditionEvaluator conditionEvaluator;


    @Autowired
    MetricClient metricClient;



    @Autowired
    KafkaNotificationProducer  notificationProducer;



    public void executeAction(ActionEvent actionEvent) {

        List<Integer> metricIds=extractMetricIds(actionEvent.conditions());

        String idsQuery=convertIdsToQuery(metricIds);

        Map<Integer,Boolean> metricResults=metricClient.validateMetricIds(idsQuery);


        boolean passed=conditionEvaluator.evaluateCondition(actionEvent.conditions(), metricResults);


        if (!passed)
        {
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


    private List<Integer> extractMetricIds(List<List<Integer>> conditions) {

        return conditions.stream()
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private String convertIdsToQuery(List<Integer> metricIds) {
        return metricIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}

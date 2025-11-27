package com.mst.processorservice.condition;


import com.mst.processorservice.client.MetricClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditionEvaluator {

    @Autowired
    MetricClient metricClient;

    public boolean evaluateCondition(List<List<Integer>> conditions) {

        for (List<Integer> group: conditions) {

            try
            {
                List<Long> metricIds = group.stream().map(Long::valueOf).toList();

                metricClient.validateMetricIds(metricIds);
                return true;
            }catch (Exception ex)
            {

            }

        }
    return false;

    }
}


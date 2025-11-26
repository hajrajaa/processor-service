package com.mst.processorservice.condition;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

@Component
public class ConditionEvaluator {

    public boolean evaluateCondition(List<List<Integer>> conditions , Map<Integer,Boolean> metricResults) {


        for (List<Integer> group: conditions) {

            boolean groupPassed=true;

            for(Integer metricId: group) {

                Boolean metricResult = metricResults.get(metricId);

                if (metricResult == null||!metricResult) {
                    groupPassed=false;
                    break;
                }
            }
            // or passed
            if (groupPassed) {
                return true;
            }
        }
        // no group passed
        return false;


    }
}

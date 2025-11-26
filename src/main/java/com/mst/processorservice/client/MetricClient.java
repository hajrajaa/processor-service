package com.mst.processorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "metric-service", url = "${services.metric.url}")
public interface MetricClient {

   @GetMapping
   Map<Integer,Boolean> validateMetricIds(@RequestParam("ids") String ids);

}

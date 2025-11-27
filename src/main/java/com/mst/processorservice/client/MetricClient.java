package com.mst.processorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "metric-service", url = "${services.metric.url}")
public interface MetricClient {

    @PostMapping("/metrics/check")
    ResponseEntity<Void> validateMetricIds(@RequestBody List<Long> metricIds);



}

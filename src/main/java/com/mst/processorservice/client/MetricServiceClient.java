package com.mst.processorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


//@FeignClient(name = "metric-service", url = "http://metric-service:8083")
//public interface MetricServiceClient {
//
//    @PostMapping("/metrics/check")
//    ResponseEntity<Void> validateMetricIds(@RequestBody List<Long> metricsIds);
//
//}

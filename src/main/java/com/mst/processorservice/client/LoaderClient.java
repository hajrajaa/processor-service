package com.mst.processorservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "metric-service", url = "${services.metric.url}")
public interface LoaderClient {

    @PostMapping("/loader/scan/manual")
    String triggerManualScan();
}

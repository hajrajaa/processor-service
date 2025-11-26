package com.mst.processorservice.controller;


import com.mst.processorservice.client.LoaderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processor")
public class ProcessorController {

    @Autowired
    LoaderClient loaderClient;


    @PostMapping("/trigger-scan")
    public String triggerScan(){
        return loaderClient.triggerManualScan();
    }
}

package org.ac.cst8277.ahmed.basit.controller;

import org.ac.cst8277.ahmed.basit.service.SubscriberService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<BaseResponse> createSubscription(@RequestParam("subscriberId") long subscriberId,
                                                           @RequestParam("publisherId") long publisherId
                                                           ) {
        return ResponseEntity.accepted().body(subscriberService.createSubscription(subscriberId, publisherId));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> confirmSubscription(@RequestParam("subscriberId") long subscriberId,
                                                            @RequestParam("publisherId") long publisherId) {
        return ResponseEntity.accepted().body(subscriberService.confirmSubscription(subscriberId, publisherId));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> removeSubscription(@RequestParam("subscriberId") long subscriberId,
                                                           @RequestParam("publisherId") long publisherId) {
        return ResponseEntity.accepted().body(subscriberService.removeSubscription(subscriberId, publisherId));
    }

    @GetMapping("/listSubscriptions")
    public ResponseEntity<BaseResponse> listSubscriptions(@RequestParam("subscriberId") long subscriberId) {
        return ResponseEntity.accepted().body(subscriberService.listSubscriptions(subscriberId));
    }

    @GetMapping("/subscription/messages")
    public ResponseEntity<BaseResponse> getMessages(@RequestParam("subscriberId") long subscriberId) {
        return ResponseEntity.accepted().body(subscriberService.listMessages(subscriberId));
    }

}

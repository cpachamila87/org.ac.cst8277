package org.ac.cst8277.ahmed.basit.controller;

import org.ac.cst8277.ahmed.basit.service.PublisherService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(path = "/api/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<BaseResponse> getPublishers() {
        return ResponseEntity.accepted().body(publisherService.getPublishers());
    }

    @GetMapping("/subscribers")
    public ResponseEntity<BaseResponse> getSubscribersByPubId(@RequestParam("publisherId") long publisherId) {
        return ResponseEntity.accepted().body(publisherService.getSubscribersByPubId(publisherId));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addPublisher(@RequestBody PublisherDTO publisherDTO) {
        return ResponseEntity.accepted().body(publisherService.addPublisher(publisherDTO));
    }

    @PutMapping
    @ApiIgnore
    public ResponseEntity<BaseResponse> addSubscription(@RequestParam("publisherId") long publisherId,
                                                        @RequestParam("subscriberId") long subscriberId
                                                        ) {
        return ResponseEntity.accepted().body(publisherService.addSubscription(publisherId, subscriberId));
    }

    @DeleteMapping
    @ApiIgnore
    public ResponseEntity<BaseResponse> removeSubscription(@RequestParam("publisherId") long publisherId,
                                                           @RequestParam("subscriberId") long subscriberId) {
        return ResponseEntity.accepted().body(publisherService.removeSubscription(publisherId, subscriberId));
    }

}

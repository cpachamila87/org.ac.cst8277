package org.ac.cst8277.ahmed.basit.controller;

import org.ac.cst8277.ahmed.basit.service.MessageService;
import org.ac.cst8277.ahmed.basit.domain.dto.MessageDTO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.accepted().body(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.accepted().body(messageService.createMessage(messageDTO));
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<BaseResponse> getMessagesByPublisher(@PathVariable("publisherId") long publisherId) {
        return ResponseEntity.accepted().body(messageService.getMessagesByPublisher(publisherId));
    }

    @GetMapping("/active/{publisherId}")
    public ResponseEntity<BaseResponse> getActiveMessagesByPublisher(@PathVariable("publisherId") long publisherId) {
        return ResponseEntity.accepted().body(messageService.getActiveMessagesByPublisher(publisherId));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<BaseResponse> publishMessage(@PathVariable("messageId") long messageId, @RequestParam("publish") boolean publish) {
        return ResponseEntity.accepted().body(messageService.publishMessage(messageId, publish));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.accepted().body(messageService.editMessage(messageDTO));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<BaseResponse> deleteMessage(@PathVariable("messageId") long messageId) {
        return ResponseEntity.accepted().body(messageService.deleteMessage(messageId));
    }

}

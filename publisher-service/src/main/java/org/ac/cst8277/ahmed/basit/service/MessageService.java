package org.ac.cst8277.ahmed.basit.service;

import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.dto.MessageDTO;

public interface MessageService {
    BaseResponse getAllMessages();
    BaseResponse getMessagesByPublisher(long id);
    BaseResponse getActiveMessagesByPublisher(long id);
//    BaseResponse getAllMessagesBySubscriber(long id);
    BaseResponse createMessage(MessageDTO messageDTO);
    BaseResponse publishMessage(long id, boolean publish);
    BaseResponse editMessage(MessageDTO messageDTO);
    BaseResponse deleteMessage(long id);
}

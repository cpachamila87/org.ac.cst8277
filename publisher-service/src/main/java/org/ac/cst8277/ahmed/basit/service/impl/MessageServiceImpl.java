package org.ac.cst8277.ahmed.basit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ac.cst8277.ahmed.basit.service.MessageService;
import org.ac.cst8277.ahmed.basit.domain.dao.MessageDAO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.Message;
import org.ac.cst8277.ahmed.basit.domain.dto.MessageDTO;
import org.ac.cst8277.ahmed.basit.repository.MessageRepository;
import org.ac.cst8277.ahmed.basit.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGE_SUCCESS_ADD_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGE_SUCCESS_DELETE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGE_SUCCESS_PUBLISHED_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGE_SUCCESS_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGE_SUCCESS_UPDATE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUCCESS_CODE;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public BaseResponse getAllMessages() {
        BaseResponse response;
        List<MessageDAO> messageDAOS;

        try {
            List<Message> messageList = messageRepository.findAll();
            messageDAOS = messageList
                    .stream()
                    .map(Mapper::convertToMessageDAO)
                    .collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, messageDAOS, MESSAGE_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getMessagesByPublisher(long id) {
        BaseResponse response;
        List<MessageDAO> messageDAOS;

        try {
            List<Message> messageList = messageRepository.findByPublisherId(id);
            messageDAOS = messageList
                    .stream()
                    .map(Mapper::convertToMessageDAO)
                    .collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, messageDAOS, MESSAGE_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getActiveMessagesByPublisher(long id) {
        BaseResponse response;
        List<MessageDAO> messageDAOS;

        try {
            List<Message> messageList = messageRepository.findByPublisherId(id);
            messageDAOS = messageList
                    .stream()
                    .filter(message -> message.isPublished())
                    .map(Mapper::convertToMessageDAO)
                    .collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, messageDAOS, MESSAGE_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

//    @Override
//    public BaseResponse getAllMessagesBySubscriber(long id) {
//        BaseResponse response;
//        List<MessageDAO> messageDAOS;
//
//        try {
//            List<Message> messageList = messageRepository.findBySubscriberId(id);
//            messageDAOS = messageList
//                    .stream()
//                    .map(Mapper::convertToUserDAO)
//                    .collect(Collectors.toList());
//            response = new BaseResponse(200, null, messageDAOS, "");
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            response = new BaseResponse(500, null, null, "");
//        }
//        return response;
//    }

    @Override
    public BaseResponse createMessage(MessageDTO messageDTO) {
        BaseResponse response;

        try {
            messageRepository.save(Mapper.convertToMessage(messageDTO));
            response = new BaseResponse(SUCCESS_CODE, null, null, MESSAGE_SUCCESS_ADD_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
        public BaseResponse publishMessage(long id, boolean publish) {
        BaseResponse response;

        try {
            Optional<Message> publisherOptional = messageRepository.findById(id);
            if (publisherOptional.isPresent()) {
                Message message = publisherOptional.get();
                message.setPublished(publish);
                messageRepository.save(message);
                response = new BaseResponse(SUCCESS_CODE, null, null, MESSAGE_SUCCESS_PUBLISHED_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse editMessage(MessageDTO messageDTO) {
        BaseResponse response;

        try {
            Optional<Message> publisherOptional = messageRepository.findById(messageDTO.getId());
            if (publisherOptional.isPresent()) {
                Message message = publisherOptional.get();
                message.setMessage(messageDTO.getMessage());
                messageRepository.save(message);
                response = new BaseResponse(SUCCESS_CODE, null, null, MESSAGE_SUCCESS_UPDATE_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse deleteMessage(long id) {
        BaseResponse response;

        try {
            messageRepository.deleteById(id);
            response = new BaseResponse(SUCCESS_CODE, null, null, MESSAGE_SUCCESS_DELETE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }
}

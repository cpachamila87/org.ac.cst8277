package org.ac.cst8277.ahmed.basit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ac.cst8277.ahmed.basit.domain.Subscriber;
import org.ac.cst8277.ahmed.basit.domain.dao.BulkMessageDAO;
import org.ac.cst8277.ahmed.basit.domain.dao.MessageDAO;
import org.ac.cst8277.ahmed.basit.domain.dao.UserResponseDAO;
import org.ac.cst8277.ahmed.basit.domain.dto.MessageResponseDTO;
import org.ac.cst8277.ahmed.basit.repository.SubscriberRepository;
import org.ac.cst8277.ahmed.basit.service.SubscriberService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.dto.UserResponseDTO;
import org.ac.cst8277.ahmed.basit.service.client.PublisherServiceClient;
import org.ac.cst8277.ahmed.basit.service.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.MESSAGES_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUBSCRIBER_ADD_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUBSCRIBER_CONFIRMED_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUBSCRIBER_REMOVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUBSCRIBER_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUCCESS_CODE;

@Slf4j
@Service
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberRepository subscriberRepository;
    private PublisherServiceClient publisherServiceClient;
    private UserServiceClient userServiceClient;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, PublisherServiceClient publisherServiceClient, UserServiceClient userServiceClient) {
        this.subscriberRepository = subscriberRepository;
        this.publisherServiceClient = publisherServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public BaseResponse createSubscription(long subId, long pubId) {
        BaseResponse response;

        try {
            subscriberRepository.save(new Subscriber(subId, pubId));
            response = new BaseResponse(SUCCESS_CODE, null, null, SUBSCRIBER_ADD_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Transactional
    @Override
    public BaseResponse confirmSubscription(long subId, long pubId) {
        BaseResponse response;

        try {
            Optional<Subscriber> subscriberOptional = subscriberRepository.findByPublisherIdAndSubscriberId(pubId, subId);
            if (subscriberOptional.isPresent()) {
                Subscriber subscriber = subscriberOptional.get();
                subscriber.setSubscribed(true);
                subscriberRepository.save(subscriber);
                BaseResponse baseResponse = publisherServiceClient.addSubscription(pubId, subId);
                if (baseResponse.getCode() == SUCCESS_CODE) {
                    response = new BaseResponse(SUCCESS_CODE, null, null, SUBSCRIBER_CONFIRMED_MESSAGE);
                } else {
                    subscriber.setSubscribed(false);
                    subscriberRepository.save(subscriber);
                    response = new BaseResponse(ERROR_CODE, null, null, baseResponse.getMessage());
                }
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    //TODO what do you mean by edit subscription
    @Override
    public BaseResponse editSubscription() {
        BaseResponse response;

        try {

            response = new BaseResponse(200, null, null, "");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(500, null, null, "");
        }
        return response;
    }

    @Override
    public BaseResponse removeSubscription(long subId, long pubId) {
        BaseResponse response;

        try {
            Optional<Subscriber> subscriberOptional = subscriberRepository.findByPublisherIdAndSubscriberId(pubId, subId);
            if (subscriberOptional.isPresent()) {
                Subscriber subscriber = subscriberOptional.get();
                subscriberRepository.delete(subscriber);
                BaseResponse baseResponse = publisherServiceClient.removeSubscription(pubId, subId);
                if (baseResponse.getCode() == SUCCESS_CODE) {
                    response = new BaseResponse(SUCCESS_CODE, null, null, SUBSCRIBER_REMOVE_MESSAGE);
                } else {
                    response = new BaseResponse(ERROR_CODE, null, null, baseResponse.getMessage());
                }
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
    public BaseResponse listSubscriptions(long subId) {
        BaseResponse response;
        List<UserResponseDAO> userResponseDAOS;

        try {
            List<Subscriber> subscriptions = subscriberRepository.findBySubscriberId(subId);
            userResponseDAOS = subscriptions
                    .stream()
                    .map(subscriber -> {
                        UserResponseDTO userResponseDTO = userServiceClient.getById(subscriber.getPublisherId());
                        UserResponseDAO userResponseDAO;
                        if (userResponseDTO.getCode() == SUCCESS_CODE) {
                            userResponseDAO = userResponseDTO.getData();
                        } else {
                            userResponseDAO = new UserResponseDAO();
                        }
                        return userResponseDAO;
                    }).collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, userResponseDAOS, SUBSCRIBER_RETRIEVE_MESSAGE);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse listMessages(long subId) {
        BaseResponse response;
        List<BulkMessageDAO> bulkMessageDAOS;

        try {
            List<Subscriber> subscriptions = subscriberRepository.findBySubscriberId(subId);
            bulkMessageDAOS = subscriptions.stream().map(subscription -> {
                MessageResponseDTO messageResponse = publisherServiceClient.getActiveMessagesByPublisher(subscription.getPublisherId());
                List<MessageDAO> messageDAO;
                BulkMessageDAO bulkMessageDAO = new BulkMessageDAO();
                if (messageResponse.getCode() == SUCCESS_CODE) {
                    messageDAO = (List<MessageDAO>) messageResponse.getDataList();
                    bulkMessageDAO.setPublisherId(subscription.getPublisherId());
                    bulkMessageDAO.setMessages(messageDAO);
                } else {
                    messageDAO = new ArrayList<>();
                    bulkMessageDAO.setMessages(messageDAO);
                }
                return bulkMessageDAO;
            }).collect(Collectors.toList());

            response = new BaseResponse(SUCCESS_CODE, null, bulkMessageDAOS, MESSAGES_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }
}

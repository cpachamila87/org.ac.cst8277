package org.ac.cst8277.ahmed.basit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ac.cst8277.ahmed.basit.domain.PubAndSub;
import org.ac.cst8277.ahmed.basit.domain.Publisher;
import org.ac.cst8277.ahmed.basit.domain.dao.PublisherDAO;
import org.ac.cst8277.ahmed.basit.domain.dao.UserResponseDAO;
import org.ac.cst8277.ahmed.basit.service.PublisherService;
import org.ac.cst8277.ahmed.basit.domain.dto.UserResponseDTO;
import org.ac.cst8277.ahmed.basit.repository.PubAndSubRepository;
import org.ac.cst8277.ahmed.basit.repository.PublisherRepository;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.dto.PublisherDTO;
import org.ac.cst8277.ahmed.basit.service.client.UserServiceClient;
import org.ac.cst8277.ahmed.basit.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.PUBLISHER_ADD_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.PUBLISHER_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUBSCRIBER_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUCCESS_CODE;

@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;
    private PubAndSubRepository pubAndSubRepository;
    private UserServiceClient userServiceClient;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, PubAndSubRepository pubAndSubRepository, UserServiceClient userServiceClient) {
        this.publisherRepository = publisherRepository;
        this.pubAndSubRepository = pubAndSubRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public BaseResponse getPublishers() {
        BaseResponse response;
        List<PublisherDAO> publisherDAOS;

        try {
            List<Publisher> publishers = publisherRepository.findAll();
            publisherDAOS = publishers.stream().map(Mapper::convertToPublisherDAO).collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, publisherDAOS, PUBLISHER_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse addPublisher(PublisherDTO publisherDTO) {
        BaseResponse response;
        PublisherDAO publisherDAO;
        Publisher publisher;

        try {
            publisher = publisherRepository.save(new Publisher(publisherDTO.getPublisherId(), publisherDTO.getTopic(), true));
            publisherDAO = Mapper.convertToPublisherDAO(publisher);
            response = new BaseResponse(SUCCESS_CODE, publisherDAO, null, PUBLISHER_ADD_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse addSubscription(long pubId, long subId) {
        BaseResponse response;

        try {
            pubAndSubRepository.save(new PubAndSub(pubId, subId));
            response = new BaseResponse(SUCCESS_CODE, null, null, "");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse removeSubscription(long pubId, long subId) {
        BaseResponse response;

        try {
            Optional<PubAndSub> pubAndSubOptional = pubAndSubRepository.findByPublisherIdAndSubscriberId(pubId, subId);
            if (pubAndSubOptional.isPresent()) {
                PubAndSub ps = pubAndSubOptional.get();
                pubAndSubRepository.deleteById(ps.getId());
                response = new BaseResponse(SUCCESS_CODE, null, null, "");
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, "");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getSubscribersByPubId(long pubId) {
        BaseResponse response;
        List<UserResponseDAO> userResponseDAOS;

        try {
            List<PubAndSub> subscribersList = pubAndSubRepository.findByPublisherId(pubId);
            userResponseDAOS = subscribersList
                    .stream()
                    .map(subscriber -> {
                        UserResponseDTO userResponseDTO = userServiceClient.getById(subscriber.getSubscriberId());
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

}

package org.ac.cst8277.ahmed.basit.service;

import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.domain.dto.PublisherDTO;

public interface PublisherService {
    BaseResponse getPublishers();
    BaseResponse addPublisher(PublisherDTO publisherDTO);
    BaseResponse addSubscription(long pubId, long subId);
    BaseResponse removeSubscription(long pubId, long subId);
    BaseResponse getSubscribersByPubId(long pubId);
}


package org.ac.cst8277.ahmed.basit.service.client;

import org.ac.cst8277.ahmed.basit.domain.dto.MessageResponseDTO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.stereotype.Component;

@Component
public class PublisherServicFallBack implements PublisherServiceClient{

    @Override
    public BaseResponse addSubscription(long publisherId, long subscriberId) {
        return null;
    }

    @Override
    public BaseResponse removeSubscription(long publisherId, long subscriberId) {
        return null;
    }

    @Override
    public MessageResponseDTO getActiveMessagesByPublisher(long publisherId) {
        return null;
    }
}

package org.ac.cst8277.ahmed.basit.service.client;

import org.ac.cst8277.ahmed.basit.domain.dto.MessageResponseDTO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.ac.cst8277.ahmed.basit.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "publisher-service", path = "/api",
        fallback = PublisherServicFallBack.class,
        configuration = DefaultFeignConfig.class)
public interface PublisherServiceClient {

    @RequestMapping(path = "/publisher", method = RequestMethod.PUT)
    BaseResponse addSubscription(@RequestParam("publisherId") long publisherId,
                                 @RequestParam("subscriberId") long subscriberId);

    @RequestMapping(path = "/publisher", method = RequestMethod.DELETE)
    BaseResponse removeSubscription(@RequestParam("publisherId") long publisherId,
                                 @RequestParam("subscriberId") long subscriberId);

    @RequestMapping(path = "/message/active/{publisherId}", method = RequestMethod.GET)
    MessageResponseDTO getActiveMessagesByPublisher(@PathVariable("publisherId") long publisherId);

}

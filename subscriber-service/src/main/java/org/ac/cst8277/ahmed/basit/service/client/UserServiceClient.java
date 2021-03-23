package org.ac.cst8277.ahmed.basit.service.client;

import org.ac.cst8277.ahmed.basit.domain.dto.UserResponseDTO;
import org.ac.cst8277.ahmed.basit.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-management-service", path = "/api/user",
        fallback = UserServiceFallBack.class,
        configuration = DefaultFeignConfig.class)
public interface UserServiceClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    UserResponseDTO getById(@PathVariable("userId") long userId);

}

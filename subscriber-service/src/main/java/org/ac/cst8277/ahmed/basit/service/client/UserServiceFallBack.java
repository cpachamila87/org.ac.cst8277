package org.ac.cst8277.ahmed.basit.service.client;

import org.ac.cst8277.ahmed.basit.domain.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBack implements UserServiceClient {

    @Override
    public UserResponseDTO getById(long userId) {
        return null;
    }
}

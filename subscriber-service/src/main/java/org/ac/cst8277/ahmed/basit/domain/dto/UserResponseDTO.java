package org.ac.cst8277.ahmed.basit.domain.dto;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.domain.dao.UserResponseDAO;
import org.ac.cst8277.ahmed.basit.util.MessageTransferResponse;

import java.util.Collection;

@Data
public class UserResponseDTO extends MessageTransferResponse {
    private UserResponseDAO data;
    private Collection<? extends UserResponseDAO> dataList;
}

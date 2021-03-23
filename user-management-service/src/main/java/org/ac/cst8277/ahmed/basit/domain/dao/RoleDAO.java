package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDAO extends BaseMessage {
    private long id;
    private String role;
}

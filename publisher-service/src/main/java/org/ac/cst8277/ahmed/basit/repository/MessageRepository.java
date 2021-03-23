package org.ac.cst8277.ahmed.basit.repository;

import org.ac.cst8277.ahmed.basit.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByPublisherId(long publisherId);
}

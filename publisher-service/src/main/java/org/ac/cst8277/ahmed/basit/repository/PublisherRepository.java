package org.ac.cst8277.ahmed.basit.repository;

import org.ac.cst8277.ahmed.basit.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

package org.sid.electromenager.dao;

import org.sid.electromenager.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface ClientRepository extends JpaRepository<Client, Long>{
	Page<Client> findByNomContains(String mc, PageRequest pageable);
}

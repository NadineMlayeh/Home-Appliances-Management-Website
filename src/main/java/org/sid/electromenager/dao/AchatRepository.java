package org.sid.electromenager.dao;

import org.sid.electromenager.entities.Achat;
import org.sid.electromenager.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface AchatRepository extends JpaRepository<Achat, Long>{
	Page<Achat> findByClient(Client client, Pageable pageable);
	Page<Achat> findByModePayment(String modePayment, Pageable pageable);
}

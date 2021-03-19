package com.kosko.reception.hotel.repositories;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosko.reception.hotel.model.Guest;

/**
 * @author kaust
 *
 */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{
	
	/**
	 * @param guestName
	 * @return
	 * @throws EntityNotFoundException
	 */
	Guest findByNameIgnoreCase(String guestName) throws EntityNotFoundException;
	
	/**
	 * @param guestName
	 * @throws EntityNotFoundException
	 */
	void deleteByNameIgnoreCase(String guestName) throws EntityNotFoundException;
	
}

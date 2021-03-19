package com.kosko.reception.hotel.repositories;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosko.reception.hotel.model.Parcel;

/**
 * @author kaust
 *
 */
@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
	
	/**
	 * @param code
	 * @return
	 * @throws EntityNotFoundException
	 */
	Parcel findByCodeIgnoreCase(String code) throws EntityNotFoundException;

}

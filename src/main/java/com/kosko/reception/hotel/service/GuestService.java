package com.kosko.reception.hotel.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.exception.UnpickedPacelsException;
import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.model.Parcel;

public interface GuestService {

	/**
	 * @return
	 */
	List<Guest> findAll();
	
	/**
	 * @param guestName
	 * @return
	 * @throws EntityNotFoundException
	 */
	Guest findByName(String guestName) throws EntityNotFoundException;
	
	/**
	 * @param guest
	 * @return
	 * @throws ConstraintViolationException
	 */
	Guest create(Guest guest) throws ConstraintViolationException;
	
	/**
	 * @param guestName
	 * @throws EntityNotFoundException
	 */
	void deleteByName(String guestName) throws EntityNotFoundException;
	
	/**
	 * @param guest
	 * @throws ConstraintViolationException
	 * @throws EntityNotFoundException
	 */
	void update(Guest guest) throws ConstraintViolationException, EntityNotFoundException;
	
	/**
	 * @param guestName
	 * @return
	 * @throws EntityNotFoundException
	 */
	boolean findGuestStatus(String guestName) throws EntityNotFoundException;
	
	/**
	 * @param guestName
	 * @return
	 * @throws EntityNotFoundException
	 */
	List<Parcel> getParcelsByName(String guestName) throws EntityNotFoundException;
	
	/**
	 * @param guestName
	 * @throws EntityNotFoundException
	 * @throws GuestAlreadyCheckedOutException
	 * @throws UnpickedPacelsException
	 */
	void checkoutGuest(String guestName) 
			throws EntityNotFoundException, GuestAlreadyCheckedOutException, UnpickedPacelsException;
	
}

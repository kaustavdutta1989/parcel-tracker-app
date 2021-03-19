package com.kosko.reception.hotel.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.model.Parcel;

/**
 * @author kaust
 *
 */
public interface ParcelService {
	
	/**
	 * @return
	 */
	List<Parcel> findAll();
	
	/**
	 * @param parcelCode
	 * @return
	 * @throws EntityNotFoundException
	 */
	Parcel findByCode(String parcelCode) throws EntityNotFoundException;
	
	/**
	 * @param parcel
	 * @param guestName
	 * @return
	 * @throws ConstraintViolationException
	 * @throws GuestAlreadyCheckedOutException
	 * @throws EntityNotFoundException
	 */
	Parcel create(Parcel parcel, String guestName) throws ConstraintViolationException, GuestAlreadyCheckedOutException, EntityNotFoundException;
	
	/**
	 * @param parcelCode
	 * @throws EntityNotFoundException
	 */
	void deleteByCode(String parcelCode) throws EntityNotFoundException;
	
	/**
	 * @param parcelCode
	 * @param guestName
	 * @throws EntityNotFoundException
	 */
	void parcelPickedByGuest(String parcelCode) throws EntityNotFoundException;
	
	/**
	 * @param parcelCode
	 * @throws EntityNotFoundException
	 */
	void updateAllPickedupParcel(String parcelCode) throws EntityNotFoundException;
	
	/**
	 * @param parcel
	 * @throws ConstraintViolationException
	 * @throws EntityNotFoundException
	 */
	void updateParcel(Parcel parcel) throws ConstraintViolationException, EntityNotFoundException;

}

package com.kosko.reception.hotel.serviceImpl;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.model.Parcel;
import com.kosko.reception.hotel.repositories.GuestRepository;
import com.kosko.reception.hotel.repositories.ParcelRepository;
import com.kosko.reception.hotel.service.ParcelService;

/**
 * @author kaust
 *
 */
@Service
public class ParcelServiceImpl implements ParcelService{

	private static final Logger LOG = 
    		LoggerFactory.getLogger(ParcelServiceImpl.class);
	
	private ParcelRepository parcelRepository;
	private GuestRepository guestRepository;
	
	@Autowired
	public ParcelServiceImpl(ParcelRepository parcelRepository,
			GuestRepository guestRepository) {
		this.parcelRepository = parcelRepository;
		this.guestRepository = guestRepository;
	}

	/**
	 *
	 */
	@Override
	public List<Parcel> findAll() {
		
		return parcelRepository.findAll();
	}
	
	/**
	 *
	 */
	@Override
	public Parcel findByCode(String parcelCode) throws EntityNotFoundException {
		
		Parcel parcel = parcelRepository.findByCodeIgnoreCase(parcelCode);
		if(parcel == null) {
			LOG.error("Parcel Not Found with code: ", parcelCode);
			throw new EntityNotFoundException("Parcel Not Found ...");
		}
		
		return parcel;		
	}

	/**
	 *
	 */
	@Override
	public Parcel create(Parcel parcel, String guestName) 
			throws GuestAlreadyCheckedOutException, EntityNotFoundException {
		Guest guest;
		Parcel newParcel = new Parcel(parcel.getCode());
		try {
			guest = guestRepository.findByNameIgnoreCase(guestName);
			if(!guest.isIscheckedin())
				throw new GuestAlreadyCheckedOutException("Guest/s has/have Already Checked Out");
			
			newParcel = parcelRepository.save(newParcel);
			guest.getParcels().add(newParcel);
			guestRepository.saveAndFlush(guest);
			
		} catch (NullPointerException e) {
			LOG.error("Constraints Exception");
			throw new ConstraintViolationException("Constraints Exception...", new HashSet<ConstraintViolation<?>>());
		}
		
		return newParcel;
	}

	/**
	 *
	 */
	@Override
	public void updateParcel(Parcel parcel) throws ConstraintViolationException, EntityNotFoundException {
		
		parcelRepository.saveAndFlush(parcel);
	}

	/**
	 *
	 */
	@Override
	public void deleteByCode(String parcelCode) throws EntityNotFoundException {
		Parcel parcel = findByCode(parcelCode);
		parcelRepository.deleteById(parcel.getId());
	}

	/**
	 *
	 */
	@Override
	public void parcelPickedByGuest(String parcelCode) throws EntityNotFoundException {
		
		Parcel parcel ;
		try {
			parcel = parcelRepository.findByCodeIgnoreCase(parcelCode);	
			parcel.setIspicked(true);
			parcel.setPickupdate(ZonedDateTime.now());
			parcelRepository.saveAndFlush(parcel);		
		} catch (Exception e) {
			LOG.error("Parcel Not Found with Code");
			throw new EntityNotFoundException("Parcel Not Found with Code...");
		}
	}

	/**
	 *
	 */
	@Override
	public void updateAllPickedupParcel(String guestName) throws EntityNotFoundException {
		Guest guest = guestRepository.findByNameIgnoreCase(guestName);
		if(guest == null) {
			LOG.error("Guest Not Found with name: ", guestName);
			throw new EntityNotFoundException("Entity Not Found ...");
		}
		Set<Parcel> parcels = guest.getParcels();
		for(Parcel p: parcels) {
			p.setIspicked(true);
			p.setPickupdate(ZonedDateTime.now());
			parcelPickedByGuest(p.getCode());
		}		
	}
}

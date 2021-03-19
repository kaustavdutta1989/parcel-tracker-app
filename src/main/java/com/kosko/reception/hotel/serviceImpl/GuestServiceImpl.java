package com.kosko.reception.hotel.serviceImpl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.exception.UnpickedPacelsException;
import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.model.Parcel;
import com.kosko.reception.hotel.repositories.GuestRepository;
import com.kosko.reception.hotel.service.GuestService;

/**
 * @author kaust
 *
 */
@Service
public class GuestServiceImpl implements GuestService {
	
	Logger LOG = 
    		LoggerFactory.getLogger(GuestServiceImpl.class);
	
	GuestRepository guestRepository;
	
	@Autowired
	public GuestServiceImpl(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	@Override
	public List<Guest> findAll() {

		return guestRepository.findAll();
	}

	/**
	 *
	 */
	@Override
	public Guest create(Guest guest) throws ConstraintViolationException {
		Guest g = new Guest(guest.getName(), guest.getDetails());
		try {
			g = guestRepository.save(g);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a guest: {}", g, e);
            throw new DataIntegrityViolationException("ConstraintsViolationException while creating a guest");
		}		
		
		return g;
	}

	/**
	 *
	 */
	@Override
	public void update(Guest guest) throws ConstraintViolationException, EntityNotFoundException {
		
		try {
			guest = guestRepository.saveAndFlush(guest);
		} catch (ConstraintViolationException e) {
			LOG.warn("ConstraintsViolationException while updating the guest: {}", guest, e);
            throw new DataIntegrityViolationException("ConstraintsViolationException while updating a guest");
		}		
	}

	/**
	 *
	 */
	@Override
	public void deleteByName(String guestName) throws EntityNotFoundException {
		
		Guest guest = findByName(guestName);
		guestRepository.delete(guest);
	}

	/**
	 *
	 */
	@Override
	public Guest findByName(String guestName) throws EntityNotFoundException {
		Guest guest = guestRepository.findByNameIgnoreCase(guestName);
		if(guest == null) {
			LOG.error("Guest Not Found with name: ", guestName);
			throw new EntityNotFoundException("Entity Not Found ...");
		}
		
		return guest;
	}

	/**
	 *
	 */
	@Override
	public boolean findGuestStatus(String guestName) {
		return findByName(guestName).isIscheckedin();
	}

	/**
	 *
	 */
	@Override
	public List<Parcel> getParcelsByName(String guestName) throws EntityNotFoundException {
		List <Parcel> parcels = new ArrayList<Parcel>();
		Guest guest = findByName(guestName);
		guest.getParcels().forEach(parcels::add);
		return parcels;
	}

	/**
	 *
	 */
	@Override
	public void checkoutGuest(String guestName) throws EntityNotFoundException, GuestAlreadyCheckedOutException, UnpickedPacelsException {
		
		Guest guest = findByName(guestName);
		
		if(!guest.isIscheckedin()) {
			LOG.error("Guest CHECKEDOUT with name: ", guestName);
			throw new EntityNotFoundException("Guest CHECKEDOUT ...");
		}
		
		List<Parcel> parcels = getParcelsByName(guestName);
		boolean parcelPick = false;
		for(Parcel p: parcels) {
			parcelPick = (!p.isIspicked()) ? true : false;
		}
		if(parcelPick) {
			LOG.error("UnpickedPacelsException with name: ", guestName);
			throw new UnpickedPacelsException("Unpicked Pacels Exception ...");
		}
		
		guest.setCheckoutdate(ZonedDateTime.now());
		guest.setIscheckedin(false);
		guestRepository.saveAndFlush(guest);
		
	}
}

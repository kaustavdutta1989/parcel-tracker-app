package com.kosko.reception.hotel.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.model.Parcel;
import com.kosko.reception.hotel.service.ParcelService;
import com.kosko.reception.hotel.serviceImpl.ParcelServiceImpl;

/**
 * @author kaust
 *
 */
@RestController
@RequestMapping("reception/parcels")
public class ParcelController {
	
	private final ParcelService parcelService;
	
	@Autowired
	public ParcelController(ParcelServiceImpl parcelService) {
		this.parcelService = parcelService;
	}
	
	
	/**
	 * @return
	 * 
	 * Get All Parcels without Filters
	 */
	@GetMapping
	public List<Parcel> findParcels() {
		
		return parcelService.findAll();
	}

	
    /**
     * @param parcelCode
     * @return
     * @throws EntityNotFoundException
     * 
     * Get List of Parcels of Guest
     */
    @GetMapping("/{parcelCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public Parcel getParcel(@PathVariable String parcelCode) 
    		throws EntityNotFoundException {
        
    	return parcelService.findByCode(parcelCode);
    }
    
    /**
     * @param parcel
     * @param guestName
     * @return
     * @throws ConstraintViolationException
     * @throws EntityNotFoundException
     * @throws GuestAlreadyCheckedOutException
     * 
     * Receive New Parcel Only and Only If Guest is Checked-In
     */
    @PostMapping("{parcelCode}/{guestName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Parcel createParcel(@Valid @RequestBody Parcel parcel, @PathVariable String guestName) 
    		throws ConstraintViolationException, EntityNotFoundException, GuestAlreadyCheckedOutException {
        return parcelService.create(parcel, guestName);
    }

    /**
     * @param parcelCode
     * @throws EntityNotFoundException
     * 
     * Parcel Picked by Guest
     */
    @PutMapping("/{parcelCode}")
    @ResponseStatus(HttpStatus.OK)
    public void parcelPickedByGuest(@PathVariable String parcelCode) throws EntityNotFoundException {
    	parcelService.parcelPickedByGuest(parcelCode);
    }
    
    /**
     * @param parcel
     * @throws EntityNotFoundException
     * @throws ConstraintViolationException
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateParcel(@Valid @RequestBody Parcel parcel) throws EntityNotFoundException, ConstraintViolationException {
    	parcelService.updateParcel(parcel);
    }
    
    /**
     * @param parcelCode
     * @throws EntityNotFoundException
     * 
     * Delete PArcel By parcelCode
     */
    @DeleteMapping("/{parcelCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParcel_HARD(@PathVariable String parcelCode) throws EntityNotFoundException {
    	
    	parcelService.deleteByCode(parcelCode);
    }

}
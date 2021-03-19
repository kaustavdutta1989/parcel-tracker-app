package com.kosko.reception.hotel.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kosko.reception.hotel.exception.GuestAlreadyCheckedOutException;
import com.kosko.reception.hotel.exception.UnpickedPacelsException;
import com.kosko.reception.hotel.model.Parcel;
import com.kosko.reception.hotel.service.GuestService;
import com.kosko.reception.hotel.service.ParcelService;
import com.kosko.reception.hotel.serviceImpl.GuestServiceImpl;
import com.kosko.reception.hotel.serviceImpl.ParcelServiceImpl;

/**
 * @author kaust
 *
 */
@RestController
@RequestMapping("reception/checkout")
public class CheckoutController {
	
	private final ParcelService parcelService;
	
	private final GuestService guestService;
	
	/**
	 * @param parcelService
	 * @param guestService
	 */
	@Autowired
	public CheckoutController(ParcelServiceImpl parcelService,
			GuestServiceImpl guestService) {
		this.parcelService = parcelService;
		this.guestService = guestService;
	}
    
    /**
     * @param guestName
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/parcels/{guestName}")
    public List<Parcel> getAllParcelsByName(String guestName)  throws EntityNotFoundException {
    	return guestService.getParcelsByName(guestName);
    }

	/**
	 * @param guestName
	 * @return
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/status/{guestName}")
    @ResponseStatus(HttpStatus.FOUND)
    public String findGuestByName(@PathVariable String guestName) throws EntityNotFoundException {
		
		return (guestService.findGuestStatus(guestName) ? "CHECKED-IN" : "CHECKED-OUT");
    }
	
	/**
	 * @param guestName
	 * @throws ConstraintViolationException
	 * @throws EntityNotFoundException
	 */
	@PutMapping("parcels/{guestName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAllParcelsByName(@PathVariable String guestName) throws ConstraintViolationException, EntityNotFoundException {
    	parcelService.updateAllPickedupParcel(guestName);
    }

    /**
     * @param guestName
     * @throws EntityNotFoundException
     * @throws GuestAlreadyCheckedOutException
     * @throws UnpickedPacelsException
     */
    @PutMapping("/status/{guestName}")
    @ResponseStatus(HttpStatus.OK)
    public void checkoutGuest_HARD(@PathVariable String guestName) 
    		throws EntityNotFoundException, GuestAlreadyCheckedOutException, UnpickedPacelsException {
    	guestService.checkoutGuest(guestName);
    }

}
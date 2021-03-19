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

import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.service.GuestService;
import com.kosko.reception.hotel.serviceImpl.GuestServiceImpl;

/**
 * @author kaust
 *
 */
@RestController
@RequestMapping("reception/guests")
public class GuestController {
	
	private final GuestService guestService;
	
	/**
	 * @param guestService
	 */
	@Autowired
	public GuestController(GuestServiceImpl guestService) {
		this.guestService = guestService;
	}
	
	/**
	 * @return
	 */
	@GetMapping
	public List<Guest> findAllGuests() {
		return guestService.findAll();
	}
    
    /**
     * @param guestName
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{guestName}")
    @ResponseStatus(HttpStatus.FOUND)
    public Guest getGuestByName(@PathVariable String guestName) throws EntityNotFoundException {
        return guestService.findByName(guestName);
    }
    
    /**
     * @param guest
     * @return
     * @throws ConstraintViolationException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@Valid @RequestBody Guest guest) throws ConstraintViolationException {
        return guestService.create(guest);
    }

    /**
     * @param guest
     * @throws ConstraintViolationException
     * @throws EntityNotFoundException
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGuest_HARD(@Valid @RequestBody Guest guest) throws ConstraintViolationException, EntityNotFoundException {
    	guestService.update(guest);
    }

    /**
     * @param guestName
     */
    @DeleteMapping("/{guestName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGuest_HARD(@PathVariable String guestName) {
    	guestService.deleteByName(guestName);
    }

}

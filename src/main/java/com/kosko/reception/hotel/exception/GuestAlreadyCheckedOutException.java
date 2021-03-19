package com.kosko.reception.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kaust
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Guest/s Has/Have Already Checked Out ...")
public class GuestAlreadyCheckedOutException extends Exception {

	private static final long serialVersionUID = 1L;


    /**
     * @param message
     */
    public GuestAlreadyCheckedOutException(String message)
    {
        super(message);
    }

}

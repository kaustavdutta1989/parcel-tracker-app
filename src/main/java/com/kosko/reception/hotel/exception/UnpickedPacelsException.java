package com.kosko.reception.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kaust
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There are UNPICKED PARCEL/s for the Guest ...")
public class UnpickedPacelsException extends Exception {

	private static final long serialVersionUID = 1L;


    /**
     * @param message
     */
    public UnpickedPacelsException(String message)
    {
        super(message);
    }

}

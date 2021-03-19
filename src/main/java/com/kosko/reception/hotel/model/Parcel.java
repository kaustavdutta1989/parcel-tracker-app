package com.kosko.reception.hotel.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author kaust
 *
 */
@Entity
@Table(
		name = "parcel", 
		uniqueConstraints = @UniqueConstraint(
				name = "uc_code", 
				columnNames = {"code"}))
public class Parcel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	/**
	 * 
	 */
	@Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime receivedate = ZonedDateTime.now();

    /**
     * 
     */
    @Column(nullable = false)
    @NotNull(message = "Parcel Code Required!")
    private String code;
	
	/**
	 * 
	 */
	@Column(nullable = false)
    private boolean ispicked = false;
	
	/**
	 * 
	 */
	@Column(nullable = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime pickupdate;
	
	/**
	 * @see receivedate = ZonedDateTime.now()
	 * @see ispicked = false
	 */
	public Parcel() {
		
	}

	/**
	 * @param code
	 * @param owner
	 */
	public Parcel(@NotNull(message = "Parcel Code Required!") String code) {

		this.code = code;
		this.receivedate = ZonedDateTime.now();
		this.ispicked = false;
		this.pickupdate = null;
	}

	public ZonedDateTime getReceivedate() {
		return receivedate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public ZonedDateTime getPickupdate() {
		return pickupdate;
	}

	public boolean isIspicked() {
		return ispicked;
	}

	public void setReceivedate(ZonedDateTime receivedate) {
		this.receivedate = receivedate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPickupdate(ZonedDateTime pickupdate) {
		this.pickupdate = pickupdate;
	}

	public void setIspicked(boolean ispicked) {
		this.ispicked = ispicked;
	}

}

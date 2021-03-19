package com.kosko.reception.hotel.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
		name = "guest", 
		uniqueConstraints = @UniqueConstraint(
				name = "uc_name", 
				columnNames = {"name"}))
public class Guest implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	/**
	 * 
	 */
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime checkindate = ZonedDateTime.now();
	
	/**
	 * 
	 */
	@Column
	private boolean ischeckedin = false;

    /**
     * 
     */
    @Column(nullable = false)
    @NotNull(message = "Guest Name Required!")
    private String name;

	/**
	 * 
	 */
	@Column(nullable = false)
    @NotNull(message = "Guest Details are a Must!")
    private String details;

	/**
	 * 
	 */
	@Column(nullable = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime checkoutdate;
	
	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "owner", referencedColumnName = "name")
	private Set<Parcel> parcels;

	/**
	 * @see ischeckedin = true
	 * @see checkindate = ZonedDateTime.now()
	 */
	public Guest() {
	}
	
	/**
	 * @param name
	 * @param details
	 */
	public Guest(String name, String details) {
		super();
		this.name = name;
		this.details = details;
		this.ischeckedin = true;
		this.checkindate = ZonedDateTime.now();
		this.checkoutdate = null;
		this.parcels = new HashSet<Parcel>();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}

	public ZonedDateTime getCheckoutdate() {
		return checkoutdate;
	}

	public boolean isIscheckedin() {
		return ischeckedin;
	}

	public Set<Parcel> getParcels() {
		return parcels;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setCheckoutdate(ZonedDateTime checkoutdate) {
		this.checkoutdate = checkoutdate;
	}

	public void setIscheckedin(boolean ischeckedin) {
		this.ischeckedin = ischeckedin;
	}

	public void setParcels(Set<Parcel> parcels) {
		this.parcels = parcels;
	}
    
    public ZonedDateTime getCheckindate() {
		return checkindate;
	}

	public void setCheckindate(ZonedDateTime checkindate) {
		this.checkindate = checkindate;
	}

}

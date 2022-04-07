package com.wings.wingsuserservice.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity

public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 25)
	private String depar_gov;
	@Size(max = 30)
	private String adr_departure;
	@Size(max = 25)
	private String arri_gov;
	@Size(max = 30)
	private String adr_arrival;
	private double longitude;
	private double latitude;
	@Size(max = 100)
	private String description;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public Route() {
	}

	public Route(String depar_gov, String adr_departure, String arri_gov, String adr_arrival, double longitude, double latitude, String description) {
		this.depar_gov = depar_gov;
		this.adr_departure = adr_departure;
		this.arri_gov = arri_gov;
		this.adr_arrival = adr_arrival;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
	}

	public Route(String depar_gov, String adr_departure, String arri_gov, String adr_arrival, double longitude, double latitude, String description, User user) {
		this.depar_gov = depar_gov;
		this.adr_departure = adr_departure;
		this.arri_gov = arri_gov;
		this.adr_arrival = adr_arrival;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepar_gov() {
		return depar_gov;
	}

	public void setDepar_gov(String depar_gov) {
		this.depar_gov = depar_gov;
	}

	public String getAdr_departure() {
		return adr_departure;
	}

	public void setAdr_departure(String adr_departure) {
		this.adr_departure = adr_departure;
	}

	public String getArri_gov() {
		return arri_gov;
	}

	public void setArri_gov(String arri_gov) {
		this.arri_gov = arri_gov;
	}

	public String getAdr_arrival() {
		return adr_arrival;
	}

	public void setAdr_arrival(String adr_arrival) {
		this.adr_arrival = adr_arrival;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public User getUser() {
		return user;
	}*/

	public void setUser(User user) {
		this.user = user;
	}
}

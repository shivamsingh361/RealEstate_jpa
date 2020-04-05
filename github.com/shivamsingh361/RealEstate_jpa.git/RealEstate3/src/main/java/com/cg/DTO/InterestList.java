package com.cg.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.DateTimeAtCompleted;

/* DB Schema:
 * 
 * CREATE TABLE intrest_log(
 * user_id varchar(40) not null,
 * timestamp datetime not null,
 * intrested_city varchar(40),
 * prop_id int(10) not null);
*/

@Entity
@Table(name="interest_log")
public class InterestList implements Serializable{
	@Column(name="user_id")
	private String userId;
	/*
	 * java.util.Date dt = new java.util.Date(); java.text.SimpleDateFormat sdf =
	 * new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String currentTime =
	 * sdf.format(dt);
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "timestamp", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	@Column(name="intrested_city")
	private String city;
	@Column(name="prop_id")
	private int propId;
	public InterestList(String userId, String city, int propId) {
		super();
		this.userId = userId;
		this.timestamp = Date.from( LocalDateTime.now().atZone( ZoneId.systemDefault()).toInstant());
		this.city = city;
		this.propId = propId;
	}
	public InterestList() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}

}

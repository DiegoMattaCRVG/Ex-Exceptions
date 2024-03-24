package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if(!checkOut.after(checkIn)) {
			throw new DomainException("CHECK-OUT DATE MUST BE AFTER CHECK-IN DATE");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public long duration() {
		long diff = getCheckOut().getTime() - getCheckIn().getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("UPDATE DATES MUST BE FUTURE DATES");
			}
		if(!checkOut.after(checkIn)) {
			throw new DomainException("CHECK-OUT DATE MUST BE AFTER CHECK-IN DATE");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Reservation: Room " 
				+ getRoomNumber() 
				+ ", check-in: " + sdf.format(getCheckIn()) 
				+ ", check-out: " + sdf.format(getCheckOut()) 
				+ ", " 
				+ duration() 
				+ " nigths";
	}
	
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	
	
}


package com.castleglobal.uber.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Coordinates  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5222186148846148148L;
	@JsonProperty
	private Long x;
	@JsonProperty
	private Long y;
	
	
	public Coordinates() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coordinates(Long x, Long y) {
		super();
		this.x = x;
		this.y = y;
	}
	public long getX() {
		return x;
	}
	 void setX(long x) {
		this.x = x;
	}
	public long getY() {
		return y;
	}
	 void setY(long y) {
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}
	
	
}

package org.cap.drinkanddelightmgt.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DistributorEntity")
public class DistributorEntity {
	
	@Id
	private String distributorId;
	private String distributorName;
	private String distributorAddress;
	private String distributorPhoneNumber;
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getDistributorAddress() {
		return distributorAddress;
	}
	public void setDistributorAddress(String distributorAddress) {
		this.distributorAddress = distributorAddress;
	}
	public String getDistributorPhoneNumber() {
		return distributorPhoneNumber;
	}
	public void setDistributorPhoneNumber(String distributorPhoneNumber) {
		this.distributorPhoneNumber = distributorPhoneNumber;
	}
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DistributorEntity entity = (DistributorEntity)object;
        return distributorId ==entity.distributorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distributorId);
    }
	
}


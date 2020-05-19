package org.cap.drinkanddelightmgt.entities;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SupplierEntity")
public class SupplierEntity {
	@Id
	private String supplierId;
	
	private String supplierName;
	private String supplierAddress;
	private String supplierPhoneNumber;
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}
	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SupplierEntity entity = (SupplierEntity)object;
        return supplierId ==entity.supplierId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId);
    }

}

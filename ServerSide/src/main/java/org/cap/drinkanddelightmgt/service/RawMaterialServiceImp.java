package org.cap.drinkanddelightmgt.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;
import org.cap.drinkanddelightmgt.dao.IRawMaterialStockDao;
import org.cap.drinkanddelightmgt.entities.RawMaterialStockEntity;
import org.cap.drinkanddelightmgt.exception.DateTimeFormatException;
import org.cap.drinkanddelightmgt.exception.EmptyDataException;
import org.cap.drinkanddelightmgt.exception.NegativeValueException;
import org.cap.drinkanddelightmgt.exception.ProcessDateException;
import org.cap.drinkanddelightmgt.exception.RawMaterialStockNotFoundException;
import org.cap.drinkanddelightmgt.exception.UpdationFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RawMaterialServiceImp implements IRawMaterialStockService {

	@Autowired
	private IRawMaterialStockDao stockdao;
	
	/*******************************************************************************************************
	 - Function Name	:	doesRawMaterialOrderExistInStock
	 - Input Parameters	:	String
	 - Return Type		:	boolean
	 - Throws			:  	No exception
	 - Description		:	Checks if Raw Material Order ID exists in Stock
	 ********************************************************************************************************/
	@Override
	public Boolean doesRawMaterialOrderIdExistInStock(String id) {
		Optional<RawMaterialStockEntity>optional=stockdao.findByOrderId(id);
	     if(optional.isPresent())
	         return true;
	   return false;
	}
	

	
	/*******************************************************************************************************
	 - Function Name	:	trackRawMaterialOrder
	 - Input Parameters	:	String id
	 - Return Type		:	RawMaterialStock
	 - Throws			:  	-
	 - Description		:	Track a particular order by id 
	 ********************************************************************************************************/
	@Override
	public RawMaterialStockEntity trackRawMaterialOrder(String id) {
			Optional<RawMaterialStockEntity>optional=stockdao.findByOrderId(id);
		     if(optional.isPresent()){
		    	 RawMaterialStockEntity rmstock=optional.get();
		         return rmstock;
		     } 
		   throw new RawMaterialStockNotFoundException("raw Material stock not found for id="+id);
	}
	
	
	
	
	
	/*******************************************************************************************************
	 - Function Name	:	processDateCheck
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	boolean
	 - Throws			:  	-
	 - Description		:	Checks if the process date entered is valid or not
	 ********************************************************************************************************/
	@Override
	public Boolean processDateCheck(RawMaterialStockEntity rawMaterialStockEntity,Date processDate) {
	        	 if(processDate.after(rawMaterialStockEntity.getDeliveryDate())) 
					return true;
				else 
		           return false;
	     
	}
	
	/*******************************************************************************************************
	 - Function Name	:	updateProcessDateInStock
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Description		:	Updates Details of Process Date in Database 
	 ********************************************************************************************************/
	
	@Override
	public RawMaterialStockEntity updateProcessDateInStock(String id, Date processDate) {
		RawMaterialStockEntity stock=trackRawMaterialOrder(id);
		if(processDateCheck(stock, processDate)) {
			stock.setProcessDate(processDate);
			return stock;
		}
		else 
			throw new ProcessDateException("Process Date can not be updated");
		
		
	}
	
	
	/*******************************************************************************************************
	 - Function Name	:	validateManufacturingDate
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Description		:	checking the Manufacturing Date 
	 ********************************************************************************************************/
	@Override
	public Boolean validateManufacturingDate(RawMaterialStockEntity rmstock,Date manufacturngDate) {
		if(rmstock.getProcessDate().after(manufacturngDate)){
			return true;
		}
		return false;
	}
	
	
	

	/*******************************************************************************************************
	 - Function Name	:	validateExpiryDate
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Description		:	checking the Expire Date 
	 ********************************************************************************************************/
	@Override
	public Boolean validateExpiryDate(RawMaterialStockEntity rmstock,Date expirydate) {
		if(rmstock.getProcessDate().before(expirydate)){
			return true;
		}
		return false;
	}
	
	
	
	/*******************************************************************************************************
	 - Function Name	:	updateRawMaterialStock
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Description		:	Updates Details of Stock in Database and add stock if not present
	 ********************************************************************************************************/
	@Override
	public RawMaterialStockEntity updateRawMaterialStock(RawMaterialStockEntity rmstock,Date expirydate,Date manufacturingDate,String qa) {
			if(expirydate.equals(null) && manufacturingDate.equals(null) && qa.equals(null)) {
				throw new EmptyDataException("Empty Data Exception catched");
			}
			if(validateManufacturingDate(rmstock,manufacturingDate) && validateExpiryDate(rmstock,expirydate)) {
				if(rmstock.getManufacturingDate().before(rmstock.getExpiryDate())) {
					rmstock=stockdao.save(rmstock);
					return rmstock;
				}
				else {
					throw new UpdationFailureException("Manufacturing must be before the expity Date");
				}
			}
			else {
				throw new UpdationFailureException("Check Manufacturing and Expiry Date");
			}
		     
	}
	

	@Override
	public RawMaterialStockEntity add(RawMaterialStockEntity stock)  {
		if(stock.getOrderId().equals(null) &&stock.getName().equals(null) && 
		       stock.getWarehouseId().equals(null) && stock.getSupplierId().equals(null) && stock.getProcessDate().equals(null)) {
			throw new EmptyDataException("Empty Data Exception catched");
		}
		if(stock.getPrice_per_unit()<=0 && stock.getQuantityUnit()<=0 && 
			stock.getQuantityValue()<=0 ) {
			throw new NegativeValueException("Values cannot be negative");
		}
		stock.setStockId(generatedId(9));
		stock.setDeliveryDate(new Date());
		if(processDateCheck(stock, stock.getProcessDate())){
			stock.setPrice(stock.getPrice_per_unit()*stock.getQuantityUnit());
			stock=stockdao.save(stock);
			return stock;
		}
		else {
			throw new ProcessDateException("Process date must be after delivery Date");
		}
		
	}



	String generatedId(int digits) {
		StringBuilder id=new StringBuilder();
		for(int i=0;i<digits;i++)
		{
			Random random=new Random();
			int num=random.nextInt(9);
			id.append(num);
		}
		return id.toString();
	}



	@Override
	public List<RawMaterialStockEntity> fetchAllRawMaterialStock() {
		List<RawMaterialStockEntity> stocks=stockdao.findAll();
		return stocks;
	}


}


package org.cap.drinkanddelightmgt.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.cap.drinkanddelightmgt.dto.RawMaterialStockDisplayDto;
import org.cap.drinkanddelightmgt.dto.RawMaterialStockRequestDto;
import org.cap.drinkanddelightmgt.entities.RawMaterialStockEntity;
import org.cap.drinkanddelightmgt.exception.*;
import org.cap.drinkanddelightmgt.service.IRawMaterialStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RMStock")
public class RawMaterialStockRestcontroller {
	@Autowired
	IRawMaterialStockService service;
	
	@GetMapping
	   public ResponseEntity<List<RawMaterialStockDisplayDto>>fetchAllDistributor(){
		List<RawMaterialStockEntity> rawMaterialStocks= service.fetchAllRawMaterialStock();
		List<RawMaterialStockDisplayDto> rawMaterialStockDtos =convertToDisplayDtos(rawMaterialStocks);
	      ResponseEntity<List<RawMaterialStockDisplayDto>>response=new ResponseEntity<>(rawMaterialStockDtos,HttpStatus.OK);
	      return response;
	   } 
	
	
	private List<RawMaterialStockDisplayDto> convertToDisplayDtos(List<RawMaterialStockEntity> entities) {
		List<RawMaterialStockDisplayDto> list=new ArrayList<>();
		for (RawMaterialStockEntity entity : entities) {
			RawMaterialStockDisplayDto dto= convertToDisplayDto(entity);
			list.add(dto);
		}
		return list;
	}


	@PostMapping("/add")
	public ResponseEntity<RawMaterialStockDisplayDto>add(@RequestBody RawMaterialStockRequestDto requestdto){
		RawMaterialStockEntity stock=convertToStock(requestdto);
		stock=service.add(stock);
		RawMaterialStockDisplayDto displaydto=convertToDisplayDto(stock);
		ResponseEntity<RawMaterialStockDisplayDto> response=new ResponseEntity<>(displaydto,HttpStatus.OK);
		return response;
	}

	private RawMaterialStockDisplayDto convertToDisplayDto(RawMaterialStockEntity stock) {
		RawMaterialStockDisplayDto dto=new RawMaterialStockDisplayDto();
		dto.setStockId(stock.getStockId());
		dto.setOrderId(stock.getOrderId());
		dto.setName(stock.getName());
		dto.setPrice_per_unit(stock.getPrice_per_unit());
		dto.setQuantityUnit(stock.getQuantityUnit());
		dto.setQuantityValue(stock.getQuantityValue());
		dto.setPrice(stock.getPrice());
		dto.setWarehouseId(stock.getWarehouseId());
		dto.setDeliveryDate(stock.getDeliveryDate());
		dto.setExpiryDate(stock.getExpiryDate());
		dto.setManufacturingDate(stock.getManufacturingDate());
		dto.setQualityCheck(stock.getQualityCheck());
		dto.setProcessDate(stock.getProcessDate());
		return dto;
	}

	private RawMaterialStockEntity convertToStock(RawMaterialStockRequestDto dto) {
		RawMaterialStockEntity stock=new RawMaterialStockEntity();
		stock.setOrderId(dto.getOrderId());
		stock.setSupplierId(dto.getSupplierId());
		stock.setName(dto.getName());
		stock.setPrice_per_unit(dto.getPrice_per_unit());
		stock.setQuantityValue(dto.getQuantityValue());
		stock.setQuantityUnit(dto.getQuantityUnit());
		stock.setWarehouseId(dto.getWarehouseId());
		stock.setProcessDate(dto.getProcessDate());
		return stock;
	}
	
	@GetMapping("/TrackRawMaterial/{id}")
	public ResponseEntity<RawMaterialStockDisplayDto> fetchById(@PathVariable("id")  String id){
		service.doesRawMaterialOrderIdExistInStock(id);
		RawMaterialStockEntity stock=service.trackRawMaterialOrder(id);
		RawMaterialStockDisplayDto displaydto=convertToDisplayDto(stock);
		ResponseEntity<RawMaterialStockDisplayDto> response=new ResponseEntity<>(displaydto,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/UpdateProcessDate/{id}")
	public ResponseEntity<RawMaterialStockDisplayDto> updateProcessDate(@PathVariable("id")  String id,@RequestBody RawMaterialStockRequestDto updateDto)
	{
		service.doesRawMaterialOrderIdExistInStock(updateDto.getOrderId());
		RawMaterialStockEntity stock=service.trackRawMaterialOrder(id);
		stock=service.updateProcessDateInStock(id, updateDto.getProcessDate());
		RawMaterialStockDisplayDto displaydto=convertToDisplayDto(stock);
		ResponseEntity<RawMaterialStockDisplayDto> response=new ResponseEntity<>(displaydto,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/Update/{id}")
	public ResponseEntity<RawMaterialStockDisplayDto> UpdateManufacturingDateExpiryDateDateAndQA(@PathVariable("id")  String id,@RequestBody RawMaterialStockRequestDto updateDto)
	{
		service.doesRawMaterialOrderIdExistInStock(updateDto.getOrderId());
		RawMaterialStockEntity stock=service.trackRawMaterialOrder(id);
		stock=service.updateRawMaterialStock(stock,updateDto.getExpiryDate(),updateDto.getManufacturingDate(),updateDto.getQualityCheck());
		RawMaterialStockDisplayDto displaydto=convertToDisplayDto(stock);
		ResponseEntity<RawMaterialStockDisplayDto> response=new ResponseEntity<>(displaydto,HttpStatus.OK);
		return response;
	}

	
	@ExceptionHandler(RawMaterialStockNotFoundException.class)
	   public ResponseEntity<String>handleEmployeeNotFound(RawMaterialStockNotFoundException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
	       return response;
	   }

	@ExceptionHandler(IncompleteDataException.class)
	   public ResponseEntity<String>handleIncompleteData(IncompleteDataException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.PARTIAL_CONTENT);
	       return response;
	   }

	@ExceptionHandler(ProcessDateException.class)
	   public ResponseEntity<String>handleProcessDateException(ProcessDateException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	@ExceptionHandler(DateTimeFormatException.class)
	   public ResponseEntity<String>handleDateTimeFormatException(DateTimeFormatException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	@ExceptionHandler(InvalidOrderIdException.class)
	   public ResponseEntity<String>handleInvalidOrderIdException(InvalidOrderIdException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	@ExceptionHandler(EmptyDataException.class)
	   public ResponseEntity<String>handleEmptyException(EmptyDataException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	@ExceptionHandler(NegativeValueException.class)
	   public ResponseEntity<String>handleEmptyException(NegativeValueException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }
}

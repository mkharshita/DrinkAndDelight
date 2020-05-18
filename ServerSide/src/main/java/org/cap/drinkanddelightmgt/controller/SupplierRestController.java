package org.cap.drinkanddelightmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.cap.drinkanddelightmgt.dto.SupplierDto;
import org.cap.drinkanddelightmgt.entities.SupplierEntity;
import org.cap.drinkanddelightmgt.exception.EmptyDataException;
import org.cap.drinkanddelightmgt.exception.SupplierNotFoundException;
import org.cap.drinkanddelightmgt.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierRestController {
	//private static final Logger Log= LoggerFactory.getLogger(SupplierRestController.class);

    @Autowired
    private ISupplierService service;
    
    @GetMapping
	   public ResponseEntity<List<SupplierDto>>fetchAllSuppliers(){
		List<SupplierEntity> suppliers= service.fetchAllSupplier();
		List<SupplierDto> supplierDtos =convertToSupplierDtos(suppliers);
	      ResponseEntity<List<SupplierDto>>response=new ResponseEntity<>(supplierDtos,HttpStatus.OK);
	      return response;
	   } 
	
	
	
	private List<SupplierDto> convertToSupplierDtos(List<SupplierEntity> suppliers) {
		List<SupplierDto> list = new ArrayList<>();
		for (SupplierEntity supplier : suppliers) {
			SupplierDto supplierDto = convertToSupplierDto(supplier);
			list.add(supplierDto);
		}
		return list;
	}
	
	
    private SupplierDto convertToSupplierDto(SupplierEntity supplier) {
    	SupplierDto supplierDto=new SupplierDto();
    	supplierDto.setSupplierId(supplier.getSupplierId());
    	supplierDto.setSupplierName(supplier.getSupplierName());
    	supplierDto.setSupplierAddress(supplier.getSupplierAddress());
    	supplierDto.setSupplierPhoneNumber(supplier.getSupplierPhoneNumber());
    	return supplierDto;
	}



	@PostMapping("/add")
    public ResponseEntity<SupplierDto>addSupplier(@RequestBody SupplierDto dto){
    	SupplierEntity supplierEntity=convertToSupplier(dto);
    	supplierEntity=service.add(supplierEntity);
    	dto=convertToSupplierDto(supplierEntity);
    	ResponseEntity<SupplierDto>response=new ResponseEntity<>(dto,HttpStatus.OK);
    	return response;
    }

	private SupplierEntity convertToSupplier(SupplierDto dto) {
		SupplierEntity supplierEntity=new SupplierEntity();
		supplierEntity.setSupplierName(dto.getSupplierName());
		supplierEntity.setSupplierAddress(dto.getSupplierAddress());
		supplierEntity.setSupplierPhoneNumber(dto.getSupplierPhoneNumber());
		return supplierEntity;
	}

	 @GetMapping("/getbyid/{id}")
	   public ResponseEntity<SupplierDto>findEmployeeById( @PathVariable("id")  String id){
	      SupplierEntity supplierEntity= service.findById(id);
	      SupplierDto supplierDto=convertToSupplierDto(supplierEntity);
	      ResponseEntity<SupplierDto>response=new ResponseEntity<>(supplierDto,HttpStatus.OK);
	      return response;
	   }
	 
	 @ExceptionHandler(EmptyDataException.class)
	   public ResponseEntity<String>handleEmptyDataException(EmptyDataException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	 @ExceptionHandler(SupplierNotFoundException.class)
	   public ResponseEntity<String>handleIdNotFindIdException(SupplierNotFoundException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }



 }



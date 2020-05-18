package org.cap.drinkanddelightmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.cap.drinkanddelightmgt.dto.DistributorDto;
import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.cap.drinkanddelightmgt.exception.DistributorNotFoundException;
import org.cap.drinkanddelightmgt.exception.EmptyDataException;
import org.cap.drinkanddelightmgt.service.IDistributorService;
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
@RequestMapping("/distributor")
public class DistributorRestController {
	
	@Autowired
	private IDistributorService service;
	
	@GetMapping
	   public ResponseEntity<List<DistributorDto>>fetchAllDistributor(){
		List<DistributorEntity> distributors= service.fetchAllDistributor();
		List<DistributorDto> distributorsDtos =convertToDistributorDtos(distributors);
	      ResponseEntity<List<DistributorDto>>response=new ResponseEntity<>(distributorsDtos,HttpStatus.OK);
	      return response;
	   } 
	
	
	
	private List<DistributorDto> convertToDistributorDtos(List<DistributorEntity> distributors) {
		List<DistributorDto> list = new ArrayList<>();
		for (DistributorEntity distributor : distributors) {
			DistributorDto distributorDto = convertToDistributorDto(distributor);
			list.add(distributorDto);
		}
		return list;
	}



	@PostMapping("/add")
    public ResponseEntity<DistributorDto>addDistributor(@RequestBody DistributorDto dto){
		DistributorEntity distributorEntity=convertToDistributor(dto);
    	distributorEntity=service.add(distributorEntity);
    	dto=convertToDistributorDto(distributorEntity);
    	ResponseEntity<DistributorDto>response=new ResponseEntity<>(dto,HttpStatus.OK);
    	return response;
    }

	private DistributorDto convertToDistributorDto(DistributorEntity distributorEntity) {
		DistributorDto distributorDto=new DistributorDto();
		distributorDto.setDistributorId(distributorEntity.getDistributorId());
		distributorDto.setDistributorName(distributorEntity.getDistributorName());
		distributorDto.setDistributorAddress(distributorEntity.getDistributorAddress());
		distributorDto.setDistributorPhoneNumber(distributorEntity.getDistributorPhoneNumber());
		return distributorDto;
	}

	private DistributorEntity convertToDistributor(DistributorDto dto) {
		DistributorEntity distributorEntity=new DistributorEntity();
		distributorEntity.setDistributorName(dto.getDistributorName());
		distributorEntity.setDistributorAddress(dto.getDistributorAddress());
		distributorEntity.setDistributorPhoneNumber(dto.getDistributorPhoneNumber());
		return distributorEntity;
	}
	
	@GetMapping("/getbyid/{id}")
	   public ResponseEntity<DistributorDto>findDistributorById( @PathVariable("id")  String id){
		DistributorEntity distributorEntity= service.findById(id);
		DistributorDto distributorDto=convertToDistributorDto(distributorEntity);
	      ResponseEntity<DistributorDto>response=new ResponseEntity<>(distributorDto,HttpStatus.OK);
	      return response;
	   }
	
	@ExceptionHandler(EmptyDataException.class)
	   public ResponseEntity<String>handleEmptyDataException(EmptyDataException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	}
	@ExceptionHandler(DistributorNotFoundException.class)
	   public ResponseEntity<String>handleIdNotFindIdException(DistributorNotFoundException ex){
	       String msg=ex.getMessage();
	       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	       return response;
	   }

	
}

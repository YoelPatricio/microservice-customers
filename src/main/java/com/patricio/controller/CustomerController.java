package com.patricio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.JsonObject;
import com.patricio.model.Customer;
import com.patricio.service.ICustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/customers")
@Api(value = "Customers microservice")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Crear un Cliente", notes = "Retorna codigo 201 created")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Customer customer) {
		Customer cus = new Customer();
		cus = customerService.registrar(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cus.getIdCustomer()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listar Clientes", notes = "Retorna un listado de clientes")
	public ResponseEntity<List<Customer>> listar() {
		List<Customer> customers = new ArrayList<>();
		try {
			customers = customerService.listar();
		} catch (Exception e) {
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/statistics",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listar Estadistica de Clientes", notes = "Retorna el promedio de edad y desviación estándar de edades de todos los clientes.")
	public String getStatistics() {
		
		Integer averageAge = customerService.getAverageAge();
		Double standardDeviation = customerService.getStandardDeviation();
		
		JsonObject person = new JsonObject();
		person.addProperty("averageAge", averageAge);
		person.addProperty("standardDeviation", standardDeviation);
		
		return person.toString();
	}
}

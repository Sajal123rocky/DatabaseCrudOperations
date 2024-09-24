package com.nw.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nw.entity.Customer;
import com.nw.repository.CustomerRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/save")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping("/findAll")
	public List<Customer> showAll(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Optional<Customer> show(@PathVariable int id) {
		
		Optional<Customer> cu=customerRepository.findById(id);
		return customerRepository.findById(id);
	}
	
	@GetMapping("/search")
	public Customer search(@PathParam("id")int id) {
		return customerRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		customerRepository.deleteById(id);
		return "delete success";
	}
	
	@GetMapping("/showByPaging")
	public List<Customer> showPageCustomers(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "5") int pageSize,@RequestParam(defaultValue = "name") String sortBy ){
		PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Customer> pc =customerRepository.findAll(pageRequest);
		return pc.getContent();
		
	}
	
	
}

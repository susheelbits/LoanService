package com.bits.cexp.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bits.cexp.loan.dto.LoanDTO;
import com.bits.cexp.loan.model.Loan;
import com.bits.cexp.loan.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

	private LoanService loanService;

	public LoanController(LoanService loanService) {
		super();
		this.loanService = loanService;
	}	

	// build create Customer REST API
	@PostMapping()
	public ResponseEntity<Loan> saveCustomer(@RequestBody Loan loan){
		return new ResponseEntity<Loan>(loanService.saveLoan(loan), HttpStatus.CREATED);
	}	

	// build get all employees REST API
	@GetMapping
	public List<Loan> getAllLoans(){
		return loanService.getAllLoans();
	}	

	// build get customer by id REST API
	// http://localhost:8080/api/loans/1
	@GetMapping("{id}")
	public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") long loanId){
		return new ResponseEntity<LoanDTO>(loanService.getLoanById(loanId), HttpStatus.OK);
	}

	// build update customer REST API
	// http://localhost:8080/api/loans/1
	@PutMapping("{id}")
	public ResponseEntity<Loan> updateLoan(@PathVariable("id") long id
												  ,@RequestBody Loan loan){
		return new ResponseEntity<Loan>(loanService.updateLoan(loan, id), HttpStatus.OK);
	}	

	// build delete customer REST API
	// http://localhost:8080/api/loans/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLoan(@PathVariable("id") long id){
		
		// delete employee from DB
		loanService.deleteLoan(id);
		
		return new ResponseEntity<String>("Loan deleted successfully!.", HttpStatus.OK);
	}		

}


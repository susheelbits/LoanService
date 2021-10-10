package com.bits.cexp.loan.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bits.cexp.loan.ResourceNotFoundException;
import com.bits.cexp.loan.dto.LoanDTO;
import com.bits.cexp.loan.dto.CustomerDTO;
import com.bits.cexp.loan.model.Loan;
import com.bits.cexp.loan.repository.LoanRepository;
import com.bits.cexp.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	private LoanRepository loanRepository;

	public LoanServiceImpl(LoanRepository loanRepository) {
		super();
		this.loanRepository = loanRepository;
	}	
	
	public Loan saveLoan(Loan loan) {
		
    	System.out.println("Inside saveLoan method of LoanServiceImpl");
    	Loan acct = loanRepository.save(loan);
    	System.out.println(" Saved and returned " + acct.toString());
    	return acct;
	}	
	
	public LoanDTO getLoanById(Long loanId) {
    	System.out.println("Inside getLoanById method of LoanServiceImpl");
		System.out.println("******* GET ACCOUNT BY ID");
		
		String baseUrl = "http://localhost:8081/api/customers/1"; RestTemplate
		restTemplate = new RestTemplate(); String result =
		restTemplate.getForObject(baseUrl, String.class);
		System.out.println("****** Printing the oject "+result.toString());
		 
		CustomerDTO customer = restTemplate.getForObject(baseUrl, CustomerDTO.class);
		
		System.out.println("*******customer  "+customer.getFirstName());
    	Loan acctDb =  loanRepository.findById(loanId).orElseThrow(() -> 
									new ResourceNotFoundException("Loan", "Id", loanId));
    	LoanDTO acctDto = new LoanDTO (acctDb.getName(), acctDb.getBalance(), customer);
    	acctDto.setLoanNumber(acctDb.getLoanNumber());
		/*
		 * acctDto.setCustomerId(acctDto.getCustomer().getCustomerId());
		 * acctDto.setFirstName(acctDto.getCustomer().getFirstName());
		 */
    	
    	return acctDto;
	}	

	@Override
	public Loan updateLoan(Loan loan, long id){
		
		// we need to check whether customer with given id is exist in DB or not
		Loan existingLoan = loanRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Loan", "Id", id)); 
		
		existingLoan.setName(loan.getName());
		existingLoan.setBalance(loan.getBalance());
		// save existing customer to DB
		loanRepository.save(existingLoan);
		
		String baseUrl = "http://localhost:9092/loan-to-deposit-rabbitmq/producer?firstName=emp1&depositId=1"; 
		RestTemplate restTemplate = new RestTemplate(); 
		String result = restTemplate.getForObject(baseUrl, String.class);
		
		System.out.println("******* stop ");		
		return existingLoan;
	}	 

	@Override
	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}	

	@Override
	public void deleteLoan(long id) {
		
		// check whether a customerRepository exist in a DB or not
		loanRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Loan", "Id", id));
		loanRepository.deleteById(id);
	}		
}

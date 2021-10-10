package com.bits.cexp.loan.service;

import java.util.List;

import com.bits.cexp.loan.dto.LoanDTO;
import com.bits.cexp.loan.model.Loan;


public interface LoanService {
	 public Loan saveLoan(Loan deposit);
	 
	 public LoanDTO getLoanById(Long depositId);
	 
	 public List<Loan> getAllLoans();
	 
	 public Loan updateLoan(Loan deposit, long id);
	 
	 void deleteLoan(long id);	
}

package com.bits.cexp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bits.cexp.loan.model.Loan;



public interface LoanRepository extends JpaRepository<Loan, Long>  {

}

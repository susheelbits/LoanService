package com.bits.cexp.loan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="loan")
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanNumber;	
	
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "name")
    private String name;
    //private Customer holder;
    
    //private Arraylist<Tansaction> transactions;
	
	public Loan() {

	}
	
	public Loan(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}		

	public Long getLoanNumber() {
		return loanNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}			
}

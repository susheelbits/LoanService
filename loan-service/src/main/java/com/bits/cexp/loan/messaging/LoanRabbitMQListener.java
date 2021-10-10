package com.bits.cexp.loan.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.bits.cexp.loan.dto.DepositDTO;





@Service
public class LoanRabbitMQListener {
	
    @RabbitListener(queues = "deposit-to-loan.queue")
    public void processOrder(DepositDTO deposit) {
    	System.out.println("Loan Received: "+deposit);
    	
    	//If Id does not exists then, Add the Customer 
    	//If Id exists, then either update or delete the customer
    	//Send Acknowledgement of the operation 
    	
    }	
}

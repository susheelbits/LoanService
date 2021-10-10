package com.bits.cexp.loan.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.cexp.loan.dto.DepositDTO;



@RestController
@RequestMapping(value = "/loan-to-deposit-rabbitmq/")
public class LoanRabbitMQController {

	
	/* @Autowired */
	LoanRabbitMQSender rabbitMQSender;

    @Autowired
    public LoanRabbitMQController(LoanRabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }
    

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("firstName") String firstName,
							@RequestParam("depositId") long depositId) {
		
		DepositDTO dep=new DepositDTO();
		dep.setBalance(depositId);
		dep.setName(firstName);
		rabbitMQSender.send(dep);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}	
}

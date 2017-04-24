package org.ProxiBanque.aspect;

import org.ProxiBanque.model.BankAccount;
import org.ProxiBanque.model.Footprint;
import org.ProxiBanque.model.Footprint.e_HeadType;
import org.ProxiBanque.model.Footprint.e_State;
import org.ProxiBanque.service.IServiceFootPrint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * AOP permettant la transversalité pour la communication
 * avec le dashboard
 *  
 * @author Kévin, Jonas, Andy, Mathieu
 * @version 1.0
 */
@Component
@Aspect
public class FootPrintAspect {
	private static Logger logger = LoggerFactory.getLogger(FootPrintAspect.class);

	@Autowired
	IServiceFootPrint service;

	@Pointcut("execution(* org.ProxiBanque.service.IServiceAccount.doVirement(..))")
	public void virement() {
	}
	
	@Around("virement()")
	public void traceVirement(ProceedingJoinPoint jp) {
		
		Object[] args = jp.getArgs();
		String body;
		
		try {
			
			jp.proceed();
		} catch (Throwable e) {
			
			
			
			
			body = "from n° " + ((BankAccount)args[0]).getAccountNumber() + " (client n° "
						  + ((BankAccount)args[0]).getClient().getId() + ") to n° "
						  + ((BankAccount)args[1]).getAccountNumber() + " (client n° "
						  + ((BankAccount)args[1]).getClient().getId() + "), amount :" 
						  + ((Double)args[2]) + ", reason : ";
			if (e.getMessage().equals("pas le droit pour un même compte")) {
				
				body += "same account";
			} else if (e.getMessage().equals("solde insuffisant")) {
				
				body += "unsufficient funds";
			}
			logger.info(body);
			service.save(new Footprint(e_HeadType.TRANSACTION, body, e_State.FAIL));
			System.err.println(body);
		}
		
		body = "from n° " + ((BankAccount)args[0]).getAccountNumber() + " (client n° "
				  + ((BankAccount)args[0]).getClient().getId() + ") to n° "
				  + ((BankAccount)args[1]).getAccountNumber() + " (client n° "
				  + ((BankAccount)args[1]).getClient().getId() + "), amount :" 
				  + ((Double)args[2]);
		logger.info(body);
		service.save(new Footprint(e_HeadType.TRANSACTION, body, e_State.SUCCESS));
		System.err.println(body);
	}

}

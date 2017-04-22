package org.ProxiBanque.aspect;

import org.ProxiBanque.model.Footprint;
import org.ProxiBanque.model.Footprint.e_HeadType;
import org.ProxiBanque.model.Footprint.e_State;
import org.ProxiBanque.service.IServiceFootPrint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class FootPrintAspect {
	private static Logger logger = LoggerFactory.getLogger(FootPrintAspect.class);
	
	@Autowired
	IServiceFootPrint service;
	
	 @Pointcut("execution(*org.Proxibanque.service.*doVirement(..))")
	     public void virement(){
	     }
	 
	 
	 @AfterThrowing(pointcut = "virement()", throwing = "ex")
	 public void afterFail(RuntimeException ex)
	 {
		 service.save(new Footprint(e_HeadType.TRANSACTION, " Transaction impossible : "+ex.getStackTrace(), e_State.CANCELED));
	 }
	 
	 @AfterReturning
	 public void afterReturningClassique()
	 {
		 service.save(new Footprint(e_HeadType.TRANSACTION, "Transaction éffectuer", e_State.SUCCESS));
	 }
	 

}

package com.situ.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.situ.crm.service.ICustomerLossService;

@Component
public class CustomerLossQuartzTask {
	
	@Autowired
	private ICustomerLossService customerLossService;
	
	@Scheduled(cron="0 0 2 * * *")
	public void customerLossTimerTask() {
		//查询即将流失的客户
		customerLossService.timerAddLossCustomer();
	}
}

package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.bean.Customer;
import com.situ.crm.bean.CustomerLoss;
import com.situ.crm.bean.CustomerOrder;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerLossMapper;
import com.situ.crm.mapper.CustomerMapper;
import com.situ.crm.mapper.CustomerOrderMapper;
import com.situ.crm.service.ICustomerLossService;
import com.situ.crm.vo.CustomerLossCountVO;

@Service
public class CustomerLossServiceImpl implements ICustomerLossService {
	@Autowired
	private CustomerLossMapper customerLossMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public ServerResponse customerLossAnalysis() {
		// TODO Auto-generated method stub
		List<CustomerLossCountVO> list = customerLossMapper.customerLossAnalysis();
		if (list == null || list.size() == 0) {
			return ServerResponse.createError("暂无数据");
		}
		return ServerResponse.createSuccess("数据查找成功", list);
	}

	@Override
	public DataGridResponse<CustomerLoss> pageList(Integer page, Integer rows, CustomerLoss saleChance) {
		// TODO Auto-generated method stub
		// 设置开启分页
		PageHelper.startPage(page, rows);
		List<CustomerLoss> listPage = customerLossMapper.pageList(saleChance);

		PageInfo<CustomerLoss> pageInfo = new PageInfo<CustomerLoss>(listPage);
		// 获取总记录的条数
		Integer total = (int) pageInfo.getTotal();

		// Integer total=(int) PageHelper.count(select);
		DataGridResponse<CustomerLoss> dataGridResponse = new DataGridResponse<CustomerLoss>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public List<Map<String, Object>> getStatus() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Integer> listStatus = customerLossMapper.getStatus();
		for (Integer status : listStatus) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", status);
			// Map<String, String> statusMap=new HashMap<>();
			if (status == 0) {
				map.put("value", "未分配");
			} else {
				map.put("value", "已分配");
			}
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public DataGridResponse<CustomerLoss> getCustomerLossInfoById(Integer id) {
		// TODO Auto-generated method stub
		CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
		List<CustomerLoss> list = new ArrayList<CustomerLoss>();
		list.add(customerLoss);
		DataGridResponse<CustomerLoss> dataGridResponse = new DataGridResponse<CustomerLoss>(list.size(), list);
		return dataGridResponse;
	}

	@Override
	/**
	 * 把要流失的客户定时插入到customer_loss表中
	 */
	public void timerAddLossCustomer() {
		// TODO Auto-generated method stub
		System.out.println("CustomerLossServiceImpl.timerAddLossCustomer()");
		// 1.查询出所有符合条件的即将流失的客户
		List<Customer> customerList = customerMapper.selectBeingLossCustomer();
		for (Customer customer : customerList) {
			// 2.将流失的客户插入到customer_loss表中
			// 2.1先将CustomerLoss（由两部分组成，一、Customer；二、CustomerLoss）对象
			// 2.2先把customer 对象的部分属性放到CustomerLoss表中
			CustomerLoss customerLoss = new CustomerLoss();
			customerLoss.setCustomerNo(customer.getNum());
			customerLoss.setCustomerName(customer.getName());
			customerLoss.setCustomerManager(customer.getManagerName());
			Integer id=customer.getId();
			CustomerOrder customerOrder = customerOrderMapper.selectCustomerOrderByCusId(id);
			if (customerOrder != null) {
				customerLoss.setLastOrderTime(customerOrder.getOrderDate());
			}else {
				customerLoss.setLastOrderTime(null);
			}
			//3将此customerloss对象插入到customer_loss表中
			customerLossMapper.insert(customerLoss);
			customer.setStatus(1);
			customerMapper.updateByPrimaryKeySelective(customer);
		}

	}
}

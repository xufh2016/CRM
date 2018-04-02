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
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerMapper;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.vo.CustomerContribution;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridResponse pageList(Integer page, Integer rows, Customer customer) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<Customer> listPage = customerMapper.pageList(customer);

		PageInfo<Customer> pageInfo = new PageInfo<Customer>(listPage);
		// 获取总记录的条数
		Integer total = (int) pageInfo.getTotal();

		// Integer total=(int) PageHelper.count(select);
		DataGridResponse<Customer> dataGridResponse = new DataGridResponse<>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doDelete(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		int count = customerMapper.doDelete(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse doAdd(Customer customer) {
		// TODO Auto-generated method stub
		int count = customerMapper.insert(customer);
		if (count > 0) {
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse doUpdate(Customer customer) {
		// TODO Auto-generated method stub
		int count = customerMapper.updateByPrimaryKey(customer);
		if (count > 0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<Map<String, String>> getRegion() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<String> listStatus = customerMapper.getRegion();
		for (String region : listStatus) {
			Map<String, String> map = new HashMap<>();
			map.put("region", region);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public List<Map<String, String>> getManagerName() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<String> listStatus = customerMapper.getManagerName();
		for (String managerName : listStatus) {
			Map<String, String> map = new HashMap<>();
			map.put("managerName", managerName);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public List<Map<String, String>> getLevel() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<String> listStatus = customerMapper.getLevel();
		for (String level : listStatus) {
			Map<String, String> map = new HashMap<>();
			map.put("level", level);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public List<Map<String, String>> getCredit() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<String> listStatus = customerMapper.getCredit();
		for (String credit : listStatus) {
			Map<String, String> map = new HashMap<>();
			map.put("credit", credit);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public List<Map<String, String>> getSatisfy() {
		// TODO Auto-generated method stub
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<String> listStatus = customerMapper.getSatisfy();
		for (String satisfy : listStatus) {
			Map<String, String> map = new HashMap<>();
			map.put("satisfy", satisfy);
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public DataGridResponse<Customer> getAllLinkManByCusId(Integer id) {
		// TODO Auto-generated method stub

		List<Customer> rows = customerMapper.selectByCustomerId(id);
		Integer total = rows.size();
		DataGridResponse<Customer> dataGridResponse = new DataGridResponse<>(total, rows);

		return dataGridResponse;
	}

	@Override
	public DataGridResponse<Customer> getCustomerBasicInfo(Integer id) {
		// TODO Auto-generated method stub
		List<Customer> rows = new ArrayList<>();
		Customer customer = customerMapper.selectByPrimaryKey(id);
		rows.add(customer);
		Integer total = rows.size();

		DataGridResponse<Customer> dataGridResponse = new DataGridResponse<>(total, rows);

		return dataGridResponse;
	}

	@Override
	public DataGridResponse<CustomerContribution> customerContribution() {
		// TODO Auto-generated method stub
		List<CustomerContribution> customerContributions = customerMapper.selectAllCustomerContribution();
		DataGridResponse<CustomerContribution> dataGridResponse = new DataGridResponse<>(customerContributions.size(),
				customerContributions);
		return dataGridResponse;
	}
}

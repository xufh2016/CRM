package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.bean.Product;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@RequestMapping("/getProductInfoPage")
	public String  getProductInfoPage() {
		return "product_info";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public DataGridResponse<Product>  pageList(Integer page,Integer rows,Product product) {
		
		return productService.pageList(page,rows,product);
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public ServerResponse doDelete(String ids) {
		return productService.doDelete(ids);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ServerResponse doAdd(Product product) {
		return productService.doAdd(product);
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ServerResponse doUpdate(Product product) {
		return productService.doUpdate(product);
	}
}

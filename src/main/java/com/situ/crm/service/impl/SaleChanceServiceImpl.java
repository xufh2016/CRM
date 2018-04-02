package com.situ.crm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.situ.crm.bean.SaleChance;
import com.situ.crm.common.DataGridResponse;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.SaleChanceMapper;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.vo.SearchSaleChanceCondition;

@Service
public class SaleChanceServiceImpl implements ISaleChanceService {

	@Autowired
	private SaleChanceMapper salechanceMapper;

	@Override
	public DataGridResponse<SaleChance> pageList(Integer page, Integer rows, SearchSaleChanceCondition saleChance) {
		// TODO Auto-generated method stub
		// 设置开启分页
		PageHelper.startPage(page, rows);
		System.out.println("SaleChanceServiceImpl.pageList()" + saleChance.getStatus());
		List<SaleChance> listPage = salechanceMapper.pageList(saleChance);

		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(listPage);
		// 获取总记录的条数
		Integer total = (int) pageInfo.getTotal();

		// Integer total=(int) PageHelper.count(select);
		DataGridResponse<SaleChance> dataGridResponse = new DataGridResponse<>(total, listPage);
		return dataGridResponse;
	}

	@Override
	public ServerResponse doDelete(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		int count = salechanceMapper.doDelete(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse doAdd(SaleChance salechance) {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(salechance.getAssignMan())) {
			salechance.setStatus(0);
		} else {
			salechance.setStatus(1);
		}
		int count = salechanceMapper.insert(salechance);
		if (count > 0) {
			return ServerResponse.createSuccess("添加成功");
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse doUpdate(SaleChance salechance) {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(salechance.getAssignMan())) {
			salechance.setStatus(0);
		} else {
			salechance.setStatus(1);
		}
		int count = salechanceMapper.updateByPrimaryKey(salechance);
		if (count > 0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<Map<String, Object>> getStatus() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Integer> listStatus = salechanceMapper.selectStatus();
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
	public List<Map<String, Object>> getDevResult() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Integer> listDevResult = salechanceMapper.getDevResult();
		for (Integer devResult : listDevResult) {
			Map<String, Object> map = new HashMap<>();
			map.put("devResult", devResult);
			// Map<String, String> statusMap=new HashMap<>();
			if (devResult != null) {
				if (devResult == 0) {
					map.put("value", "未开发");
				} else if (devResult == 1) {
					map.put("value", "开发中");
				} else if (devResult == 2) {
					map.put("value", "开发成功");
				} else if (devResult == 3) {
					map.put("value", "开发失败");
				}
			}
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public ServerResponse getCustomerDevPlan(Integer id) {
		// TODO Auto-generated method stub
		SaleChance saleChance = salechanceMapper.selectByPrimaryKey(id);
		if (saleChance != null) {
			return ServerResponse.createSuccess("查找成功", saleChance);
		}
		return ServerResponse.createError("未找到数据");
	}

	@Override
	public ServerResponse updateDevResult(Integer saleChanceId, Integer devResult) {
		// TODO Auto-generated method stub
		int count = salechanceMapper.updateDevResult(saleChanceId, devResult);
		if (count > 0)
			return ServerResponse.createSuccess("更新成功");
		return ServerResponse.createError("更新失败");
	}

	@Override
	public void exportExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<SaleChance> saleChances = salechanceMapper.selectAll();

		// 得到输出流
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 得到workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 得到sheet
		HSSFSheet sheet = workbook.createSheet("用户列表");
		sheet.setDefaultColumnWidth(25);
		// 创建合并单元格对象
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 6, 1, 12);
		sheet.addMergedRegion(cellRangeAddress);
		// 创建行：Row
		HSSFRow rowTitle = sheet.createRow(2);
		// 创建单元格
		HSSFCell cellTitle = rowTitle.createCell(1);

		HSSFCellStyle style = createStyle(workbook, 25);
		cellTitle.setCellStyle(style);
		cellTitle.setCellValue("销售机会报表");
		HSSFRow rowHead = sheet.createRow(7);
		String[] titleArray = { "编号", "客户名称", "概要", "联系人" };
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell cellHead = rowHead.createCell(i + 1);
			HSSFCellStyle headStyle = createStyle(workbook, 13);
			cellHead.setCellStyle(headStyle);
			cellHead.setCellValue(titleArray[i]);
		}
		HSSFCellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		for (int i = 0; i < saleChances.size(); i++) {
			HSSFRow row = sheet.createRow(8 + i);
			HSSFCell cellId = row.createCell(1);
			cellId.setCellStyle(styleCenter);
			cellId.setCellValue(saleChances.get(i).getId());
			HSSFCell cellName = row.createCell(2);
			cellName.setCellStyle(styleCenter);
			cellName.setCellValue(saleChances.get(i).getCustomerName());
			HSSFCell cellDescription = row.createCell(3);
			cellDescription.setCellStyle(styleCenter);
			cellDescription.setCellValue(saleChances.get(i).getDescription());
			HSSFCell cellLinkman = row.createCell(4);
			cellLinkman.setCellStyle(styleCenter);
			cellLinkman.setCellValue(saleChances.get(i).getLinkMan());
		}
		response.setContentType("applicaiton/x-excel");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			workbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private HSSFCellStyle createStyle(HSSFWorkbook workbook, int fontSize) {
		// 创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// center horizontal alignment
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		font.setFontHeightInPoints((short) fontSize);// 设置字体大小
		// 加载字体到样式
		style.setFont(font);
		return style;
	}

}

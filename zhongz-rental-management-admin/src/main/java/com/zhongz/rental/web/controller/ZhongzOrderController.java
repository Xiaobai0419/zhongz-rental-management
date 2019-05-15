package com.zhongz.rental.web.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

import com.zhongz.rental.domain.ZhongzContract;
import com.zhongz.rental.domain.ZhongzOrder;
import com.zhongz.rental.param.ZhongzUserOrderParam;
import com.zhongz.rental.result.base.Result;
import com.zhongz.rental.service.IZhongzOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-29
 */
@Controller
@RequestMapping("/system/zhongzOrder")
public class ZhongzOrderController extends BaseController
{
    private String prefix = "zhongz/rental/order";
	
	@Autowired
	private IZhongzOrderService zhongzOrderService;
	
	@RequiresPermissions("system:zhongzOrder:view")
	@GetMapping()
	public String zhongzOrder()
	{
	    return prefix + "/zhongzOrder";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("system:zhongzOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhongzOrder zhongzOrder)
	{
		startPage();
        List<ZhongzOrder> list = zhongzOrderService.selectZhongzOrderList(zhongzOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单列表
	 */
	@RequiresPermissions("system:zhongzOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhongzOrder zhongzOrder)
    {
    	List<ZhongzOrder> list = zhongzOrderService.selectZhongzOrderList(zhongzOrder);
        ExcelUtil<ZhongzOrder> util = new ExcelUtil<ZhongzOrder>(ZhongzOrder.class);
        return util.exportExcel(list, "zhongzOrder");
    }
	
	/**
	 * 新增订单
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}
	
	/**
	 * 新增保存订单
	 */
	@RequiresPermissions("system:zhongzOrder:add")
	@Log(title = "订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(ZhongzUserOrderParam zhongzUserOrderParam)
	{
		return zhongzOrderService.insertZhongzOrder(zhongzUserOrderParam);
	}

	/**
	 * 修改订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ZhongzContract zhongzContract = zhongzOrderService.selectZhongzOrderById(id);
		mmap.put("zhongzContract", zhongzContract);
		if(zhongzContract != null) {
			mmap.put("zhongzOrder", zhongzContract.getOrder());
		}
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单
	 */
	@RequiresPermissions("system:zhongzOrder:edit")
	@Log(title = "订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhongzContract zhongzContract)
	{		
		return toAjax(zhongzOrderService.updateZhongzOrder(zhongzContract));
	}
	
	/**
	 * 删除订单
	 */
	@RequiresPermissions("system:zhongzOrder:remove")
	@Log(title = "订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(zhongzOrderService.deleteZhongzOrderByIds(ids));
	}
	
}

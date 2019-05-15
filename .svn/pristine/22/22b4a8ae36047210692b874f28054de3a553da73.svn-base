package com.zhongz.rental.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.zhongz.rental.domain.ZhongzSuggestion;
import com.zhongz.rental.service.IZhongzSuggestionService;

/**
 * 意见反馈 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-28
 */
@Controller
@RequestMapping("/system/zhongzSuggestion")
public class ZhongzSuggestionController extends BaseController
{
    private String prefix = "zhongz/rental/suggestion";
	
	@Autowired
	private IZhongzSuggestionService zhongzSuggestionService;
	
	@RequiresPermissions("system:zhongzSuggestion:view")
	@GetMapping()
	public String zhongzSuggestion()
	{
	    return prefix + "/zhongzSuggestion";
	}
	
	/**
	 * 查询意见反馈列表
	 */
	@RequiresPermissions("system:zhongzSuggestion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhongzSuggestion zhongzSuggestion)
	{
		startPage();
        List<ZhongzSuggestion> list = zhongzSuggestionService.selectZhongzSuggestionJoinUserList(zhongzSuggestion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出意见反馈列表
	 */
	@RequiresPermissions("system:zhongzSuggestion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhongzSuggestion zhongzSuggestion)
    {
    	List<ZhongzSuggestion> list = zhongzSuggestionService.selectZhongzSuggestionList(zhongzSuggestion);
        ExcelUtil<ZhongzSuggestion> util = new ExcelUtil<ZhongzSuggestion>(ZhongzSuggestion.class);
        return util.exportExcel(list, "zhongzSuggestion");
    }
	
	/**
	 * 新增意见反馈
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存意见反馈
	 */
	@RequiresPermissions("system:zhongzSuggestion:add")
	@Log(title = "意见反馈", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ZhongzSuggestion zhongzSuggestion)
	{		
		return toAjax(zhongzSuggestionService.insertZhongzSuggestion(zhongzSuggestion));
	}

	/**
	 * 修改意见反馈
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ZhongzSuggestion zhongzSuggestion = zhongzSuggestionService.selectZhongzSuggestionById(id);
		mmap.put("zhongzSuggestion", zhongzSuggestion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存意见反馈
	 */
	@RequiresPermissions("system:zhongzSuggestion:edit")
	@Log(title = "意见反馈", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhongzSuggestion zhongzSuggestion)
	{		
		return toAjax(zhongzSuggestionService.updateZhongzSuggestion(zhongzSuggestion));
	}
	
	/**
	 * 删除意见反馈
	 */
	@RequiresPermissions("system:zhongzSuggestion:remove")
	@Log(title = "意见反馈", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(zhongzSuggestionService.deleteZhongzSuggestionByIds(ids));
	}
	
	/**  
	 * @Title: accept
	 * @Description: 采纳意见
	 * @Author: dell   
	 * @CreateTime: 2019年4月28日 下午12:20:45
	 * @param ids
	 * @return AjaxResult
	 * @Throws   
	 */
	@RequiresPermissions("system:zhongzSuggestion:accept")
	@Log(title = "意见反馈", businessType = BusinessType.UPDATE)
	@PostMapping( "/accept")
	@ResponseBody
	public AjaxResult accept(String ids)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("acceptStatus", 1);
		params.put("acceptorId", ShiroUtils.getUserId());
		params.put("acceptTime", new Date());
		return toAjax(zhongzSuggestionService.updateZhongzSuggestionByIds(params, ids));
	}
	
	/**  
	 * @Title: disaccept
	 * @Description: 不采纳意见
	 * @Author: dell   
	 * @CreateTime: 2019年4月28日 下午12:20:54
	 * @param ids
	 * @return AjaxResult
	 * @Throws   
	 */
	@RequiresPermissions("system:zhongzSuggestion:disaccept")
	@Log(title = "意见反馈", businessType = BusinessType.UPDATE)
	@PostMapping( "/disaccept")
	@ResponseBody
	public AjaxResult disaccept(String ids)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("acceptStatus", 2);
		params.put("acceptorId", ShiroUtils.getUserId());
		params.put("acceptTime", new Date());
		return toAjax(zhongzSuggestionService.updateZhongzSuggestionByIds(params, ids));
	}
	
}

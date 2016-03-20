package com.coaledu.org.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coaledu.org.iservice.IOrgTypeRuleService;
import com.coaledu.org.iservice.IOrgTypeService;
import com.coaledu.org.model.OrgType;
import com.coaledu.org.model.OrgTypeRule;

@Controller
@RequestMapping("/admin/orgType")
public class OrgTypeController {
	
	private IOrgTypeService orgTypeService;
	private IOrgTypeRuleService orgTypeRuleService;
	
	public IOrgTypeService getOrgTypeService() {
		return orgTypeService;
	}

	@Autowired
	public void setOrgTypeService(IOrgTypeService orgTypeService) {
		this.orgTypeService = orgTypeService;
	}

	public IOrgTypeRuleService getOrgTypeRuleService() {
		return orgTypeRuleService;
	}
	
	@Autowired
	public void setOrgTypeRuleService(IOrgTypeRuleService orgTypeRuleService) {
		this.orgTypeRuleService = orgTypeRuleService;
	}

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("orgTypes", orgTypeService.getAllOrgType());
		return "orgType/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("ot", new OrgType());
		return "orgType/add";
	}
	
	/**
	 * 进行验证时，br中默认的错误信息是orgType的，如果使用ot无法获取信息，
	 * 所以需要在@valid之后增加ModelAttribute来指定验证出错之后br中存储的model名称
	 * @param ot
	 * @param br
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("ot")OrgType ot,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "orgType/add";
		}
		orgTypeService.addOrgType(ot);
		return "redirect:/admin/orgType/list";
	}
	

	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute("ot", orgTypeService.getOrgTypeById(id));
		return "orgType/update";
	}
	

	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid @ModelAttribute("ot") OrgType ot,BindingResult br,Model model) {
		if(br.hasFieldErrors()) {
			model.addAttribute("ot",ot);
			return "orgType/update";
		}
		OrgType tot = orgTypeService.getOrgTypeById(id);
		tot.setName(ot.getName());
		tot.setSn(ot.getSn());
		orgTypeService.updateOrgType(tot);
		return "redirect:/admin/orgType/list";
	}
	

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,Model model) {
		orgTypeService.deleteOrgTypeById(id);
		return "redirect:/admin/orgType/list";
	}
	

	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute("ot",orgTypeService.getOrgTypeById(id));
		model.addAttribute("childs",orgTypeService.getChildOrgTypeByTypeId(id));
		return "orgType/show";
	}

	@RequestMapping("/setRule/{id}")
	public String updateRule(@PathVariable int id,Model model) {
		model.addAttribute("ot",orgTypeService.getOrgTypeById(id));
		model.addAttribute("rules",orgTypeService.getOrgTypeRuleTreeByOrgTypeId(id));
		return "orgType/setRule";
	}
	
	@RequestMapping("/deleteRule/{id}/{cid}")
	public String deleteRule(@PathVariable int id,@PathVariable int cid) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("pid", id);
		map.put("cid", cid);
		orgTypeRuleService.deleteOrgTypeRuleById(map);
		return "redirect:/admin/orgType/setRule/"+id;
	}
	
	@RequestMapping(value="/addRule",method=RequestMethod.POST)
	public void addRule(@RequestParam int cid,@RequestParam int pid,@RequestParam int num,HttpServletResponse reps) throws IOException {
		
		OrgTypeRule orgTypeRule = new OrgTypeRule();
		orgTypeRule.setCid(cid);
		orgTypeRule.setNum(num);
		orgTypeRule.setPid(pid);
		orgTypeRuleService.addOrgTypeRule(orgTypeRule);
		reps.getWriter().println("ok");
	}
	
	@RequestMapping(value="/updateRule",method=RequestMethod.POST)
	public void updateRule(@RequestParam int cid,@RequestParam int pid,@RequestParam int num,HttpServletResponse reps) throws IOException {
		OrgTypeRule orgTypeRule = new OrgTypeRule();
		orgTypeRule.setCid(cid);
		orgTypeRule.setNum(num);
		orgTypeRule.setPid(pid);
		orgTypeRuleService.updateOrgTypeRule(orgTypeRule);
		reps.getWriter().println("ok");
	}
}

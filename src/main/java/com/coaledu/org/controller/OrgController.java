package com.coaledu.org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coaledu.basic.dto.TreeDto;
import com.coaledu.org.iservice.IOrgService;
import com.coaledu.org.iservice.IOrgTypeService;
import com.coaledu.org.model.Org;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/org")
public class OrgController {
	private IOrgService orgService;
	private IOrgTypeService orgTypeService;
	
	public IOrgService getOrgService() {
		return orgService;
	}
	@Autowired
	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public IOrgTypeService getOrgTypeService() {
		return orgTypeService;
	}
	@Autowired
	public void setOrgTypeService(IOrgTypeService orgTypeService) {
		this.orgTypeService = orgTypeService;
	}
	@RequestMapping("/list")
	public String list() {
		return "org/list";
	}

	@RequestMapping("/treeAll")
	public @ResponseBody List<TreeDto> tree() {
		List<TreeDto> list = orgService.getOrgTree();
		return list;
	}
	
	@RequestMapping("/list/{id}")
	public String listChilds(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "1") int page,
			Integer tid,
            Model model) {
		Org org = orgService.getOrgById(id);
		model.addAttribute("parent",org);
		
		String search = "";
		if(tid!=null){search = "&tid="+tid;}
		model.addAttribute("search", search);
		
		model.addAttribute("tid",tid);
		model.addAttribute("orgTypes", orgTypeService.getChildOrgTypeByTypeId(org.getTid()));
		
		Integer rows = 5;
		PageHelper.startPage(page, rows);
		
		Map<String,Integer> map=new HashMap<String,Integer>();  
		map.put("pid",org.getTid());  
		map.put("tid",tid);
		List<Org> list = orgService.getOrgByPidAndTypeId(map);
		model.addAttribute("pageInfo", new PageInfo<Org>(list));
		
		PageInfo<Org> pagehelper = new PageInfo<Org>(list);
		model.addAttribute("pagehelper", pagehelper);
		
		model.addAttribute("url", "/admin/org/list/"+org.getId());
		
		return "org/listChilds";
	}
	
	private Map<Integer,String> initManagerType() {
		Map<Integer,String> types = new HashMap<Integer,String>();
		types.put(0, "默认类型");
		types.put(1, "所有类型");
		types.put(2, "自定义类型");
		types.put(-1,"不具备管理功能");
		return types;
	}

	@RequestMapping(value="/list/{id}/add",method=RequestMethod.GET)
	public String add(@PathVariable int id,Model model) {
		model.addAttribute("org", new Org());
		Org parent = orgService.getOrgById(id);
		model.addAttribute("parent", parent);
		model.addAttribute("orgTypes", orgTypeService.getChildOrgTypeByTypeId(parent.getTid()));
		model.addAttribute("managerTypes",initManagerType());
		return "org/add";
	}
	
	@RequestMapping(value="/list/{id}/add",method=RequestMethod.POST)
	public String add(@PathVariable int id,@Valid @ModelAttribute("org")Org org,BindingResult br,Integer pid,Model model) {
		if(br.hasErrors()) {
			return "org/add";
		}
		orgService.addOrg(org,pid);
		return "redirect:/admin/org/list/"+id;
	}

	@RequestMapping(value="/list/{pid}/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int pid,@PathVariable int id) {
		orgService.deleteOrg(id);
		return "redirect:/admin/org/list/"+pid;
	}
	
	@RequestMapping(value="/list/{pid}/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int pid,@PathVariable int id,Model model) {
		model.addAttribute("parent", orgService.getOrgById(pid));
		model.addAttribute("org", orgService.getOrgById(id));
		//model.addAttribute("persons", personService.listPersonAndPosByOrg(id, null));
		return "org/show";
	}
	
	@RequestMapping(value="/list/{pid}/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int pid,@PathVariable int id,Model model) {
		model.addAttribute("org", orgService.getOrgById(id));
		Org parent = orgService.getOrgById(pid);
		model.addAttribute("orgTypes", orgTypeService.getChildOrgTypeByTypeId(parent.getTid()));
		model.addAttribute("parent", parent);
		model.addAttribute("managerTypes",initManagerType());
		return "org/update";
	}
	
	@RequestMapping(value="/list/{pid}/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int pid,@PathVariable int id,@Valid @ModelAttribute("org")Org org,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "org/update";
		}
		Org to = orgService.getOrgById(id);
		to.setAddress(org.getAddress());
		to.setAtt1(org.getAtt1());
		to.setAtt2(org.getAtt2());
		to.setAtt3(org.getAtt3());
		to.setManagerType(org.getManagerType());
		to.setName(org.getName());
		to.setOrderNum(org.getOrderNum());
		to.setPhone(org.getPhone());
		to.setTid(org.getTid());
		to.setTname(org.getTname());
		orgService.updateOrg(to);
		return "redirect:/admin/org/list/"+pid;
	}
	/*	
	@RequestMapping(value="/setRule/{id}",method=RequestMethod.GET)
	public String setRule(@PathVariable int id,Model model) {
		model.addAttribute("org",orgService.load(id));
		model.addAttribute("mids", orgService.listManagerRuleIds(id));
		return "org/setRule";
	}
	
	@RequestMapping(value="/setRule",method=RequestMethod.POST)
	public @ResponseBody AjaxObj setRule(Integer id,@RequestParam("mids[]")Integer[] mids) {
		orgService.addRule(id, mids);
		return AjaxObj.success("成功设置规则");
	}
	
	@RequestMapping("/persons/{id}")
	public String showPerson(@PathVariable int id,Integer posId,Model model) {
		model.addAttribute("positions", positionService.listByOrg(id));
		if(posId!=null&&posId>0) {
			model.addAttribute("posId", posId);
		} else {
			posId = null;
		}
		model.addAttribute("persons", personService.findPersonAndPosByOrg(id, posId));
		Org org = orgService.load(id);
		model.addAttribute("org",org);
		model.addAttribute("parent", org);
		return "org/showPerson";
	}
	
	@RequestMapping("/personManagers/{id}")
	public String personManagers(@PathVariable int id,Model model) {
		model.addAttribute("person", personService.load(id));
		return "org/personManagers";
	}
	
	@RequestMapping("/personTree/{id}")
	public @ResponseBody List<TreeDto> personManagerTree(@PathVariable int id) {
		return personService.listOrgTree(id);
	}
	*/
}

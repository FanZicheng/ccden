package com.coaledu.org.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coaledu.org.iservice.IPositionService;
import com.coaledu.org.model.Position;

@Controller
@RequestMapping("/admin/position")
public class PositionController {

	private IPositionService positionService;
	
	
	public IPositionService getPositionService() {
		return positionService;
	}
	
	@Autowired
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("datas", positionService.getAllPosition());
		return "position/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("position", new Position());
		return "position/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("position")Position position,BindingResult br,Model model) {
		if(br.hasFieldErrors()) {
			return "position/add";
		}
		positionService.addPosition(position);
		return "redirect:/admin/position/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute("position", positionService.getPositionById(id));
		return "position/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid @ModelAttribute("position")Position position,BindingResult br,Model model) {
		if(br.hasFieldErrors()) {
			return "position/update";
		}
		Position tp = positionService.getPositionById(id);
		tp.setManager(position.getManager());
		tp.setName(position.getName());
		tp.setSn(position.getSn());
		positionService.updatePosition(tp);
		return "redirect:/admin/position/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		positionService.deletePositionById(id);
		return "redirect:/admin/position/list";
	}
}

package com.cf.login.website.other;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cf.api.system.SystemType;

@Controller
@RequestMapping("/html")
public class HtmlController {
	@SystemType
	@GetMapping("/{fileNmae}")
	public ModelAndView jump(@PathVariable("fileNmae") String fileName){
		ModelAndView mv = new ModelAndView("html/"+fileName);
		return mv;
	}
}

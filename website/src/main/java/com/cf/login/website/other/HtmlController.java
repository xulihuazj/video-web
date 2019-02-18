package com.cf.login.website.other;

import com.cf.api.system.SystemType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

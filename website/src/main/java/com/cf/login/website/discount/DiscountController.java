package com.cf.login.website.discount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/discount")
public class DiscountController {

    @GetMapping("/page")
    public ModelAndView complaintsListPage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("discount/discount");
    }
}

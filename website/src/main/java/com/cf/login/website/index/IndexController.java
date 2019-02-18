package com.cf.login.website.index;

import com.cf.api.request.APIRequest;
import com.cf.api.request.app.common.AppHouseDetailRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.house.AppHouseDetailResponse;
import com.cf.api.response.app.house.AppHouseListResponse;
import com.cf.api.system.SystemType;
import com.cf.login.apiinterface.common.CommonApiInterface;
import com.cf.login.website.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Resource
    CommonApiInterface commonApiInterface;

    @GetMapping(value = "/")
    @SystemType
    public ModelAndView index(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("index");
    }

    @SystemType
    @GetMapping(value = "/house")
    public ModelAndView house(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        APIRequest request = BaseController.getRequest(httpServletRequest, null);
        APIResponse<AppHouseListResponse> apiResponse = commonApiInterface.house(request);

        return new ModelAndView("index_house").addObject("response", apiResponse);
    }

    @SystemType
    @GetMapping(value = "/house/{id}")
    public ModelAndView houseDetail(@PathVariable("id") Long id,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        AppHouseDetailRequest appHouseDetailRequest = new AppHouseDetailRequest();
        appHouseDetailRequest.setId(id);
        APIRequest<AppHouseDetailRequest> request = BaseController.getRequest(httpServletRequest, appHouseDetailRequest);

        APIResponse<AppHouseDetailResponse> apiResponse = commonApiInterface.houseDetail(request);

        if (apiResponse.getBizResponse() == null) {
            return new ModelAndView("redirect:/404");
        }

        return new ModelAndView("house/detail").addObject("response", apiResponse);
    }

    @SystemType
    @GetMapping("/app.html")
    public ModelAndView app(){
        ModelAndView mv = new ModelAndView("app");
        return mv;
    }

    @GetMapping("/404")
    @SystemType
    public ModelAndView error404(){
    	ModelAndView mv = new ModelAndView("err_404");
    	return mv;
    }
    

}

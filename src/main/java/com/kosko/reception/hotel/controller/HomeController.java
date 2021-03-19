package com.kosko.reception.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author kaust
 *
 */
@ApiIgnore
@Controller
public class HomeController {

	/**
	 * @return
	 */
	@RequestMapping("/hotel/hospitality/")
	public String home() {
		return "redirect:swagger-ui.html";
	}

}

package com.aaron.springweb.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaron.springweb.core.service.IPingService;

/**
 * 	http://localhost:8080/ping/tester1
 *	http://localhost:8080/ping/tester2
 *	http://localhost:8080/ping/tester3
 *	http://localhost:8080/pings
 * 	@author aaron.qiu
 *  @since 2016-05-03
 */
@Controller
public class PingController {

	@Autowired
	private IPingService pingService;

	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/ping/{tag}", produces = "text/plain")
	@ResponseBody
	public String pingTag(@PathVariable("tag") String tag) {
		pingService.insert(tag);
		return "Ping tag '" + tag + "' has been inserted. ";
	}

	@RequestMapping(value = "/pings", produces = "text/plain")
	@ResponseBody
	public String pings() {
		List<Map<String, Object>> result = pingService.findAllPings();
		if (result.size() == 0)
			return "No record found.";

		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> row : result) {
			sb.append("Ping" + row).append("\n");
		}
		return sb.toString();
	}
}

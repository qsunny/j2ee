package com.aaron.springweb.web.controller;

import com.aaron.springweb.bean.Page;
import com.aaron.springweb.bean.Ping;
import com.aaron.springweb.core.service.IPingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
		List<Ping> result = pingService.findAllPings();
		if (result.size() == 0)
			return "No record found.";

		StringBuilder sb = new StringBuilder();
		for (Ping row : result) {
			sb.append("Ping" + row).append("\n");
		}
		return sb.toString();
	}

	@RequestMapping(value = "/pingPage", produces = "text/plain")
	@ResponseBody
	public String pingPage() {

		Page<Ping> page = new Page<Ping>();
		page.setCurrNum(1);
		page.setPreSize(2);
		Page<Ping> p = pingService.getAllPingByPage(page);
		System.out.println("=========================>"+p);
		List<Ping> list = p.getData();
		StringBuilder sb = new StringBuilder();
		for (Ping ping : list) {
			sb.append("Ping" + ping.toString()).append("\n");
		}
		return sb.toString();
	}
}

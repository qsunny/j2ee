package com.aaron.springweb.web.controller;

import com.aaron.springweb.bean.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aaron.qiu on 2016/9/26.
 */
@Controller
public class AppController {

    @RequestMapping(value="/pizzavalley/{pizzaName}", method = RequestMethod.GET)
    public String getPizza(@PathVariable String pizzaName, ModelMap model) {

        Pizza pizza = new Pizza(pizzaName);
        model.addAttribute("pizza", pizza);

        return "pizza";

    }
}

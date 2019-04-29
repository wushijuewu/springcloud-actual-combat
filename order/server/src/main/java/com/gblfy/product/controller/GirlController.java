package com.gblfy.product.controller;

import com.gblfy.product.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gblfy
 * @ClassNme GirlController
 * @Description TODO
 * @Date 2019/4/29 0:50
 * @version1.0
 */
@RestController
@RequestMapping("/girl")
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/print")
    public String print() {
        return "name:" + girlConfig.getName() + "," + girlConfig.getAge();
    }
}

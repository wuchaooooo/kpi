package com.wuchaooooo.kpi.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wuchaooooo on 17/04/2017.
 */
@Controller
public class PPTController {
    @RequestMapping(value = "/ppt")
    public String getPPT() {
        return "ppt/middle-reply";
    }
}

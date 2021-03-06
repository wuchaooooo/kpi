package com.wuchaooooo.kpi.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by wuchaooooo on 26/11/2016.
 */

@Controller
@RequestMapping(value = "/interviewer")
public class InterviewerController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("role", "interviewer");
        return "common/index";
    }
}

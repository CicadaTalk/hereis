package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Course;
import edu.scu.hereis.service.CourseService;
import edu.scu.hereis.wxresult.CourseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/6.
 */

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @ResponseBody
    @RequestMapping("/getCourseByClassroom")
    public List<CourseResult> getCourseByClassroom(Integer sbId) {
        if (sbId == null) return null;
        List<Course> courseList = courseService.getCoursesBysbId(sbId);
        List<CourseResult> courseResultList = CourseResult.toList(courseList);
        return courseResultList;
    }
}

package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vicent_Chen on 2018/5/7.
 */
public class CourseResult implements Comparable<CourseResult> {
    private String Id;
    private String no;
    private String name;
    private Integer beginTime;
    private Integer endTime;

    /**
     * 根据课程列表生成符合微信前端数据格式的courseResult列表，并根据课程起始时间进行排序
     * @param courseList
     * @return
     */
    public static List<CourseResult> toList(List<Course> courseList) {
        List<CourseResult> courseResultList = new ArrayList<>();
        for (Course c : courseList)
            courseResultList.add(new CourseResult(c));
        Collections.sort(courseResultList);
        return courseResultList;
    }

    public CourseResult(Course course) {
        this.Id = course.getCourseId();
        this.no = course.getCourseNo();
        this.name = course.getCourseName();
        this.beginTime = course.getBeginTime();
        this.endTime = course.getEndTime();
    }

    @Override
    public int compareTo(CourseResult o) {
        return this.beginTime - o.beginTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}

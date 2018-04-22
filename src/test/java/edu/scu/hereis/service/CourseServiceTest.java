package edu.scu.hereis.service;

import edu.scu.hereis.entity.Course;
import edu.scu.hereis.entity.CourseKey;
import edu.scu.hereis.exception.CourseException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static edu.scu.hereis.exception.CourseException.*;

/**
 * Created by Vicent_Chen on 2018/4/22.
 * 测试方法：
 * 1. 首先获取当前数据库中函数CURRENT_ROW_NUM
 * 2. 运行测试
 * 所有测试均已通过 2018/4/22
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CourseServiceTest {
    // 当前course表中存在的行数
    public final static int CURRENT_ROW_NUM = 0;

    @Autowired
    CourseService courseService;

    Course nullCourse;
    Course emptyIDCourse, emptyNoCourse;
    Course emptyNameCourse;
    Course emptyBeginTimeCourse, emptyEndTimeCourse;
    Course emptySbidCourse;

    Course toInsert1, toInsert2, toUpdate;
    List<Course> toInsertList;

    @Before
    public void init() {

        nullCourse = null;

        emptyIDCourse = new Course();
        emptyIDCourse.setCourseId(null); emptyIDCourse.setCourseNo("1");
        emptyIDCourse.setCourseName("123123");
        emptyIDCourse.setBeginTime(1); emptyIDCourse.setEndTime(2);
        emptyIDCourse.setSbId(1);

        emptyNoCourse = new Course();
        emptyNoCourse.setCourseId("emptyNoCourse"); emptyNoCourse.setCourseNo(null);
        emptyNoCourse.setCourseName("123123");
        emptyNoCourse.setBeginTime(1); emptyNoCourse.setEndTime(2);
        emptyNoCourse.setSbId(1);

        emptyNameCourse = new Course();
        emptyNameCourse.setCourseId("emptyNameCourse"); emptyNameCourse.setCourseNo("1");
        emptyNameCourse.setCourseName(null);
        emptyNameCourse.setBeginTime(1); emptyNameCourse.setEndTime(2);
        emptyNameCourse.setSbId(1);

        emptyBeginTimeCourse = new Course();
        emptyBeginTimeCourse.setCourseId("emptyBeginTimeCourse"); emptyBeginTimeCourse.setCourseNo("1");
        emptyBeginTimeCourse.setCourseName("123123");
        emptyBeginTimeCourse.setBeginTime(null); emptyBeginTimeCourse.setEndTime(2);
        emptyBeginTimeCourse.setSbId(1);

        emptyEndTimeCourse = new Course();
        emptyEndTimeCourse.setCourseId("emptyEndTimeCourse"); emptyEndTimeCourse.setCourseNo("1");
        emptyEndTimeCourse.setCourseName("123123");
        emptyEndTimeCourse.setBeginTime(1); emptyEndTimeCourse.setEndTime(null);
        emptyEndTimeCourse.setSbId(1);

        emptySbidCourse = new Course();
        emptySbidCourse.setCourseId("emptySbidCourse"); emptySbidCourse.setCourseNo("1");
        emptySbidCourse.setCourseName("123123");
        emptySbidCourse.setBeginTime(1); emptySbidCourse.setEndTime(2);
        emptySbidCourse.setSbId(null);

        // right
        toInsert1 = new Course();
        toInsert1.setCourseId("toInsert"); toInsert1.setCourseNo("1");
        toInsert1.setCourseName("123123");
        toInsert1.setBeginTime(1); toInsert1.setEndTime(2);
        toInsert1.setSbId(1);

        toInsert2 = new Course();
        toInsert2.setCourseId("toInsert"); toInsert2.setCourseNo("2");
        toInsert2.setCourseName("123123");
        toInsert2.setBeginTime(1); toInsert2.setEndTime(2);
        toInsert2.setSbId(1);

        toUpdate = new Course();
        toUpdate.setCourseId("toInsert"); toUpdate.setCourseNo("1");
        toUpdate.setCourseName("123123");
        toUpdate.setBeginTime(4); toUpdate.setEndTime(5);
        toUpdate.setSbId(1);

        toInsertList = new ArrayList<>();
        toInsertList.add(toInsert1); toInsertList.add(toInsert2);
    }

    @Test
    public void test001getAllCourses() {
        assertEquals(CURRENT_ROW_NUM, courseService.getAllCourses().size());
    }

    @Test
    public void test002insertCourse() {
        try {
            courseService.insertCourse(nullCourse);
        }
        catch (CourseException e) {
            assertEquals(COURSE_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptyIDCourse);
        }
        catch (CourseException e) {
            assertEquals(ID_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptyNoCourse);
        }
        catch (CourseException e) {
            assertEquals(NO_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptyNameCourse);
        }
        catch (CourseException e) {
            assertEquals(NAME_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptyBeginTimeCourse);
        }
        catch (CourseException e) {
            assertEquals(TIME_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptyEndTimeCourse);
        }
        catch (CourseException e) {
            assertEquals(TIME_EMPTY_ERROR_CODE, e.getCode());
        }

        try {
            courseService.insertCourse(emptySbidCourse);
        }
        catch (CourseException e) {
            assertEquals(SB_ID_EMPTY_ERROR_CODE, e.getCode());
        }
    }

    @Test
    public void test003insertCourseList() {
        courseService.insertCourseList(toInsertList);
        assertEquals(CURRENT_ROW_NUM + 2, courseService.getAllCourses().size());
        toInsert1.setBeginTime(5);
        try {
            courseService.insertCourse(toInsert1);
        }
        catch (CourseException e) {
            assertEquals(UNKNOWN_ERROR_CODE, e.getCode());
        }
    }

    @Test
    public void test004getCourseByKey() {
        CourseKey courseKey = new CourseKey(toInsert2.getCourseId(), toInsert2.getCourseNo());
        Course course = courseService.getCourseByKey(null);
        assertEquals(null, course);
        course = courseService.getCourseByKey(new CourseKey("no", "no"));
        assertEquals(null, course);
        course = courseService.getCourseByKey(courseKey);
        assertEquals(toInsert2.getCourseNo(), course.getCourseNo());
    }

    @Test
    public void test005getCoursesBysbId() {
        List<Course> courseList = courseService.getCoursesBysbId(-1);
        assertEquals(0, courseList.size());
        courseList = courseService.getCoursesBysbId(toInsert1.getSbId());
        assertEquals(2, courseList.size());

    }

    @Test
    public void test006getCoursesById() {
        List<Course> courseList;
        try {
            courseList = courseService.getCoursesById(null);
        }
        catch (CourseException e) {
            assertEquals(ID_EMPTY_ERROR_CODE, e.getCode());
        }
        courseList = courseService.getCoursesById("NONO");
        assertEquals(0, courseList.size());
        courseList = courseService.getCoursesById(toInsert1.getCourseId());
        assertEquals(2, courseList.size());
    }

    @Test
    public void test007updateCourse() {
        try {
            courseService.updateCourse(nullCourse);
        }
        catch (CourseException e) {
            assertEquals(COURSE_EMPTY_ERROR_CODE, e.getCode());
        }

        Course course = new Course(); course.setCourseId(null); course.setCourseNo(null);
        try {
            courseService.updateCourse(course);
        }
        catch (CourseException e) {
            assertEquals(ID_EMPTY_ERROR_CODE, e.getCode());
        }

        courseService.updateCourse(emptySbidCourse);
        courseService.updateCourse(toUpdate);
        CourseKey courseKey = new CourseKey(toUpdate.getCourseId(), toUpdate.getCourseNo());
        course = courseService.getCourseByKey(courseKey);
        assertEquals(toUpdate.getBeginTime(), course.getBeginTime());
    }

    @Test
    public void test008deleteCourseByKey() {
        CourseKey courseKey = new CourseKey(toUpdate.getCourseId(), toUpdate.getCourseNo());
        courseService.deleteCourseByKey(courseKey);
        assertEquals(CURRENT_ROW_NUM + 1, courseService.getAllCourses().size());
    }

    @Test
    public void test009deleteAllCourses() {
        courseService.deleteAllCourses();
        assertEquals(0, courseService.getAllCourses().size());
    }
}
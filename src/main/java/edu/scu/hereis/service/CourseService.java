package edu.scu.hereis.service;

import edu.scu.hereis.dao.CourseMapper;
import edu.scu.hereis.entity.Course;
import edu.scu.hereis.entity.CourseExample;
import edu.scu.hereis.entity.CourseKey;
import edu.scu.hereis.exception.CourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.scu.hereis.exception.CourseException.*;

/**
 * Created by Vicent_Chen on 2018/4/21.
 */
@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    /**
     * 插入课程，若数据库中主键已存在，抛出异常
     * @param course 课程属性均不得为空
     */
    @Transactional
    public void insertCourse(Course course) {
        if (course == null) {
            throw new CourseException(COURSE_EMPTY_ERROR_CODE, COURSE_EMPTY_ERROR);
        }

        try {
            courseMapper.insertSelective(course);
        }
        catch (DataIntegrityViolationException e) {
            if (course.getCourseId() == null) {
                throw new CourseException(ID_EMPTY_ERROR_CODE, ID_EMPTY_ERROR);
            }
            else if (course.getCourseNo() == null) {
                throw new CourseException(NO_EMPTY_ERROR_CODE, NO_EMPTY_ERROR);
            }
            else if (course.getCourseName() == null) {
                throw new CourseException(NAME_EMPTY_ERROR_CODE, NO_EMPTY_ERROR);
            }
            else if (course.getBeginTime() == null || course.getEndTime() == null) {
                throw new CourseException(TIME_EMPTY_ERROR_CODE, TIME_EMPTY_ERROR);
            }
            else if (course.getSbId() == null) {
                throw new CourseException(SB_ID_EMPTY_ERROR_CODE, SB_ID_EMPTY);
            }
            else {
                throw new CourseException(UNKNOWN_ERROR_CODE, e.getMessage());
            }
        }
    }

    /**
     * 插入课程列表
     * @param courseList
     */
    @Transactional
    public void insertCourseList(List<Course> courseList) {
        for (Course course : courseList) {
            insertCourse(course);
        }
    }

    /**
     * 根据课程ID、No删除课程
     * @param courseKey
     */
    @Transactional
    public void deleteCourseByKey(CourseKey courseKey) {
        try {
            courseMapper.deleteByPrimaryKey(courseKey);
        } catch (CourseException e) {
            throw new CourseException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 删除所有课程
     */
    @Transactional
    public void deleteAllCourses() {
        try {
            CourseExample courseExample = new CourseExample();
            courseMapper.deleteByExample(courseExample);
        }
        catch (CourseException e) {
            throw new CourseException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 更新课程
     * @param course 课程ID，No均不得为空
     */
    @Transactional
    public void updateCourse(Course course) {
        if (course == null) {
            throw new CourseException(COURSE_EMPTY_ERROR_CODE, COURSE_EMPTY_ERROR);
        }
        if (course.getCourseId() == null) {
            throw new CourseException(ID_EMPTY_ERROR_CODE, ID_EMPTY_ERROR);
        }
        if (course.getCourseNo() == null) {
            throw new CourseException(NO_EMPTY_ERROR_CODE, NO_EMPTY_ERROR);
        }

        try {
            courseMapper.updateByPrimaryKeySelective(course);
        }
        catch (CourseException e) {
            throw new CourseException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据课程ID、No获取课程
     * @param courseKey ID, No均不得为空
     * @return 返回值可能为null
     */
    public Course getCourseByKey(CourseKey courseKey) {
        return courseMapper.selectByPrimaryKey(courseKey);
    }

    /**
     * 根据教室ID获取课程
     * @param sbId 教室ID
     * @return 可能返回空列表
     */
    public List<Course> getCoursesBysbId(int sbId) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andSbIdEqualTo(sbId);
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return courseList;
    }

    /**
     * 根据课程ID获取课程
     * @param courseId 课程ID
     * @return 可能返回空列表
     */
    public List<Course> getCoursesById(String courseId) {
        if (courseId == null) {
            throw new CourseException(ID_EMPTY_ERROR_CODE, ID_EMPTY_ERROR);
        }
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andCourseIdEqualTo(courseId);
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return courseList;
    }

    /**
     * 获取所有课程
     * @return 可能返回空列表
     */
    public List<Course> getAllCourses() {
        CourseExample courseExample = new CourseExample();
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return courseList;
    }
}

package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Course;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vicent_Chen on 2018/5/7.
 * 所有测试均已通过 18/5/7
 */
public class CourseResultTest {

    @Test
    public void toList() {
        Course c1 = new Course(); c1.setBeginTime(1);
        Course c2 = new Course(); c2.setBeginTime(2);
        Course c3 = new Course(); c3.setBeginTime(3);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(c2); courses.add(c1); courses.add(c3);
        List<CourseResult> courseResults = CourseResult.toList(courses);
        assertEquals(c1.getBeginTime(), courseResults.get(0).getBeginTime());
        assertEquals(c2.getBeginTime(), courseResults.get(1).getBeginTime());
        assertEquals(c3.getBeginTime(), courseResults.get(2).getBeginTime());
    }
}
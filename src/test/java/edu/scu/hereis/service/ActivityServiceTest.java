package edu.scu.hereis.service;

import edu.scu.hereis.entity.Activity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 测试通过
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Test
    public void insertActivity() throws Exception {
        Activity activity = new Activity();
        activity.setId(1);
        activity.setSpotId(1);
        activity.setIntro("something");
        activity.setBeginTime(new Date());
        activity.setEndTime(new Date());
        activity.setName("some");

        activityService.insertActivity(activity);
    }

    @Test
    public void deleteActivity() throws Exception {

        activityService.deleteActivity(1);
    }

    @Test
    public void getActivityById() throws Exception {

        activityService.getActivityById(1);
    }

    @Test
    public void getActivitiesBySpotId() throws Exception {

        activityService.getActivitiesBySpotId(1);
    }

    @Test
    public void updateActivity() throws Exception {

        Activity activity = new Activity();
        activity.setId(1);
        activity.setSpotId(1);
        activity.setIntro("something update");
        activity.setBeginTime(new Date());
        activity.setEndTime(new Date());
        activity.setName("some");

        activityService.updateActivity(activity);
    }

}
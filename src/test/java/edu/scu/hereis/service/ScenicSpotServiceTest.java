package edu.scu.hereis.service;

import edu.scu.hereis.entity.ScenicSpot;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试通过
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScenicSpotServiceTest {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @Test
    public void insertScenicSpot() throws Exception {

        ScenicSpot scenicSpot = new ScenicSpot();
        scenicSpot.setSpotId(1);
        scenicSpot.setIntro("something");
        scenicSpot.setWarning("no waring");

        scenicSpotService.insertScenicSpot(scenicSpot);
    }

    @Test
    public void deleteScenicSpot() throws Exception {

        scenicSpotService.deleteScenicSpot(1);
    }

    @Test
    public void getScenicSpotById() throws Exception {

        scenicSpotService.getScenicSpotById(1);
    }

    @Test
    public void updateScenicSpot() throws Exception {

        ScenicSpot scenicSpot = new ScenicSpot();
        scenicSpot.setSpotId(1);
        scenicSpot.setIntro("something update");
        scenicSpot.setWarning("no waring");

        scenicSpotService.updateScenicSpot(scenicSpot);
    }

}
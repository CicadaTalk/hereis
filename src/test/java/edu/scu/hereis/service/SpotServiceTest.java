package edu.scu.hereis.service;

import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.exception.SpotException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static edu.scu.hereis.exception.SpotException.*;
import static org.junit.Assert.*;

/**
 * Created by Vicent_Chen on 2018/4/21.
 * 测试方法：
 * 1. 首先获取当前数据库中函数CURRENT_ROW_NUM
 * 2. 然后获取当前数据库中自增索引CURRENT_MAX_ID
 * 3. 运行测试
 * 4. 测试以后使用 alter table spot AUTO_INCREMENT=CURRENT_MAX_ID + 1 重置数据库自增索引
 * 所有测试均已通过 2018/4/21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpotServiceTest {

    // 当前Spot表中最大的ID
    public final static int CURRENT_MAX_ID = 1;
    // 当前Spot表中存在的行数
    public final static int CURRENT_ROW_NUM = 1;

    @Autowired
    SpotService spotService;

    Spot nullSpot;

    Spot emptyGpsLngSpot, emptyGpsLatSpot;
    Spot emptyNameSpot;
    Spot emptyBriefIntroSpot;
    Spot emptyBgImgSpot;
    Spot emptyCategorySpot;

    Spot emptyIDSpot;
    Spot additionIDSpot;

    Spot spotToUpdate;
    Spot GPSSpot1, GPSSpot2;

    @Before
    public void init() {
        // wrong
        nullSpot = null;

        emptyGpsLngSpot = new Spot();
        emptyGpsLngSpot.setGpsLng(null); emptyGpsLngSpot.setGpsLat(.0);
        emptyGpsLngSpot.setName("EmptyGpsLngSpot"); emptyGpsLngSpot.setBriefIntro("EmptyGpsLngSpot");
        emptyGpsLngSpot.setBgImg("EmptyGpsLngSpot"); emptyGpsLngSpot.setCategory("TEST");

        emptyGpsLatSpot = new Spot();
        emptyGpsLatSpot.setGpsLng(.0); emptyGpsLatSpot.setGpsLat(null);
        emptyGpsLatSpot.setName("EmptyGpsLatSpot"); emptyGpsLatSpot.setBriefIntro("EmptyGpsLatSpot");
        emptyGpsLatSpot.setBgImg("EmptyGpsLatSpot"); emptyGpsLatSpot.setCategory("TEST");

        emptyNameSpot = new Spot();
        emptyNameSpot.setGpsLng(.0); emptyNameSpot.setGpsLat(.0);
        emptyNameSpot.setName(null); emptyNameSpot.setBriefIntro("EmptyNameSpot");
        emptyNameSpot.setBgImg("EmptyNameSpot"); emptyNameSpot.setCategory("TEST");

        emptyBriefIntroSpot = new Spot();
        emptyBriefIntroSpot.setGpsLng(.0); emptyBriefIntroSpot.setGpsLat(.0);
        emptyBriefIntroSpot.setName("EmptyBriefIntroSpot"); emptyBriefIntroSpot.setBriefIntro(null);
        emptyBriefIntroSpot.setBgImg("EmptyBriefIntroSpot"); emptyBriefIntroSpot.setCategory("TEST");

        emptyBgImgSpot = new Spot();
        emptyBgImgSpot.setGpsLng(.0); emptyBgImgSpot.setGpsLat(.0);
        emptyBgImgSpot.setName("EmptyBgImgSpot"); emptyBgImgSpot.setBriefIntro("EmptyBgImgSpot");
        emptyBgImgSpot.setBgImg(null); emptyBgImgSpot.setCategory("TEST");

        emptyCategorySpot = new Spot();
        emptyCategorySpot.setGpsLng(.0); emptyCategorySpot.setGpsLat(.0);
        emptyCategorySpot.setName("EmptyCategroySpot"); emptyCategorySpot.setBriefIntro("EmptyCategroySpot");
        emptyCategorySpot.setBgImg("EmptyCategroySpot"); emptyCategorySpot.setCategory(null);

        // right
        emptyIDSpot = new Spot();
        emptyIDSpot.setGpsLng(.0); emptyIDSpot.setGpsLat(.0);
        emptyIDSpot.setName("EmptyIdSpot"); emptyIDSpot.setBriefIntro("EmptyIdSpot");
        emptyIDSpot.setBgImg("EmptyIdSpot"); emptyIDSpot.setCategory("TEST");

        additionIDSpot = new Spot(); additionIDSpot.setId(100);
        additionIDSpot.setGpsLng(.0); additionIDSpot.setGpsLat(.0);
        additionIDSpot.setName("AdditionIDSpot"); additionIDSpot.setBriefIntro("AdditionIDSpot");
        additionIDSpot.setBgImg("AdditionIDSpot"); additionIDSpot.setCategory("TEST");

        spotToUpdate = new Spot(); spotToUpdate.setId(CURRENT_MAX_ID + 1);
        spotToUpdate.setName("spotToUpdate");

        GPSSpot1 = new Spot();
        GPSSpot1.setGpsLng(100.45); GPSSpot1.setGpsLat(100.45);
        GPSSpot1.setName("GPSSpot1"); GPSSpot1.setBriefIntro("GSPSpot1");
        GPSSpot1.setBgImg("GPSSpot1"); GPSSpot1.setCategory("GPSSpot1");

        GPSSpot2 = new Spot();
        GPSSpot2.setGpsLng(90.45); GPSSpot2.setGpsLat(90.45);
        GPSSpot2.setName("GPSSpot2"); GPSSpot2.setBriefIntro("GSPSpot2");
        GPSSpot2.setBgImg("GPSSpot2"); GPSSpot2.setCategory("GPSSpot2");
    }

    @Test
    public void test001getAllSpots() {
        assertEquals(spotService.getAllSpots().size(), CURRENT_ROW_NUM);
    }

    @Test
    public void test002insertSpot() {
        try {
            spotService.insertSpot(nullSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), SPOT_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyGpsLngSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), GPS_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyGpsLatSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), GPS_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyNameSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), NAME_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyBriefIntroSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), BRIEF_INTRO_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyBgImgSpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), BG_IMG_EMPTY_ERROR_CODE);
        }

        try {
            spotService.insertSpot(emptyCategorySpot);
        }
        catch (SpotException e) {
            assertEquals(e.getCode(), CATEGORY_EMPTY_ERROR_CODE);
        }

        spotService.insertSpot(emptyIDSpot);
        spotService.insertSpot(additionIDSpot);
        assertEquals(CURRENT_ROW_NUM + 2, spotService.getAllSpots().size());
    }

    @Test
    public void test003getSpotByID() {
        assertEquals(emptyIDSpot.getName(), spotService.getSpotByID(CURRENT_MAX_ID + 1).getName());
    }

    @Test
    public void test004updateSpot() {
        spotService.updateSpot(spotToUpdate);
        assertEquals(spotToUpdate.getName(), spotService.getSpotByID(CURRENT_MAX_ID + 1).getName());
    }

    @Test
    public void test005deleteSpot() {
        spotService.deleteSpot(null);
        assertEquals(CURRENT_ROW_NUM + 2, spotService.getAllSpots().size());
        spotService.deleteSpot(CURRENT_MAX_ID + 1);
        assertEquals(CURRENT_ROW_NUM + 1, spotService.getAllSpots().size());
        spotService.deleteSpot(CURRENT_MAX_ID + 2);
        assertEquals(CURRENT_ROW_NUM, spotService.getAllSpots().size());
    }

    @Test
    public void test006getSpotByGPS() {
        spotService.insertSpot(GPSSpot1); spotService.insertSpot(GPSSpot2);
        List<Spot> spotList = spotService.getSpotsByGPS(100.0, 101.0, 100.0, 101.0);
        assertEquals(1, spotList.size());
        spotList = spotService.getSpotsByGPS(90.0, 101.0, 90.0, 101.0);
        assertEquals(2, spotList.size());
        spotService.deleteSpot(CURRENT_MAX_ID + 3);
        spotService.deleteSpot(CURRENT_MAX_ID + 4);
    }
}
package edu.scu.hereis.service;

import edu.scu.hereis.dao.SpotMapper;
import edu.scu.hereis.entity.Spot;
import edu.scu.hereis.entity.SpotExample;
import edu.scu.hereis.exception.SpotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.scu.hereis.exception.SpotException.*;

/**
 * Created by Vicent_Chen on 2018/4/21.
 */
@Service
public class SpotService {

    @Autowired
    SpotMapper spotMapper;

    /**
     * 插入地点，地点除ID外所有属性均不能为空，若为空则抛出对应的异常
     * 若地点ID不为空，地点ID会首先被设为null在插入至数据库中
     * @param spot
     */
    @Transactional
    public void insertSpot(Spot spot) {
        if (spot == null) {
            throw new SpotException(SPOT_EMPTY_ERROR_CODE, SPOT_EMPTY_ERROR);
        }

        try {
            spot.setId(null);
            spotMapper.insertSelective(spot);
        }
        catch (DataIntegrityViolationException e) {
            if (spot.getGpsLng() == null || spot.getGpsLat() == null) {
                throw new SpotException(GPS_EMPTY_ERROR_CODE, GPS_EMPTY_ERROR);
            }
            else if (spot.getName() == null) {
                throw new SpotException(NAME_EMPTY_ERROR_CODE, NAME_EMPTY_ERROR);
            }
            else if (spot.getBriefIntro() == null) {
                throw new SpotException(BRIEF_INTRO_EMPTY_ERROR_CODE, BRIEF_INTRO_EMPTY_ERROR);
            }
            else if (spot.getBgImg() == null) {
                throw new SpotException(BG_IMG_EMPTY_ERROR_CODE, BG_IMG_EMPTY_ERROR);
            }
            else if (spot.getCategory() == null) {
                throw new SpotException(CATEGORY_EMPTY_ERROR_CODE, CATEGORY_EMPTY_ERROR);
            }
            else {
                throw new SpotException(UNKNOWN_ERROR_CODE, e.getMessage());
            }
        }

    }

    /**
     * 根据ID删除地点
     * @param id
     */
    @Transactional
    public void deleteSpot(Integer id) {
        try {
            spotMapper.deleteByPrimaryKey(id);
        }
        catch (RuntimeException e) {
            throw new SpotException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据地点更新地点，地点ID不能为空
     * 此方法会自动忽略不能找到的地点
     * @param spot
     */
    @Transactional
    public void updateSpot(Spot spot) {
        if (spot == null || spot.getId() == null) {
            throw new SpotException(SpotException.SPOT_EMPTY_ERROR);
        }

        try {
            spotMapper.updateByPrimaryKeySelective(spot);
        }
        catch (RuntimeException e) {
            throw new SpotException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据ID查找Spot，若查找失败返回null
     * @param id
     * @return
     */
    public Spot getSpotByID(Integer id) {
        Spot spot = null;
        try {
             spot = spotMapper.selectByPrimaryKey(id);
        }
        catch (RuntimeException e) {
            throw new SpotException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
        return spot;
    }

    /**
     * 根据GPS范围查找地点，可能返回空列表
     * @param beginLng
     * @param endLng
     * @param beginLat
     * @param endLat
     * @return
     */
    public List<Spot> getSpotsByGPS(Double beginLng, Double endLng, Double beginLat, Double endLat) {
        SpotExample spotExample = new SpotExample();
        spotExample.createCriteria().andGpsLngBetween(beginLng, endLng).andGpsLatBetween(beginLat, endLat);
        List<Spot> spotList = spotMapper.selectByExample(spotExample);
        return spotList;
    }

    /**
     * 返回所有地点
     * 若地点不存在则为空的列表
     * @return
     */
    public List<Spot> getAllSpots() {
        SpotExample spotExample = new SpotExample();
        List<Spot> spotList = spotMapper.selectByExample(spotExample);
        return spotList;
    }
}

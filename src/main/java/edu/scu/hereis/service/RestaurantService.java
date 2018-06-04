package edu.scu.hereis.service;

import edu.scu.hereis.dao.RestaurantMapper;
import edu.scu.hereis.entity.Restaurant;
import edu.scu.hereis.entity.RestaurantExample;
import edu.scu.hereis.exception.RestaurantServiceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 餐馆相关操作的服务类
 */
@Service
public class RestaurantService {

    @Autowired
    private SqlSession sqlSession;

    /**
     * 传入一个餐馆的对象，保存餐馆的信息
     *
     * @param restaurant 餐馆对象
     */
    @Transactional
    public void addRestaurant(Restaurant restaurant) {

        //检查输入
        if (null == restaurant || restaurant.getSpotId() <= 0) {
            throw new RestaurantServiceException(RestaurantServiceException.INVALID_INPUT);
        }

        //添加餐馆信息
        RestaurantMapper restaurantMapper = sqlSession.getMapper(RestaurantMapper.class);
        restaurantMapper.insert(restaurant);

        return;
    }

    /**
     * 传入一个餐馆的id(spotId)，将对应的餐馆进行删除
     *
     * @param spotId 要删除的餐馆的id
     */
    @Deprecated
    @Transactional
    public void deleteRestaurant(int spotId) {

        //检查输入
        if (spotId <= 0) {
            throw new RestaurantServiceException(RestaurantServiceException.INVALID_INPUT);
        }

        //删除对应的餐馆
        RestaurantMapper restaurantMapper = sqlSession.getMapper(RestaurantMapper.class);
        restaurantMapper.deleteByPrimaryKey(spotId);

        return;
    }

    /**
     * 根据传入的spotId判断该餐馆是否已经存在
     * @param spotId 餐馆id
     * @return 存在返回false, 不存在返回true
     */
    @Transactional
    public boolean restaurantExists(int spotId) {

        //检查输入
        if (spotId <= 0) {
            throw new RestaurantServiceException(RestaurantServiceException.INVALID_INPUT);
        }

        // 查找
        RestaurantMapper restaurantMapper = sqlSession.getMapper(RestaurantMapper.class);
        RestaurantExample restaurantExample = new RestaurantExample();
        restaurantExample.createCriteria().andSpotIdEqualTo(spotId);
        List<Restaurant> list = restaurantMapper.selectByExample(restaurantExample);
        if (list.size() > 0) {
            return false;
        }

        return true;
    }


}

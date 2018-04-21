package edu.scu.hereis.service;

import edu.scu.hereis.entity.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;


    /**
     * 测试通过
     */
    @Test
    public void addRestaurant() {

        Restaurant restaurant = new Restaurant();
        restaurant.setSpotId(1);
        restaurantService.addRestaurant(restaurant);

    }

    /**
     * 测试通过
     */
    @Test
    public void deleteRestaurant() {
        restaurantService.deleteRestaurant(1);
    }
}
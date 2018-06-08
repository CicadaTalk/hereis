package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Menu;
import edu.scu.hereis.entity.Restaurant;
import edu.scu.hereis.service.MenuService;
import edu.scu.hereis.service.RestaurantService;
import edu.scu.hereis.wxresult.MenuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 与餐馆相关，包括获取菜单
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    /**
     * 根据spotId获取详细菜单信息
     * @param spotId 热点id
     * @return 详细菜单信息
     */
    @GetMapping("/getMenuBySpotId")
    public MenuResult[] getMenuBySpotId(Integer spotId) {

        //根据spotId获取分类
        List<String> categories = menuService.getAllCategoryBySpotId(spotId);
        //每个分类获取旗下的菜单
        MenuResult[] menuResultArray = new MenuResult[categories.size()];//但会结果
        List<Menu> menus;
        int i = 0;
        MenuResult menuResult;
        for (String category :
                categories) {
            //先获取该分类下的菜单
            menus = menuService.getMenuByCategory(category);
            //再封装返回结果
            menuResult = new MenuResult(category, menus);
            menuResultArray[i++] = menuResult;
        }

        return menuResultArray;
    }

    /**
     * 添加新的菜单
     * @param menu 菜单对象
     * @return 菜单id
     */
    @PostMapping("/addMenu")
    public Integer addMenu(Menu menu) {

        if (menu != null) {
            // 如果该餐馆未存在则先插入餐馆记录
            if (restaurantService.restaurantExists(menu.getSpotId())) {
                Restaurant restaurant = new Restaurant();
                restaurant.setSpotId(menu.getSpotId());
                restaurantService.addRestaurant(restaurant);
            }
            // 插入菜单记录
            menuService.addMenu(menu);
        }
        return menuService.getLastInsertId();
    }
}

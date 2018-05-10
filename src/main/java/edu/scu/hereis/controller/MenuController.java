package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Menu;
import edu.scu.hereis.service.MenuService;
import edu.scu.hereis.wxresult.MenuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 与餐馆相关，包括获取菜单
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
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
}

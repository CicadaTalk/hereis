package edu.scu.hereis.service;

import edu.scu.hereis.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    /**
     * 测试通过
     */
    @Test
    public void addMenu() {

        Menu menu = new Menu();
        menu.setCategory("热菜");
        menu.setImgPath("./11.png");
        menu.setName("红烧肉");
        menu.setPrice(28.0);
        menu.setSpotId(1);

        menuService.addMenu(menu);
    }

    /**
     * 测试通过
     */
    @Test
    public void deleteMenu() {

        menuService.deleteMenu(2);
    }

    /**
     * 测试通过
     */
    @Test
    public void updateMenu() {
        Menu menu = new Menu();
        menu.setName("红肉");
        menu.setId(5);
        menuService.updateMenu(menu);
    }

    /**
     * 测试通过
     */
    @Test
    public void getAllCategoryBySpotId() {
        List<String> categories = menuService.getAllCategoryBySpotId(1);
        for (String category :
                categories) {
            System.out.println(category);
        }
    }

    /**
     * 测试通过
     */
    @Test
    public void getMenuByCategory() {
        List<Menu> menus = menuService.getMenuByCategory("热菜");
        for (Menu menu :
                menus) {
            System.out.println(menu);
        }
    }

    /**
     * 测试通过
     */
    @Test
    public void getMenu() {
        Menu menu = menuService.getMenu(5);
        System.out.println(menu);
    }

    /**
     * 测试通过
     */
    @Test
    public void getAllMenuBySpotId() {
        List<Menu> menus = menuService.getAllMenuBySpotId(1);

        for (Menu menu :
                menus) {
            System.out.println(menu);
        }

    }
}
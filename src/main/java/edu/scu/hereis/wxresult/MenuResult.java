package edu.scu.hereis.wxresult;


import java.util.List;

public class MenuResult {

    /**
     * 菜的类别
     */
    private String category;

    /**
     * 菜单
     */
    private Menu[] menus;

    /**
     * 传入类别category和menus,
     * 将传入的edu.scu.hereis.entity.Menu列表转为edu.scu.hereis.wxresult.Menu数组并且保存在此对象中，
     * 构造返回结果
     *
     * @param category 类别
     * @param menus    要转化的edu.scu.hereis.entity.Menu列表
     */
    public MenuResult(String category, List<edu.scu.hereis.entity.Menu> menus) {
        this.category = category;

        //新的Menu数组
        Menu[] menuArray = new Menu[menus.size()];
        //将旧的Menu列表转化为新的Menu列表
        int i = 0;
        for (edu.scu.hereis.entity.Menu menu :
                menus) {
            menuArray[i++] = new Menu(menu);
        }

        //将Menu数组保存到此对象中
        this.menus = menuArray;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Menu[] getMenus() {
        return menus;
    }

    public void setMenus(Menu[] menus) {
        this.menus = menus;
    }
}

/**
 * 被接收的菜单类
 */
class Menu {

    private String name;
    private String imagePath;
    private double price;

    /**
     * 构造
     *
     * @param menu
     */
    public Menu(edu.scu.hereis.entity.Menu menu) {
        this.name = menu.getName();
        this.imagePath = menu.getImgPath();
        this.price = menu.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


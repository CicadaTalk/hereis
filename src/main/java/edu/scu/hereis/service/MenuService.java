package edu.scu.hereis.service;

import edu.scu.hereis.dao.MenuMapper;
import edu.scu.hereis.entity.Menu;
import edu.scu.hereis.entity.MenuExample;
import edu.scu.hereis.exception.MenuServiceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private SqlSession sqlSession;

    /**
     * 添加菜单，必须保证菜单的spotId外键大于等于0
     *
     * @param menu 菜单对象
     */
    @Transactional
    public void addMenu(Menu menu) {

        //检查输入
        if (null == menu || menu.getSpotId() <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //存储菜单信息
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        menuMapper.insert(menu);

        return;
    }

    /**
     * 传入菜单的id，删除对应的菜单
     *
     * @param id 菜单id，必须大于0
     */
    @Transactional
    public void deleteMenu(int id) {

        //检查输入
        if (id <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //删除对应的菜单信息
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        menuMapper.deleteByPrimaryKey(id);

        return;
    }

    /**
     * 传入新的菜单信息，对菜单信息进行更新
     *
     * @param menu 菜单信息
     */
    @Transactional
    public void updateMenu(Menu menu) {

        //检查输入
        if (null == menu || menu.getId() <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //更新对应的菜单信息
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        menuMapper.updateByPrimaryKeySelective(menu);

        return;
    }

    /**
     * 传入spotId，获取该spotId下的所有菜单
     *
     * @param spotId
     * @return 返回所有的菜单列表
     */
    public List<Menu> getAllMenuBySpotId(Integer spotId) {
        //检查输入
        if (null == spotId || spotId <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //获取该spotId下的所有菜单
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andSpotIdEqualTo(spotId);
        List<Menu> menus = menuMapper.selectByExample(menuExample);

        return menus;
    }

    /**
     * 传入spot_id，获取该spot_id下的所有菜单分类
     *
     * @param spotId
     * @return 返回所有菜单分类
     */
    public List<String> getAllCategoryBySpotId(Integer spotId) {

        //检查输入
        if (null == spotId || spotId <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //获取该spot_id下的所有的菜单分类
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<String> categories = menuMapper.selectAllCategoryBySpotId(spotId);

        return categories;
    }

    /**
     * 根据传入的菜单类别，获取菜单
     *
     * @param category 菜单类别
     * @return 返回所有菜单组成的列表
     */
    public List<Menu> getMenuByCategory(String category) {

        //检查输入
        if (null == category) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //根据类别查找菜单
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andCategoryEqualTo(category);

        List<Menu> menus = menuMapper.selectByExample(menuExample);

        return menus;
    }

    /**
     * 传入菜单的id，获取对应的菜单信息
     *
     * @param id 菜单的id
     * @return 对应的菜单信息
     */
    public Menu getMenu(Integer id) {

        //检查输入
        if (null == id || id <= 0) {
            throw new MenuServiceException(MenuServiceException.INVALID_INPUT);
        }

        //获取菜单信息
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        Menu menu = menuMapper.selectByPrimaryKey(id);

        return menu;
    }
}

package edu.scu.hereis.dao;

import edu.scu.hereis.entity.Menu;
import edu.scu.hereis.entity.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    long countByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int deleteByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    List<Menu> selectByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    Menu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Fri Apr 20 16:26:09 CST 2018
     */
    int updateByPrimaryKey(Menu record);
}
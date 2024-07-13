package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 根据id 查找对应的套餐
     * @param id
     * @return
     */
    @Select("select * from setmeal where id=#{id}")
    Setmeal getById(Long id);

    /**
     * 根据id删除套餐
     * @param setmealIds
     */
    void deleteByIds(List<Long> setmealIds);

    /**
     * 修改套餐表
     */
    //这个注解只是更新了实体类中的数据，数据库的更新需要自己完成
    @AutoFill(value =OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据菜品id查询套餐
     * @return
     */
    @Select("select s.* from setmeal s left outer join setmeal_dish sd on s.id = sd.setmeal_id where sd.dish_id=#{dishId}")
    List<Setmeal> getByDishId(Long dishId);

    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);


    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}

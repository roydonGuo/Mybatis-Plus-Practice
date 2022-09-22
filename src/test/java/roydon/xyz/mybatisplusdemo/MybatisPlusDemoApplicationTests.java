package roydon.xyz.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import roydon.xyz.mybatisplusdemo.entity.User;
import roydon.xyz.mybatisplusdemo.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Resource
    private UserMapper userMapper;


    /**
     * select---------------------------------------------------------------
     */
    @Test
    public void testSelectById() {
        // 根据 ID 查询
        userMapper.selectById(2L);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "张三"); //查询条件
        // 根据 entity 条件，查询一条记录。查询的数据超过一条时，会抛出异常(例如：wrapper.eq("password", "123456");)
        userMapper.selectOne(wrapper);
    }

    @Test
    void testSelectBatchIds() {
        // 查询（根据ID 批量查询），返回 List<User> 集合，若ID 不存在像100L，那么只会查出存在的ID
        userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
//        userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L,100L)); // 只会查出1，2，3
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email", "itcast");
        userMapper.selectList(wrapper);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "123456");
        // 查询（根据 columnMap 条件）
        userMapper.selectByMap(map);
    }

    @Test
    public void testSelectPage() {

        IPage<User> page = new Page<>(0, 2); // 第一页，两条数据

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("password", "123456");

        userMapper.selectPage(page, queryWrapper);

        System.out.println("数据总条数" + page.getTotal());
        System.out.println("数据总页数" + page.getPages());
        System.out.println("当前页数" + page.getCurrent()); // getRecords为数据list
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20); // 条件：年龄大于20岁的用户
        // 根据 Wrapper 条件，查询总记录数，返回整数类型
        userMapper.selectCount(wrapper);
    }

    /**
     * update---------------------------------------------------------
     */
    @Test
    public void testUpdateById() {
        // 根据 ID 修改，返回整型，表示影响的行数
        User user = new User();
        user.setId(1L); //条件，根据id更新
        user.setAge(19); //更新的字段
        user.setPassword("666666");

        userMapper.updateById(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(20); //更新的字段
        user.setPassword("8888888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "zhangsan"); //匹配 user_name = zhangsan 的用户数据

        // 根据 whereWrapper 条件，更新记录
        userMapper.update(user, wrapper);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 21).set("password", "999999") //更新的字段
                .eq("user_name", "zhangsan"); //更新的条件
        userMapper.update(null, updateWrapper);
    }

    /**
     * insert
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("baomidou");
        user.setNickName("苞米豆");
        user.setPassword("123456");
        user.setAge(30);
        user.setEmail("insert@itcast.cn");
        user.setAddress("北京");
        userMapper.insert(user); //result数据库受影响的行数
        //获取自增长后的id值, 自增长后的id值会回填到user对象中
        System.out.println("id => " + user.getId());
    }

    /**
     * delete
     */
    @Test
    public void testDelete() {

        //用法一：
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name", "baomidou")
//                .eq("password", "123456");

        //用法二：
        User user = new User();
        user.setUserName("baomidou");
        user.setPassword("123456");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        // 根据包装条件做删除
        userMapper.delete(wrapper);
    }

    @Test
    public void testDeleteById() {
        // 根据id删除数据
        userMapper.deleteById(5L);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "lisi");
        map.put("password", "123456");

        // 根据map删除数据，多条件之间是and关系
        userMapper.deleteByMap(map);
    }

    @Test
    public void testDeleteBatchIds() {
        // 根据id批量删除数据
        userMapper.deleteBatchIds(Arrays.asList(3L, 4L));
//        userMapper.deleteBatchIds(Arrays.asList(3L, 4L,100L));
    }

    /**
     * 自定义方法
     */
    @Test
    void selById() {
        userMapper.getById(1L);
    }

    /**
     * 自定义的方法
     */
//    @Test
//    public void testFindById(){
//        User user = this.userMapper.findById(2L);
//        System.out.println(user);
//    }
    @Test
    public void testAllEq() {

        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
//        wrapper.allEq(params);
        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
//        wrapper.allEq(params, false);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age = ?
//        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")) , params);
        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id") || k.equals("name")), params);
        userMapper.selectList(wrapper);
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name LIKE ?
        // 参数：%五(String)
        wrapper.likeLeft("name", "五");
        userMapper.selectList(wrapper);
    }

    @Test
    public void testOrderByAgeDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒序排序
        // SELECT id,user_name,name,age,email AS mail FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
        userMapper.selectList(wrapper);

    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五").or().eq("age", 21);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五")
                .or()
                .eq("age", 21)
                .select("id", "name", "age"); //指定查询的字段
        userMapper.selectList(wrapper);
    }


}

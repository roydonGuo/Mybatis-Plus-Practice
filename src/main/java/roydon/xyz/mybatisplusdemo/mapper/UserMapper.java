package roydon.xyz.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import roydon.xyz.mybatisplusdemo.entity.User;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/21
 * Time: 20:18
 **/
public interface UserMapper extends BaseMapper<User> {

    User getById(Long id);

}

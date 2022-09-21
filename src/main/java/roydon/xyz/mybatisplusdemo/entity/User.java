package roydon.xyz.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/21
 * Time: 20:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO) // 主键自增长
    private Long id;

    private String userName;

    @TableField(select = false) // 忽略查询
    private String password;

    @TableField(value = "name") // 指定数据库字段
    private String nickName;
    private Integer age;
    private String email;

    @TableField(exist = false) // 数据库是否存在此字段
    private String address;

}

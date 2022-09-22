package roydon.xyz.mybatisplusdemo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/21
 * Time: 20:49
 **/
@Configuration
@MapperScan("roydon/xyz/mybatisplusdemo/mapper")
public class MybatisPlusConfig {

    /**
     * page分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}

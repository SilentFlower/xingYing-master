package com.xingying.shopping.master.config.mvc;

import com.xingying.shopping.master.common.utils.json.JSONUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author zhaoweihao
 * @since
 */
@Configuration
public class WebMvcConfig {

    /**
     * 配置统一的json转换
     * @return 消息转换器对象
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(JSONUtils.getObjectMapper());
        return converter;
    }
}

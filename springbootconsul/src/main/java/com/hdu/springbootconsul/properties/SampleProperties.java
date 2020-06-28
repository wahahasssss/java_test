package com.hdu.springbootconsul.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/8
 * @Time 下午5:35
 */

@ConfigurationProperties("sample")
@Data
public class SampleProperties {
    private String prop = "default value";

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}

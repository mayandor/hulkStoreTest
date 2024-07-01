package com.test.hulkstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
        "classpath:/config/queriesContext.xml"
})
public class Config {
}

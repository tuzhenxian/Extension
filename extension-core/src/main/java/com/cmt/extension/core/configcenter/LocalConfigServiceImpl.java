package com.cmt.extension.core.configcenter;

import com.cmt.extension.core.configcenter.model.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * LocalConfigServiceImpl读取本地配置
 *
 * @author tuzhenxian
 * @date 20-7-20
 */
public class LocalConfigServiceImpl implements ConfigService {
    private static final String CONFIG_FILE = "spi.yml";

    @Override
    public Application init(String appName) {
        Application application = null;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            application = mapper.readValue(in, Application.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Application.emptyIfNull(application);
    }
}

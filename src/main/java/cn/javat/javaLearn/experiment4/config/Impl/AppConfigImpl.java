package cn.javat.javaLearn.experiment4.config.Impl;

import cn.javat.javaLearn.experiment4.config.AppConfig;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置类
 * 单例模式设计，配置全局共享化
 */
public class AppConfigImpl implements AppConfig {
    private static volatile AppConfigImpl instance;
    @Getter
    Properties appConfig;

    private AppConfigImpl() {
        appConfig = new Properties();
        try {
            appConfig.load(this.getClass().getClassLoader().getResourceAsStream("Config.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getProperty(String key) {
        return appConfig.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return appConfig.getProperty(key, defaultValue);
    }

    @Override
    public void setProperty(String key, String value) {
        appConfig.setProperty(key, value);
    }

    /**
     * 获取单例
     *
     * @return AppConfig
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfigImpl.class) {
                if (instance == null) {
                    instance = new AppConfigImpl();
                }
            }
        }
        return instance;
    }
}
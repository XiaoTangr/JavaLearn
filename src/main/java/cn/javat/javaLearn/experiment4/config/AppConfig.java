package cn.javat.javaLearn.experiment4.config;

public interface AppConfig {
    String getProperty(String key);

    String getProperty(String key, String defaultValue);

    void setProperty(String key, String value);
}

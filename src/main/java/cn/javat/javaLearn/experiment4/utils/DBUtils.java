package cn.javat.javaLearn.experiment4.utils;

import cn.javat.javaLearn.experiment4.config.AppConfig;
import cn.javat.javaLearn.experiment4.config.Impl.AppConfigImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private final AppConfig appConfig;
    private boolean driverLoaded = false;

    public DBUtils() {
        this.appConfig = AppConfigImpl.getInstance();
        try {
            // 加载数据库驱动
            Class.forName(appConfig.getProperty("database.driver"));
            driverLoaded = true;
        } catch (ClassNotFoundException e) {
            AppUtils.print("数据库驱动加载失败: " + e.toString());
        }
    }

    // 获取数据库连接
    public Connection getConnection() {
        if (!driverLoaded) {
            AppUtils.print("数据库驱动未正确加载，无法建立连接");
            return null;
        }
        
        String url = appConfig.getProperty("database.url");
        String username = appConfig.getProperty("database.username");
        String password = appConfig.getProperty("database.password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            AppUtils.print("数据库连接失败，请检查数据库配置: " + e.toString());
        }
        return null;
    }

    // 关闭数据库连接
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                AppUtils.print("关闭数据库连接时出错: " + e.toString());
            }
        }
    }
}
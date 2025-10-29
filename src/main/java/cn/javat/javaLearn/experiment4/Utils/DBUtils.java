package cn.javat.javaLearn.experiment4.Utils;

import cn.javat.javaLearn.experiment4.config.AppConfig;
import cn.javat.javaLearn.experiment4.config.Impl.AppConfigImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private final AppConfig appConfig;

    public DBUtils() {
        this.appConfig = AppConfigImpl.getInstance();
        try {
            // 加载数据库驱动
            Class.forName(appConfig.getProperty("database.driver"));
        } catch (ClassNotFoundException e) {
            AppUtils.print(e.toString());
        }
    }

    // 获取数据库连接
    public Connection getConnection() {
        String url = appConfig.getProperty("database.url");
        String username = appConfig.getProperty("database.username");
        String password = appConfig.getProperty("database.password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            AppUtils.print(e.toString());
        }
        return null;
    }

    // 关闭数据库连接
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                AppUtils.print(e.toString());
            }
        }
    }
}
package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource ds;
    static {
        //读取配置文件
        Properties p = new Properties();
        InputStream ips = DBUtils.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        try {
            p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = p.getProperty("db.driver");
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");

        //创建数据库连接池对象
        ds = new DruidDataSource();
        //设置数据库连接信息
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        //初始连接数量
        ds.setInitialSize(3);
        //设置最大连接数量
        ds.setMaxActive(5);
    }
    public static Connection getConn() throws Exception {

        //获取连接对象
        Connection conn = ds.getConnection();
        System.out.println(conn);

        return conn;
    }
}

package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet(name = "regServlet",urlPatterns = "/reg")
public class regServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取连接
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(name+":"+password);

        //获取连接
        try(Connection conn = DBUtils.getConn()) {
            String sql ="insert into user value(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.print(name+":"+password);
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //
    }
}

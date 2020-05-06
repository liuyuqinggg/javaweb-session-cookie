import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TestCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        Cookie[] cookies = req.getCookies();

        if(cookies != null){
            writer.print("上一次访问的时间：");
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("lastLoginTime")){
                    long l = Long.parseLong(cookie.getValue());
                    Date date = new Date(l);
                    writer.print(date.toLocaleString());
                }
            }
        }else {
            writer.print("这是您的第一次访问");
        }

        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");//新建一个cookie
        cookie.setMaxAge(60*60); //设置cookie的有效期
        resp.addCookie(cookie); //响应给客户端一个cookie
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

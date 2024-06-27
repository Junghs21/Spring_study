package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    //싱글톤이라서 new MemberRepository();가 안됨
    //왜냐하면 MemberRepository 생성자를 private으로 막았기 때문
    //따라서 아래와 같이 getInstance()로 가져와야 함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        //HTML코드를 Java코드로 작성한 것
        //서블릿으로 하면 HTML코드를 JAVA코드로 다 더해야 하므로 굉장히 불편한 단점이 존재
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "     <meta charset=\"UTF-8\">\n" +
                "     <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                "    username: <input type=\"text\" name=\"username\" />\n" +
                "    age: <input type=\"text\" name=\"age\" />\n" +
                "    <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}

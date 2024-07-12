package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //URL(key)과 컨트롤러V1(value)의 Map 형태로 매핑 정보 저장
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1()); //new-form이 호출되면 MemberFormControllerV1 객체가 반환됨
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());   // save가 호출되면 MemberSaveControllerV1 객체가 반환
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());    //members가 호출되면 MemberListControllerV1 객체가 반환
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //RequestURI값을 가져오므로 new-form, save, members와 같은 주소 값(키 값)을 가져옴
        String requestURI = request.getRequestURI();

        //controllerMap.put을 통해 저장한 매핑 정보를 바탕으로 호출한 키 값(주소 값)으로 매핑된 객체가 반환됨
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //다형성을 통해서 각각의 객체에 override된 process로직들을 호출
        controller.process(request, response);
    }
}

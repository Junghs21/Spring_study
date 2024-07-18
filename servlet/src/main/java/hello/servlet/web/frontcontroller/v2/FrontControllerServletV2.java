package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    //URL(key)과 컨트롤러V2(value)의 Map 형태로 매핑 정보 저장
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2()); //new-form이 호출되면 MemberFormControllerV2 객체가 반환됨
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());   // save가 호출되면 MemberSaveControllerV2 객체가 반환
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());    //members가 호출되면 MemberListControllerV2 객체가 반환
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestURI값을 가져오므로 new-form, save, members와 같은 주소 값(키 값)을 가져옴
        String requestURI = request.getRequestURI();

        //controllerMap.put을 통해 저장한 매핑 정보를 바탕으로 호출한 키 값(주소 값)으로 매핑된 객체가 반환됨
        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //다형성을 통해서 각각의 객체에 override된 process로직들을 호출
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}

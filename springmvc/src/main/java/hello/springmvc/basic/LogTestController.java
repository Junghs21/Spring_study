package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());   //<- 해당 코드가 Slf4j 어노테이션과 동일한 코드라고 생각하면 됨

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
//        log.info(" info log=" + name); //위의 info랑 동일한 코드이지만, 이 코드는 사용하지 않는 것을 권장
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}

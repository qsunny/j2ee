package web.controller;

import com.aaron.springbootDemo.bean.User;
import com.aaron.springbootDemo.bean.common.GenericResp;
import com.aaron.springbootDemo.core.pager.Page;
import com.aaron.springbootDemo.core.service.user.IUserService;
import com.aaron.springbootDemo.utils.CommUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/api")
public class SpringbootDemoController {

    private static final Logger log = LoggerFactory.getLogger(SpringbootDemoController.class);
    @Resource
    private IUserService userService;

    @RequestMapping(value="/index",method = RequestMethod.POST)
    public GenericResp<Page<User>> loginIndex(HttpServletRequest request, HttpServletResponse response, @RequestBody Page<User> page) {
        GenericResp<Page<User>> resp = new GenericResp<>();
        try {
            System.out.println("============loginIndex===============");
            if(CommUtils.isNull(page.getParam())) {
                User user = new User();
                page.setParam(user);
            }
            page = userService.getPagerModelByQuery(page.getParam(),page);
            resp.setData(page);
        } catch (Exception e) {
            log.error("loginIndex",e);
        }
        return resp;
    }

    @RequestMapping(value="/user/info",method = RequestMethod.POST)
    public GenericResp<User> getUser(HttpServletRequest request, HttpServletResponse response) {
        GenericResp<User> resp = new GenericResp<>();
        try {
            System.out.println("============getUser===============");


        } catch (Exception e) {
            e.printStackTrace();
            log.error("getUser",e);
        }
        return resp;
    }
}

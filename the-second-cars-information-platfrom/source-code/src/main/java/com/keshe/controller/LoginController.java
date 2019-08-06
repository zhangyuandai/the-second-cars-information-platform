package com.keshe.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.keshe.dao.AdminDao;
import com.keshe.dao.CarBrandsDao;
import com.keshe.dao.UserDao;
import com.keshe.entities.Admin;
import com.keshe.entities.CarBrand;
import com.keshe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CarBrandsDao carBrandsDao;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @PostMapping("/user/handleRegister")
    public String handleRegister(User user){
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String index(Model model){
        List<CarBrand> carbrand =  carBrandsDao.findAll();
        model.addAttribute("carbrand",carbrand);
        return "index";
    }
//    @GetMapping("/")
//    public String shouye(){
//        return "index";
//    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/login2")
    public String login2(){
        return "login2";
    }

    @PostMapping("/handleLogin2")
    public String login2(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session, Map<String,Object> map){
        Admin admin = adminDao.findByNameAndPassword(name,password);
        if (admin!=null){
            session.setAttribute("username",name);
            return "redirect:/admin.html";
        }else{
            map.put("msg","用户名或密码错误!");
            return "login2";
        }
    }

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping("/handleLogin")
    public String imgvrifyControllerDefaultKaptcha(@RequestParam("username") String username,
                                                 @RequestParam("password") String password,
                                                 HttpSession session, Map<String,Object> map,
                                                 HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,
                                                   Model model) {
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("rand");
        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);

        if (!captchaId.equals(parameter)) {
            map.put("info","验证码错误");
            return "login";
        } else {
            User user = userDao.findByUsernameAndPassword(username, password);
            if (user != null) {
                session.setAttribute("username",username);
                model.addAttribute("user",user);
                return "index_user";
            } else {
                map.put("msg", "用户名或密码错误!");
                return "login";
            }
//            andView.addObject("info", "登录成功");
//            andView.setViewName("succeed");
        }
    }

}

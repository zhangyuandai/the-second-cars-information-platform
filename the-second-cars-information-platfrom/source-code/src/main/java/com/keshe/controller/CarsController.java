package com.keshe.controller;

import com.keshe.dao.CarBrandsDao;
import com.keshe.dao.CarsDao;
import com.keshe.entities.CarBrand;
import com.keshe.entities.cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
public class CarsController {
    @Autowired
    private CarsDao carsDao;

    @Autowired
    private CarBrandsDao carBrandsDao;

    @PostMapping("/zhuce")
    public String tijiao(@RequestParam(value="carname") String carname,
                         @RequestParam(value="price") String price,
                         @RequestParam("city") String city,
                         @RequestParam("phonenumber") String phonenumber,
                         @RequestParam(value="tupian") MultipartFile file,
                         Model model){
        cars cars = new cars();
        cars.setCarname(carname);
        cars.setPrice(price);
        cars.setCity(city);
        cars.setPhonenumber(phonenumber);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("/home/weijia/IdeaProject/ershouche/src/main/resources/static/cars/img/"+carname+".png")));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String filename="/home/weijia/IdeaProject/ershouche/src/main/resources/static/cars/img/"+carname+".png";
                cars.setTupian(filename);
                carsDao.save(cars);//增加用户
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            List<cars> carss = carsDao.findAll();
            model.addAttribute("carss",carss);
            return "index_cars";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }


    //查询所有车辆列表
    @GetMapping("/cars")
    public String userList(Model model){
        List<cars> cars = carsDao.findAll();
        model.addAttribute("cars",cars);
        return "carsList";
    }

    //添加一个成员
//    @PostMapping("/caradd")
//    public String caradd(@RequestParam("username") String username,
//                          @RequestParam("password") String password,@RequestParam("age") Integer age){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setAge(age);
//
//        userDao.save(user);
//
//        return "redirect:/users";
//    }

    //查询一个成员
    @GetMapping("/car/{id}")
    public cars userFindOne(@PathVariable("id") Long id){
        return carsDao.findById(id).get();
    }

    //删除一个成员
    @RequestMapping("/cardelete")
    public String carDelete(Long id){
        carsDao.deleteById(id);
        return"redirect:/cars";
    }

    //更新一个成员
    @GetMapping(value = "/cartoEdit")
    public String carUpdate(Model model,Long id){
        cars car = carsDao.findUserById(id);
        model.addAttribute("car",car);
        return "caredit";
    }

    @PostMapping("/caredit")
    public String edit (cars  car){
        carsDao.save(car);
        return "redirect:/cars";
    }


///////////////////////////////////////////////////////////////////////////
    //CarBrands管理

    //查询所有品牌列表
    @GetMapping("/allbrands")
    public String brandsList(Model model){
        List<CarBrand> carBrands = carBrandsDao.findAll();
        model.addAttribute("brands",carBrands);
        return "brandList";
    }

    //增加一个品牌
    @PostMapping("/brandadd")
    public String carbrands(@RequestParam(value="brandname") String brandname,
                         @RequestParam(value="tupian") MultipartFile file,
                         Model model){
        CarBrand carBrand = new CarBrand();
        carBrand.setBrandname(brandname);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("/home/weijia/IdeaProject/ershouche/src/main/resources/static/cars/img/"+brandname+".jpg")));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String filename="/home/weijia/IdeaProject/ershouche/src/main/resources/static/cars/img/"+brandname+".jpg";
                carBrand.setTupian(filename);
                carBrandsDao.save(carBrand);//增加用户
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            model.addAttribute("carband",carBrand);
            return "redirect:/allbrands";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    //更新一个成员
    @GetMapping(value = "/brandtoEdit")
    public String brandUpdate(Model model,Long id){
        CarBrand carBrand= carBrandsDao.findUserById(id);
        model.addAttribute("carbrand",carBrand);
        return "brandEdit";
    }

    @PostMapping("/brandedit")
    public String brandedit (CarBrand carBrand){
        carBrandsDao.save(carBrand);
        return "redirect:/allbrands";
    }

    //删除一个成员
    @RequestMapping("/branddelete")
    public String brandDelete(Long id){
        carBrandsDao.deleteById(id);
        return"redirect:/allbrands";
    }

}

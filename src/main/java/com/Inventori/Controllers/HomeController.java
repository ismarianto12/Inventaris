package com.Inventori.Controllers;

import com.Inventori.Repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.Inventori.Models.Distributor;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class HomeController {


    @Value("${file.upload-dir}")
    private String uploadDir;

    private DistributorRepository distributorRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model) {
        model.addAttribute("message", "Hello World");
        model.addAttribute("title", "Inventori");
        model.addAttribute("directory", uploadDir);
        return "/home";
    }

    @RequestMapping(value = "/loginaction", method = RequestMethod.GET)
    public Object loginAction(@RequestParam Map<String, Object> param, HttpServletResponse httpServletResponse) {
        Map<String, Object> data = new HashMap<>();
        if (param.get("username").toString() == "admin" && param.get("password").toString() == "123") {

            data.put("ket", "data login berhasil");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/dashboard");
            return redirectView;
        } else {
            data.put("ket", "data login berhasil");
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/?ket=logingagal");
            return redirectView;
        }
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("title", "Inventori");
        return "about";
    }

    @RequestMapping(value = "/distributor", method = RequestMethod.GET)
    public String specapi(Model model,Distributor distributor) {
        List<Distributor> data = distributorRepository.findAll();
        model.addAttribute("inventoridata",data);
        model.addAttribute("data", "Inventori");
        return "distributor";
    }

    @RequestMapping(value = "/clustering",method = RequestMethod.GET)
    public String detail(Model model,Distributor distributor) {
        List<Distributor> data = distributorRepository.findAll();
        model.addAttribute("inventoridata",data);
        model.addAttribute("data", "Inventori");
        return "clustering";

    }

}


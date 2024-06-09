package ppj.assignments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ppj.assignments.data.Country;
import ppj.assignments.service.CountryService;

import java.util.List;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    String showHome() {
        return "home";
    }
}




package kg.djedai.controllers;

import kg.djedai.models.User;
import kg.djedai.store.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


/**
 * @author Zhoodar
 * @since 06.07.2016.
 */

@Controller
public class AdminController {

    @Autowired
    private DAOFactory factory;

    @RequestMapping(value = "/admin/users",method = RequestMethod.GET)
    public String getAdminPage(ModelMap model){
        model.addAttribute("users",this.factory.userStorage.getAll());
        return "admin/users";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String getAddUser(){
        return "admin/add";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String AddUser(@ModelAttribute User user){
        this.factory.userStorage.create(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/edit/user",method = RequestMethod.GET)
    public String getEditUser(@RequestParam(value="id", required=true) Integer id, ModelMap model){
        model.addAttribute("user",this.factory.userStorage.read(id));
        return "admin/edit";
    }

    @RequestMapping(value = "/admin/edit/user",method = RequestMethod.POST)
    public String editUser(@ModelAttribute User user){
        this.factory.userStorage.update(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/delete/user",method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Integer id){
        this.factory.userStorage.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/","/login" },method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error",required = false)String error){
        ModelAndView model = new ModelAndView();
        if(error!=null){
            model.addObject("error","Error: Invalid login or password! ");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Principal user, ModelMap model){
        if(user!=null){
            model.addAttribute("errorMsg",user.getName()+" you don't have an access to this page!");
        }else {
            model.addAttribute("errorMsg","You don't have an access to this page!");
        }
        return "accessDenied";
    }

}

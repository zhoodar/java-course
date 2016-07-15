package kg.djedai.controllers;

import kg.djedai.models.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.models.Dog;
import kg.djedai.models.Pet;
import kg.djedai.store.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Zhoodar
 * @since 06.07.2016.
 */
@Controller
public class ClinicController {

    @Autowired
    private DAOFactory factory;

    private List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();
    private boolean isSearching = false;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(ModelMap model){
        model.addAttribute("results",this.foundClient);
        model.addAttribute("content", isSearching);
        this.isSearching=false;
        return "clinic/Index";
    }
    @RequestMapping(value = "/view/clients", method = RequestMethod.GET)
    public String showClients(ModelMap model){
        model.addAttribute("clients",factory.hibernateStorage.getClients());
        return "client/ClientView";
    }

    @RequestMapping(value = "/add/client", method = RequestMethod.GET)
    public String getAddClient(){
        return "client/CreateClient";
    }

    @RequestMapping(value = "/add/client", method = RequestMethod.POST)
    public String addClient(@RequestParam("nameClient")String nameClient ,
                            @RequestParam("namePet")String namePet,
                            @RequestParam("type")String type ){
        String id = factory.hibernateStorage.generateId();
        ClientModel client = new ClientModel(id,nameClient);
        Pet pet = createPet(type,namePet);
        factory.hibernateStorage.addClient(client);
        factory.hibernateStorage.addPetToClient(pet,id);
        return "redirect:/view/clients";
    }

    private Pet createPet(String type , String namePet) {
        Pet pet;
        if(type.equals("2")){
            pet = new Cat(namePet);
        } else
            pet = new Dog(namePet);
        return pet;
    }

    @RequestMapping(value = "/edit/client",method = RequestMethod.GET)
    public String getEditClient(@RequestParam(value="id", required=true) String id, ModelMap model){
        model.addAttribute("client",factory.hibernateStorage.getClientById(id));
        model.addAttribute("pets",factory.hibernateStorage.getPetCurrentClient(id));
        return "client/EditClient";
    }

    @RequestMapping(value = "/edit/client", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute ClientModel client){
        this.factory.hibernateStorage.editClient(client);
        return "redirect:/view/clients";
    }

    @RequestMapping(value = "/add/pet",method = RequestMethod.POST)
    public  String addPet( @RequestParam("id")String id,
                           @RequestParam("typePet")String type,
                           @RequestParam("petName")String name, ModelMap model){
        Pet pet = createPet(type,name);
        this.factory.hibernateStorage.addPetToClient(pet,id);
        model.addAttribute("id",id);
        return "redirect:/edit/client";
    }

    @RequestMapping(value = "/delete/client",method = RequestMethod.GET)
    public String deleteClient(@RequestParam(value="id") String id){
        factory.hibernateStorage.deleteClient(id);
        return "redirect:/view/client";
    }

    @RequestMapping(value = "/delete/pet",method = RequestMethod.POST)
    public String deleteClientPet(@RequestParam("id") String id,
                                  @RequestParam("petName")String name, ModelMap model){
        factory.hibernateStorage.deletePetCurrentClient(id,name);
        model.addAttribute("id",id);
        return "redirect:/edit/client";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request) {
        this.isSearching = true;
        this.foundClient.clear();
        String toSearch = request.getParameter("name");
        if(toSearch!=null) {
            if (request.getParameter("typeSearch") != null) {
                this.foundClient.addAll(this.factory.hibernateStorage.findByFullName(toSearch));
            } else {
                this.foundClient.addAll(this.factory.hibernateStorage.findByContain(toSearch));
            }
        }
        return "redirect:/index";
    }

}

package usitcc.amarillo.theteam889.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usitcc.amarillo.theteam889.data.ReportPets;
import usitcc.amarillo.theteam889.entity.Pet;
import usitcc.amarillo.theteam889.services.PetServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PetController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    PetServiceImpl petService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }

    @GetMapping("/pet")
    public String loadPetPage(Model model, Pet tran){
        Iterable<Pet> pts = petService.getAllPet();
        model.addAttribute("petform", new Pet());
        model.addAttribute("pets", pts);
        model.addAttribute("next", "/create");
        return "pet";
    }

    @PostMapping(value="/create")
    public String createPet(@ModelAttribute("petform") Pet pet){
        System.out.println(pet.getStatus());
        petService.createPet(pet);
        return "redirect:pet";
    }

    @GetMapping(value="/delete/{id}")
    public String deletePet(@PathVariable String id){
        long tid = Long.parseLong(id);
        petService.deletePetById(tid);
        return "redirect:/pet";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable String id, Model model) {
        Iterable<Pet> pts = petService.getAllPet();
        model.addAttribute("petform", petService.getPetById(Long.parseLong(id)).get());
        model.addAttribute("pets", pts);
        model.addAttribute("next", "/update/pet/" + id);
        return ("pet");
    }

    @RequestMapping(value = "/update/pet/{id}", method = RequestMethod.POST)
    public String saveUpdate(@PathVariable String id, @ModelAttribute("myObject") Pet updated) throws Exception {
        petService.updatePet(id,updated);
        return "redirect:/pet";
    }


    @GetMapping(value="/report")
    public String loadReport(Model model){
        BigDecimal revenue = BigDecimal.valueOf(0.0);
        BigDecimal price = BigDecimal.valueOf(0.0);
        ReportPets rp = new ReportPets();
        Iterable<Pet> pts = new ArrayList<>();
        model.addAttribute("reported",pts);
        model.addAttribute("st",rp);
        return "report";
    }

    @GetMapping(value="/report/{status}")
    public String getReport(@PathVariable String status,Model model) throws IOException {
        ReportPets rp = new ReportPets();
        model.addAttribute("st",rp);
        Iterable<Pet> pts = petService.getPetByStatus(status);
        model.addAttribute("reported",pts);
        List<Pet> write = new ArrayList<>();
        pts.forEach(write::add);
        writhInJson(write);
        return ("report");
    }

    public void writhInJson(List<Pet> rs)throws IOException {
        StringBuilder sb = new StringBuilder();
        rs.stream().forEach(s->sb.append(s));

        Path path = Paths.get("Report.txt");
        Files.deleteIfExists(path);
        FileWriter fw = new FileWriter(String.valueOf(path));
        try {
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

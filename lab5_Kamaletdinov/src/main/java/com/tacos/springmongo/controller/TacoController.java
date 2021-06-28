package com.tacos.springmongo.controller;

import com.tacos.springmongo.model.Taco;
import com.tacos.springmongo.repository.TacoRepository;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TacoController {

    private final TacoRepository tacoRepository;

    @RequestMapping("/taco")
    public String taco(Model model) {
        model.addAttribute("tacos", tacoRepository.findAll());
        return "taco";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String tacoName, @RequestParam String tacoDesc, @RequestParam Double tacoPrice, @RequestParam String tacoImage) {
        Taco taco = new Taco();
        taco.setTacoName(tacoName);
        taco.setTacoDesc(tacoDesc);
        taco.setTacoPrice(tacoPrice);
        taco.setTacoImage(tacoImage);
        tacoRepository.save(taco);

        return "redirect:/show/" + taco.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("taco", tacoRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        tacoRepository.delete(taco.get());

        return "redirect:/taco";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("taco", tacoRepository.findById(id).get());
        return "edit";
    }
    
    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String tacoName, @RequestParam String tacoDesc, @RequestParam Double tacoPrice, @RequestParam String tacoImage) {
        Optional<Taco> taco = tacoRepository.findById(id);
        taco.get().setTacoName(tacoName);
        taco.get().setTacoDesc(tacoDesc);
        taco.get().setTacoPrice(tacoPrice);
        taco.get().setTacoImage(tacoImage);
        tacoRepository.save(taco.get());

        return "redirect:/show/" + taco.get().getId();
    }
}

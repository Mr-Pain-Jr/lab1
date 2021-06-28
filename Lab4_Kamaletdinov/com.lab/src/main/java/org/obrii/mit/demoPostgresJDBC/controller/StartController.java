/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.demoPostgresJDBC.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 38068
 */
@Controller
public class StartController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/start")
    public String getStart() {
        return "start";
    }

    @PostMapping("/runSQL")
    public String runSQL(@RequestParam String script, Model model) {

        /* int result = jdbcTemplate.queryForObject(
        script, Integer.class);*/
        String firstWord = script.split(" ")[0].toLowerCase();
        List<String> line = new ArrayList<>();

        if (firstWord.equals("select")) {

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(script);

            for (Map row : rows) {
                line.add(row.toString());
            }
        } else if (firstWord.equals("insert")) {
            jdbcTemplate.update(script);
            line.add(script + " executed successfully");
        } else {
            line.add("Incorrect Statement");
        }

        model.addAttribute("firstWord", firstWord);
        model.addAttribute("list", line);

        return "result";
    }

}

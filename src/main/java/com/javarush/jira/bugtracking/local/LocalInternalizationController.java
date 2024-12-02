package com.javarush.jira.bugtracking.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;
@Slf4j
@Controller
@RequiredArgsConstructor
public class LocalInternalizationController {

    @GetMapping("/intern/{str}")
    public void RuInternalization(@PathVariable String str, Locale locale){
        log.info("switched to locale"+locale);
        if(str.equals("ru")){

        }else{

        }
    }
}

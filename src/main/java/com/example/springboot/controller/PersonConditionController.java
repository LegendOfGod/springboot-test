package com.example.springboot.controller;

import com.example.springboot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lqb
 * @date 2021/12/20 15:05
 */
@RestController
public class PersonConditionController {


    private Person windowsPerson;
    private Person linuxPerson;

    @Autowired(required = false)
    @Qualifier("winP")
    public void setWindowsPerson(Person windowsPerson) {
        this.windowsPerson = windowsPerson;
    }


    @Autowired(required = false)
    @Qualifier("linuxP")
    public void setLinuxPerson(Person linuxPerson) {
        this.linuxPerson = linuxPerson;
    }


    @RequestMapping("/windowsPerson")
    public Person windowsPerson(){
        return windowsPerson;
    }

    @RequestMapping("/linuxPerson")
    public Person linuxPerson(){
        return linuxPerson;
    }
}

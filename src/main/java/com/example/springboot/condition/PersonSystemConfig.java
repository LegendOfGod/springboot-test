package com.example.springboot.condition;

import com.example.springboot.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author lqb
 * @date 2021/12/20 15:01
 */
@Configuration
public class PersonSystemConfig {


    @Bean("winP")
    @Conditional(WindowsCondition.class)
    public Person windowsPerson(){
        Person person = new Person();
        person.setName("微软");
        person.setSystem("windows");
        return person;
    }


    @Bean("linuxP")
    @Conditional(LinuxCondition.class)
    public Person linuxPerson(){
        Person person = new Person();
        person.setName("linux");
        person.setSystem("linux");
        return person;
    }

}

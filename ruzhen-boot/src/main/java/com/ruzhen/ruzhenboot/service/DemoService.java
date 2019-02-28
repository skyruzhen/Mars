package com.ruzhen.ruzhenboot.service;

import com.ruzhen.ruzhenboot.entity.Person;

public interface DemoService {
    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);

}

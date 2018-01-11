// iService.aidl
package com.richard.servicedemo;

import com.richard.servicedemo.Person;
/**
 * Created by richardxu on 2018/1/4.
 */

interface iService {
    String getColor();
    void sayHello();
    double getWeight(int value);

    void savePersonInfo(in Person person);
    List<Person> getAllPerson();
}


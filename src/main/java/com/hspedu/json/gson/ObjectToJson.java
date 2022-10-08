package com.hspedu.json.gson;

import com.google.gson.Gson;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.vo.PersonVo;

import java.util.List;
import java.util.Map;

public class ObjectToJson {

    /**
     * javabean to json
     *
     * @param person
     * @return
     */
    public static String javabeanToJson(Person person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        return json;
    }

    /**
     * list to json
     *
     * @param list
     * @return
     */
    public static String listToJson(List<PersonVo> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * map to json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Person> map) {

        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }
}

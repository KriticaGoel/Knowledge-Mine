package designPattern.creational.prototype.PrototypeRegistry;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentRegistry {

    Map<String, Student> template = new HashMap<String, Student>();

    public void register(String name, Student s){
        template.put(name, s);
    }

    public Student get(String name){
        Student prototype = template.get(name);
        // Use Optional + lambda to throw an exception if template is missing,
        // then clone the found prototype.
        return Optional.ofNullable(prototype)
                .orElseThrow(() -> new RuntimeException("template "+name+" not found"))
                .clone();

    }
}

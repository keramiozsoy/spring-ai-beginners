package com.example;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    private static final Logger log = LoggerFactory.getLogger(LessonService.class);
    private List<Lesson> lessons = new ArrayList<>();

    @Tool(name = "custom_get_lessons", description = "Get a list of lessons")
    public List<Lesson> getLessons() {
        return lessons;
    }

    @Tool(name = "custom_get_lesson", description = "Get a single lessons by title")
    public Lesson getLesson(String title) {
        return lessons.stream().filter(lesson -> lesson.title().equals(title)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init() {
        lessons.addAll(List.of(
                new Lesson("Building Web Applications with Spring Boot (FreeCodeCamp)", "https://youtu.be/31KTdfRH6nY"),
                new Lesson("Spring Boot Tutorial for Beginners - 2023 Crash Course using Spring Boot 3","https://youtu.be/UgX5lgv4uVM")
        ));
    }

}

package de.ait.ec.controllers;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.services.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CoursesService coursesService;

    @Operation(summary = "Добавление курса", description = "Доступно администратору системы")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto addCourse(@RequestBody NewCourseDto newCourse) {
        return coursesService.addCourse(newCourse);
    }

    @Operation(summary = "Получение всех курсов", description = "Доступно всем пользователям")
    @GetMapping
    public List<CourseDto> getCourses(){
        return coursesService.getCourses();
    }

}

package de.ait.ec.services.impl;

import de.ait.ec.dto.*;
import de.ait.ec.exceptions.RestException;
import de.ait.ec.models.Course;
import de.ait.ec.models.Lesson;
import de.ait.ec.models.User;
import de.ait.ec.repositories.CoursesRepository;
import de.ait.ec.repositories.LessonsRepository;
import de.ait.ec.repositories.UsersRepository;
import de.ait.ec.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static de.ait.ec.dto.CourseDto.from;
import static de.ait.ec.dto.LessonDto.from;
import static de.ait.ec.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;

    private final LessonsRepository lessonsRepository;

    private final UsersRepository studentsRepository;

    @Override
    public CourseDto addCourse(NewCourseDto newCourse) {
        Course course = Course.builder()
                .title(newCourse.getTitle())
                .description(newCourse.getDescription())
                .price(newCourse.getPrice())
                .beginDate(LocalDate.parse(newCourse.getBeginDate()))
                .endDate(LocalDate.parse(newCourse.getEndDate()))
                .state(Course.State.DRAFT)
                .build();

        coursesRepository.save(course);
        return from(course);
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = coursesRepository.findAll();
        return from(courses);
    }

    @Override
    public CourseDto getCourse(Long courseId) {
         /*
        Optional<Course> courseOptional = coursesRepository.findById(courseId); // находим Optional в базе

        Course course;

        if (courseOptional.isPresent()) { // если в этом Optional данные есть (нашли курс)
            course = courseOptional.get(); // получаем объект этого курса
        } else {
            // если там ничего на самом деле не лежит, то выбрасываем ошибку
            throw new IllegalArgumentException("Course with id <" + courseId + "> not found");
        }
        */

        // Optional
       Course course = getCourseOrThrow(courseId);
        return from(course);
    }

    @Override
    public LessonDto addLessonToCourse(Long courseId, NewLessonDto newLesson) {

        Course course = getCourseOrThrow(courseId);

        Lesson lesson;

        if (newLesson.getExistsLessonId() == null) {
            lesson = Lesson.builder()
                    .name(newLesson.getName())
                    .dayOfWeek(DayOfWeek.valueOf(newLesson.getDayOfWeek()))
                    .startTime(LocalTime.parse(newLesson.getStartTime()))
                    .finishTime(LocalTime.parse(newLesson.getFinishTime()))
                    .course(course) // проставляем, к какому курсу привязан урок
                    .build();

        } else {
            lesson = lessonsRepository.findById(newLesson.getExistsLessonId())
                    .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Lesson with id <" + newLesson.getExistsLessonId() + "> not found"));

        }



        lessonsRepository.save(lesson); // сохраняем урок

        return from(lesson); // возвращаем пользователю ответ

    }

    @Override
    public List<LessonDto> getLessonsOfCourse(Long courseId) {
        // найдем курс, у которого хотим забрать уроки
        Course course = getCourseOrThrow(courseId);

        // получим все уроки этого курса:
        Set<Lesson> lessons = lessonsRepository.findAllByCourseOrderById(course);
        // сконвертировали в DTO
        return from(lessons);
    }

    @Override
    public LessonDto deleteLessonFromCourse(Long courseId, Long lessonId) {
        Course course = getCourseOrThrow(courseId);

        Lesson lesson = lessonsRepository.findByCourseAndId(course,lessonId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Lesson with id <" + lessonId + "> not found in course with id <" + courseId + ">"));

        lesson.setCourse(null);
        lessonsRepository.save(lesson);
        return from(lesson);

       /* Set<Lesson> lessonsOfCourse = course.getLessons();

        for (Lesson lesson : lessonsOfCourse){
            if (lesson.getId().equals(lessonId)){
                lesson.setCourse(null);
                lessonsRepository.save(lesson);
                return from(lesson);
            }
        }
       throw new RestException(HttpStatus.NOT_FOUND, "Lesson with id <" + lessonId + "> not found in course with id <" + courseId + ">");

        */
    }

    @Override
    public LessonDto updateLessonInCourse(Long courseId, Long lessonId, UpdateLessonDto updateLesson) {
        Course course = getCourseOrThrow(courseId);

        Lesson lesson = lessonsRepository.findByCourseAndId(course,lessonId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Lesson with id <" + lessonId + "> not found in course with id <" + courseId + ">"));
        
        lesson.setName(updateLesson.getName());
        if (lesson.getStartTime() !=null){
            lesson.setStartTime(LocalTime.parse(updateLesson.getStartTime()));
        } else {
            lesson.setStartTime(null);
        }
        if (lesson.getFinishTime() !=null){
            lesson.setFinishTime(LocalTime.parse(updateLesson.getFinishTime()));
        } else {
            lesson.setFinishTime(null);
        }
        if (lesson.getDayOfWeek() !=null){
            lesson.setDayOfWeek(DayOfWeek.valueOf(updateLesson.getDayOfWeek()));
        } else {
            lesson.setDayOfWeek(null);
        }

        lessonsRepository.save(lesson);
        return from(lesson);

        /*
        Set<Lesson> lessonsOfCourse = course.getLessons();


        for (Lesson lesson : lessonsOfCourse){
            if (lesson.getId().equals(lessonId)){
                lesson.setName(updateLesson.getName());
                lesson.setDayOfWeek(DayOfWeek.valueOf(updateLesson.getDayOfWeek()));
                lesson.setStartTime(LocalTime.parse(updateLesson.getStartTime()));
                lesson.setFinishTime(LocalTime.parse(updateLesson.getFinishTime()));


                lessonsRepository.save(lesson);
                return from(lesson);
            }
        }
        throw new RestException(HttpStatus.NOT_FOUND, "Lesson with id <" + lessonId + "> not found in course with id <" + courseId + ">");

         */

    }

    @Override
    public List<UserDto> addStudentToCourse(Long courseId, StudentToCourseDto studentData) {
        Course course = getCourseOrThrow(courseId);

        User student = studentsRepository.findById(studentData.getUserId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + studentData.getUserId() + "> not found"));

        //не рабочий вариант
        //course.getStudents().add(student);
        //coursesRepository.save(course);

        student.getCourses().add(course);

        if (!student.getCourses().add(course)){
            throw new RestException(HttpStatus.BAD_REQUEST,"User with id <" + student.getId() + "> already in course with id<" + courseId + ">");
        }

        studentsRepository.save(student);

        Set<User> studentsOfCourse = studentsRepository.findAllByCoursesContainsOrderById(course);
        return from(studentsOfCourse);
    }

    @Override
    public List<UserDto> getStudentsOfCourse(Long courseId) {
        Course course = getCourseOrThrow(courseId);
        Set<User> studentsOfCourse = studentsRepository.findAllByCoursesContainsOrderById(course);

        return from(studentsOfCourse);
    }

    private Course getCourseOrThrow(Long courseId) {
        return coursesRepository.findById(courseId) // либо находим курс по id
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Course with id <" + courseId + "> not found"));
    }


}

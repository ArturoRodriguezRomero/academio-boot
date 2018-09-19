package com.autentia.academioboot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.autentia.academioboot.exception.ResourceNotFoundException;
import com.autentia.academioboot.exception.StorageException;
import com.autentia.academioboot.model.Course;
import com.autentia.academioboot.service.course.CourseService;
import com.autentia.academioboot.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    private final StorageService storageService;

    @Autowired
    public CourseController(CourseService courseService, StorageService storageService) {
        this.courseService = courseService;
        this.storageService = storageService;
    }

    @GetMapping("/all")
    public List<Course> getAllCourse() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") int courseId) {
        return Optional.of(courseService.getById(courseId))
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
    }

    @PostMapping("/create")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.insert(course);
    }

    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        InputStream inputStream;
        String filename = file.getOriginalFilename();

        try {
            inputStream = file.getInputStream();
            storageService.store(inputStream, filename);
        } catch (IOException e) {
            throw new StorageException("Could not store file:" + filename);
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}
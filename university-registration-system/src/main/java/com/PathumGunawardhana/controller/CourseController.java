package com.PathumGunawardhana.controller;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.PathumGunawardhana.dao.CourseDAO;

@Controller
public class CourseController {
	@Autowired
    private CourseDAO courseDAO;

    private Map<Integer, String> courseMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Object Oriented Analysis and Design");
        map.put(2, "Server Side Web Programming");
        map.put(3, "Software Engineering");
        return map;
    }
    
    @RequestMapping("/courses")
    public String showCourses(HttpServletRequest request) {

        List<Map<String, Object>> courses = new ArrayList<>();

        for (Map.Entry<Integer, String> e : courseMap().entrySet()) {
            Map<String, Object> c = new HashMap<>();
            c.put("id", e.getKey());
            c.put("name", e.getValue());
            courses.add(c);
        }
        request.setAttribute("courses", courses);
        return "courses";
    }

    @RequestMapping(value = "/register/{courseId}", method = RequestMethod.POST)
    public String register(@PathVariable int courseId, HttpSession session) {

        List<Integer> selected =
                (List<Integer>) session.getAttribute("selectedCourses");

        if (selected == null) {
            selected = new ArrayList<>();
        }

        if (!selected.contains(courseId)) {
            selected.add(courseId);
        }

        session.setAttribute("selectedCourses", selected);

        return "redirect:/courses";
    }
    
    @RequestMapping("/finish")
    public String finish(HttpSession session, HttpServletRequest request) {

        Integer studentId = (Integer) session.getAttribute("studentId");

        List<Integer> selected =
                (List<Integer>) session.getAttribute("selectedCourses");

        if (selected == null) {
            selected = new ArrayList<>();
        }

        for (Integer courseId : selected) {
            courseDAO.registerCourse(studentId, courseId);
        }

        List<Map<String, Object>> previousCourses =
                courseDAO.getRegisteredCourses(studentId);

        List<Map<String, Object>> newCourses = new ArrayList<>();

        for (Integer id : selected) {
            Map<String, Object> c = new HashMap<>();

            if (id == 1) {
                c.put("name", "Object Oriented Analysis and Design");
                c.put("instructor", "Prof.Saluka Kodituwakku");
                c.put("credits", 3);
            }
            if (id == 2) {
                c.put("name", "Server Side Web Programming");
                c.put("instructor", "Mr.Isuru Madugalla");
                c.put("credits", 3);
            }
            if (id == 3) {
                c.put("name", "Software Engineering");
                c.put("instructor", "Dr.Ruwanthini Siyambalapitiya");
                c.put("credits", 2);
            }

            newCourses.add(c);
        }

        request.setAttribute("message", "Registration Successful!");
        request.setAttribute("previousCourses", previousCourses);
        request.setAttribute("newCourses", newCourses);

        session.removeAttribute("selectedCourses");

        return "success";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
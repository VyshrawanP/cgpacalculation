package com.example.grade;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CGPAController {

    @PostMapping("/calculate")
    public String calculateCGPA(@RequestParam List<String> grade,
                                @RequestParam List<Integer> credits,
                                Model model) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < grade.size(); i++) {
            String g = grade.get(i);
            int c = credits.get(i);

            int gradePoint = switch (g) {
                case "S" -> 10;
                case "A" -> 9;
                case "B" -> 8;
                case "C" -> 7;
                case "D" -> 6;
                case "E" -> 5;
                case "F", "N" -> 0; // F means fail, N is absent
                default -> 0;
            };

            totalPoints += gradePoint * c;
            totalCredits += c;
        }

        double cgpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
        model.addAttribute("cgpa", String.format("%.2f", cgpa));
        return "result";  // Renders result.html
    }
}

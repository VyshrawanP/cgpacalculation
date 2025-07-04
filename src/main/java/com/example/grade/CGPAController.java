package com.example.grade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class CGPAController{

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
        model.addAttribute("grades", grade);
         model.addAttribute("credits", credits);
        return "index";  // Renders result.html
    }


         @GetMapping("/calculates")
        public String showCalculatesPage() {
        return "calculates";  // renders calculates.html
        }



    @PostMapping("/calculates")
public String calculateCGPAs(@RequestParam List<Double> gpa,@RequestParam List<Integer> credit,Model model) {
    
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < gpa.size(); i++) {
            double g = gpa.get(i);
            int c = credit.get(i);

            double gradePoint = g;

            totalPoints += gradePoint * c;
            totalCredits += c;
        }

        double cgpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
        model.addAttribute("cgpa", String.format("%.2f", cgpa));
        model.addAttribute("grades", gpa);
         model.addAttribute("credit", credit);
        return "calculates";  // Renders result.html
    
    }


    @GetMapping("/semcalculates")
public String showsemCalculatesPage() {
    return "semcalculates";  // renders calculates.html
}


 @PostMapping("/semcalculates")
public String calculateSemCGPA(
        @RequestParam(required = false) List<Double> gpa,
        @RequestParam(required = false) List<Integer> credi,
        Model model) {

    double totalPoints = 0;
    int totalCredits = 0;

    if (gpa != null && credi!= null) {
        int size = Math.min(gpa.size(), credi.size());
        for (int i = 0; i < size; i++) {
            Double g = gpa.get(i);
            Integer c = credi.get(i);

            if (g != null && c != null) {
                totalPoints += g * c;
                totalCredits += c;
            }
        }
    }

    double cgpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
 model.addAttribute("cgpa", String.format("%.2f", cgpa));
    model.addAttribute("grades", gpa);
    model.addAttribute("credit", credi);

    return "semcalculates"; // or your view name
}

        
    }





















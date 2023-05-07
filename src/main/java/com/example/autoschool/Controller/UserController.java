package com.example.autoschool.Controller;

import com.example.autoschool.Entity.Exam;
import com.example.autoschool.Entity.Program;
import com.example.autoschool.Entity.ProgramDescription;
import com.example.autoschool.Entity.User;
import com.example.autoschool.Repository.ExamRepository;
import com.example.autoschool.Repository.ProgramDescriptionRepository;
import com.example.autoschool.Repository.ProgramRepository;
import com.example.autoschool.UserService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    private final ProgramDescriptionRepository programDescriptionRepository;

    private final ProgramRepository programRepository;

    private final ExamRepository examRepository;

    @PostMapping("/update")
    public String update(Model model, Authentication authentication, String email,
                         @RequestParam String phone,
                         @RequestParam String firstname,
                         @RequestParam String lastname
    ) throws IOException {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setNumber(phone);

        userService.update(user);

        model.addAttribute("user", user);
        return "redirect:/user/personal";
    }

    @GetMapping("/personal")
    public String personal(Model model, Authentication authentication, String email) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);
        model.addAttribute("user", user);

        Iterable<Program> programs = programRepository.getAllByUserEmail(email);
        List<Exam> exams = examRepository.getExamByCategory(programRepository.getProgramProgramByUserEmail(email));
        System.out.println(programRepository.getProgramProgramByUserEmail(email));
        model.addAttribute("programs", programs);
        model.addAttribute("email", email);
        model.addAttribute("exams", exams);
        return "personal";
    }

    @PostMapping("/q")
    public String loginMember(
            @RequestParam String email

    ) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        System.out.println("Personal " + email);
        return "redirect:/personal";
    }

    @GetMapping("/course")
    public String course(Model model) {
        Iterable<ProgramDescription> programs = programDescriptionRepository.findAll();
        model.addAttribute("programs", programs);
        return "program";
    }

    @PostMapping("/course")
    public String coursePost(Model model,
                             @RequestParam String program,
                             String email) {
        ProgramDescription programDescription = programDescriptionRepository.getProgramDescriptionByProgram(program);//depositDes
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);

        Program program1 = new Program();
        program1.setProgram(program);
        program1.setProgramTerm(programDescription.getProgramTerm());
        program1.setPrice(programDescription.getPrice());
        program1.setUserEmail(email);


        programRepository.save(program1);

        return "redirect:/user/personal";
    }

}

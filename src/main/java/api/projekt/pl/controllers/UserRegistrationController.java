package api.projekt.pl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import api.projekt.pl.dto.UserCustomRegistrationDto;
import api.projekt.pl.models.UserCustom;
import api.projekt.pl.services.UserServiceCustom;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserServiceCustom userService;

    @ModelAttribute("userCustom")
    public UserCustomRegistrationDto userRegistrationDto() {
        return new UserCustomRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public ResponseEntity<String> registerUserAccount(@RequestBody @Valid UserCustomRegistrationDto userDto, 
                                      BindingResult result){
        UserCustom existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "Konto z takim mailem już istnieje");
        	return new ResponseEntity<String>("Konto z takim mailem już istnieje", HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()){
        	List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        	System.out.println(errors);
        	return new ResponseEntity<String>("Błąd przy rejestracji", HttpStatus.BAD_REQUEST);
        }

        userService.save(userDto);
        return new ResponseEntity<String>("Utworzono nowe konto", HttpStatus.CREATED);
    }

}

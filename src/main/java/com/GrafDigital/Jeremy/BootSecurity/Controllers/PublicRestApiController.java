package com.GrafDigital.Jeremy.BootSecurity.Controllers;

import com.GrafDigital.Jeremy.BootSecurity.Modele.AppUser;
import com.GrafDigital.Jeremy.BootSecurity.Repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@AllArgsConstructor // pour l'injection de AppUserRepository
public class PublicRestApiController {
    // Injectons le repository
    private AppUserRepository appUserRepository;
    public PublicRestApiController(){}

    @GetMapping("test1")
    public String test1(){
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2";
    }

    @GetMapping("users")
    public List<AppUser> users(){
        return appUserRepository.findAll();
    }
}

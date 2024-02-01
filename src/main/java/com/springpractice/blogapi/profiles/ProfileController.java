package com.springpractice.blogapi.profiles;

import com.springpractice.blogapi.dto.ProfileResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("")
    public List<ProfileResponseDTO> getProfile(){
        return profileService.getProfile();
    }

    @GetMapping("/{username}")
    public ProfileResponseDTO getProfileByUsername(@PathVariable String username){
        return profileService.getProfileByUsername(username);
    }
}

package com.springpractice.blogapi.profiles;

import com.springpractice.blogapi.dto.ProfileResponseDTO;
import com.springpractice.blogapi.users.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileResponseDTO> getProfile() {
        List<UserEntity> users = profileRepository.findAll();
        List<ProfileResponseDTO> profileResponseDTOs = new ArrayList<>();
        for(UserEntity user : users){
            ProfileResponseDTO profileResponseDTO = modelMapper.map(user, ProfileResponseDTO.class);
            profileResponseDTOs.add(profileResponseDTO);
        }
        return profileResponseDTOs;
    }

    public ProfileResponseDTO getProfileByUsername(String username) {
        UserEntity user = profileRepository.findByUsername(username);
        ProfileResponseDTO profileResponseDTO = modelMapper.map(user, ProfileResponseDTO.class);
        return profileResponseDTO;
    }
}

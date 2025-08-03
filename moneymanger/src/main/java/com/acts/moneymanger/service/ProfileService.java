package com.acts.moneymanger.service;

import com.acts.moneymanger.dto.ProfileDTO;
import com.acts.moneymanger.entity.ProfileEntity;
import com.acts.moneymanger.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;

    public ProfileDTO registerProfile(ProfileDTO profileDTO) {
       ProfileEntity  newProfile = toEntity(profileDTO);
       newProfile.setActivationToken(UUID.randomUUID().toString());
       newProfile = profileRepository.save(newProfile);
       //Send activation email
        String activationLink = "http://localhost:8080/activate?token = "+ newProfile.getActivationToken();
        String subject = "Activate your Money Manager account";
        String body = "Click the link to activate your account: " + activationLink;
        emailService.sendEmail(newProfile.getEmail(),subject,body);
       return toDTO(newProfile);
    }

    public ProfileEntity toEntity(ProfileDTO profileDTO){
        return ProfileEntity.builder()
                .id(profileDTO.getId())
                .fullName(profileDTO.getFullName())
                .email(profileDTO.getEmail())
                .password(profileDTO.getPassword())
                .profileImageUrl(profileDTO.getProfileImageUrl())
                .createdAt(profileDTO.getCreatedAt())
                .updatedAt(profileDTO.getUpdatedAt())
                .build();
    }

    public ProfileDTO toDTO(ProfileEntity profileEntity) {
        return ProfileDTO.builder()
                .id(profileEntity.getId())
                .fullName(profileEntity.getFullName())
                .email(profileEntity.getEmail())
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .createdAt(profileEntity.getCreatedAt())
                .updatedAt(profileEntity.getUpdatedAt())
                .build();
    }

    public boolean activateProfile(String activationToken) {
        return profileRepository.findByActivationToken(activationToken)
                .map(profile -> {
                    profile.setIsActive(true);
                    profileRepository.save(profile);
                    return true;
                })
                .orElse(false);
    }


}

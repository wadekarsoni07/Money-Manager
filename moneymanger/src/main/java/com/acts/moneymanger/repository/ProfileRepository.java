package com.acts.moneymanger.repository;

import com.acts.moneymanger.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository <ProfileEntity, Long> {

    //select * from tbl_profile where email = ?
    Optional<ProfileEntity> findByEmail(String email);

    //select * from tbl_profile where activation_token = ?
    Optional<ProfileEntity>findByActivationToken(String token);
}

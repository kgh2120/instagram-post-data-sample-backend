package com.kk.instagrampostsamplebackend.repository;

import com.kk.instagrampostsamplebackend.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, String> {
}

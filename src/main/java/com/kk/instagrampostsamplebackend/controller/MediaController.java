package com.kk.instagrampostsamplebackend.controller;

import com.kk.instagrampostsamplebackend.dto.PhotoRetrieveResponse;
import com.kk.instagrampostsamplebackend.service.MediaRetrieveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/photos")
@RestController
public class MediaController {


    private final MediaRetrieveService mediaRetrieveService;


    @GetMapping
    public ResponseEntity<List<PhotoRetrieveResponse>> retrieveInstagramMedia(){
        return ResponseEntity.ok(mediaRetrieveService.retrieveMedia());
    }


}

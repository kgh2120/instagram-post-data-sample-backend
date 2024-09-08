package com.kk.instagrampostsamplebackend.service;

import com.kk.instagrampostsamplebackend.entity.MediaResponse;
import com.kk.instagrampostsamplebackend.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MediaRetrieveService {

    @Value("${insta.token}")
    private String accessToken;
    private final MediaRepository mediaRepository;


    public static final String MEDIA_RETRIEVE_BASE_URL = "https://graph.instagram.com/me/media?";



    public void retrieveMedia(){


        RestClient restClient = RestClient.create();

        final String fields = "fields=id,media_url,media_type";

        StringBuilder sb = new StringBuilder(MEDIA_RETRIEVE_BASE_URL);
        String uri = sb.append(fields).append("&")
                .append("access_token=").append(accessToken)
                .append("&")
                .append("limit=20")
                .toString();

        // TODO limit 개수 줄이고 DB에 있으면 저장하고 아님 말고 처음엔 좀 손해같긴 한데, 한두번 하고나면, 네트워크 IO 줄일 수 있어서 좋을지도? 적당히 설정해보자.

        ResponseEntity<MediaResponse> entity = restClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(MediaResponse.class);

        MediaResponse body = entity.getBody();


        for(MediaResponse.MediaData md : body.getData()){
            if(mediaRepository.existsById(md.getId())){
                return;
            }
            mediaRepository.save(md.toEntity());
        }


        while (body.getPaging() != null && body.getPaging().getNext() != null) {
            String next = body.getPaging().getNext();
            MediaResponse bb = restClient.get()
                    .uri(next)
                    .retrieve()
                    .toEntity(MediaResponse.class)
                    .getBody();
            body = bb;

            for(MediaResponse.MediaData md : body.getData()){
                if(mediaRepository.existsById(md.getId())){
                    return;
                }
                mediaRepository.save(md.toEntity());
            }
        }

    }


}

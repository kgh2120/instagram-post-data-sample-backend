package com.kk.instagrampostsamplebackend;

import com.kk.instagrampostsamplebackend.entity.MediaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class InstagramPostSampleBackendApplicationTests {

    @Autowired
    Environment env;

    @Test
    void contextLoads() {
    }


    public static final String MEDIA_RETRIEVE_BASE_URL = "https://graph.instagram.com/me/media?";


    @Test
    public void retrieveMedia(){


        final String accessToken = env.getProperty("insta.token");

        RestClient restClient = RestClient.create();


        final String fields = "fields=id,media_url,media_type";

        StringBuilder sb = new StringBuilder(MEDIA_RETRIEVE_BASE_URL);
        String uri = sb.append(fields).append("&")
                .append("access_token=").append(accessToken)
                .append("&")
                .append("limit=5")
                .toString();


        ResponseEntity<MediaResponse> entity = restClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(MediaResponse.class);

        MediaResponse body = entity.getBody();



        assert body != null;
        List<MediaResponse.MediaData> datas = new ArrayList<>(body.getData());

        while (body.getPaging() != null && body.getPaging().getNext() != null) {
            String next = body.getPaging().getNext();
            MediaResponse bb = restClient.get()
                    .uri(next)
                    .retrieve()
                    .toEntity(MediaResponse.class)
                    .getBody();
            assert bb != null;
            datas.addAll(bb.getData());
            body = bb;
        }

        assertThat(datas.size()).isEqualTo(28); // 테스트 계정의 게시글 수랑 동일한지 체크해주시요

    }

}

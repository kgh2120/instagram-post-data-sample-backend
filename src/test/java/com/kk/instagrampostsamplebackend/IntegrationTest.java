package com.kk.instagrampostsamplebackend;

import com.kk.instagrampostsamplebackend.service.MediaRetrieveService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 테스트를 할 때에도 insta.token=${token}에 해당하는 환경변수를 넣어줘야 합니다.
 * 넣는 방법은 다양하지만 이 예제를 작성할 때엔 따로 ignore 처리하고 싶지 않아서 ide에서 설정해줬습니다.
 * 새로운 설정 파일을 작성하거나 @SpringBootTest(properties = {"insta.token=example"})와 같이 설정해주세요
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    MediaRetrieveService mediaRetrieveService;

    @LocalServerPort
    int port;

    @Test
    void retrieveMediaTest(){

        mediaRetrieveService.retrieveFromInstagram();

        RestClient client = RestClient.create();

        List result = client.get()
                .uri("http://localhost:"+ port+"/photos")
                .retrieve()
                .body(List.class);

        assertThat(result.size()).isEqualTo(28); // isEqualTo 숫자는 자신이 올린 사진의 숫자만큼 설정하세요.
        System.out.println(result);


    }
}

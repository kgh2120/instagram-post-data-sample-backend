package com.kk.instagrampostsamplebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 인스타그램 Media에 대응되는 클래스.
 * 예시를 위한 PJT기 때문에, 간단하게 개발함.
 * 주어진 필드인 id, mediaUrl, mediaType외에도 개발자가 원하는 정보를 리턴한다면 정보를 획들할 수 있음.
 * ID의 경우 테스트 과정에서 17자리의 숫자가 나왔음. Long으로 해도 좋을 듯 싶은데, 잘 모르겠어서 String으로 정해놨다.
 * @Author : kgh2120
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Media {

    @Id
    private String id;
    private String mediaUrl;
    private String mediaType;

}

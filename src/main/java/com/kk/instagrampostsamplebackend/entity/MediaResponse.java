package com.kk.instagrampostsamplebackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 딱 인스타 API에서 긁으면 이렇게 나옵니다.
 * 페이징에서 next가 존재하지 않으면 더 조회하지 않으면 됩니다.
 * 만약 인스타 계정에서 데이터가 단 하나도 없다면 paging이 넘어오지 않습니다.
 * @Author: kgh2120
 */
@Data
public class MediaResponse {

    private List<MediaData> data;
    private Paging paging;



    @Data
    public static class MediaData{
        private String id;
        private String media_url;
        private String media_type;

        public Media toEntity(){
            return new Media(id, media_url, media_type);
        }
    }

    @Data
    public static class Paging {

        private Cursors cursors;
        private String next;


        @Data
        public static class Cursors{
            private String before;
            private String after;
        }
    }
}

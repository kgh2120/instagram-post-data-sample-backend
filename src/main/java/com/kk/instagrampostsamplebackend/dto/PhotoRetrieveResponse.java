package com.kk.instagrampostsamplebackend.dto;

import com.kk.instagrampostsamplebackend.entity.Media;
import lombok.Data;

@Data
public class PhotoRetrieveResponse {

    private String id;
    private String mediaUrl;

    public static PhotoRetrieveResponse fromEntity(Media media){

        PhotoRetrieveResponse photoRetrieveResponse = new PhotoRetrieveResponse();
        photoRetrieveResponse.id = media.getId();
        photoRetrieveResponse.mediaUrl = media.getMediaUrl();
        return photoRetrieveResponse;
    }
}

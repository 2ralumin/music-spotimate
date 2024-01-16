package kodong.spotimate.music.album.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kodong.spotimate.music.album.dto.response.AlbumDetailResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumService {

    //헤더 생성
    public HttpHeaders makeHeader(String accessToken){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", accessToken);
        httpHeaders.add("Host", "api.spotify.com");
        httpHeaders.add("Content-type", "application/json");
        return httpHeaders;
    }
    public AlbumDetailResponse getAlbumDetail(String id, HttpHeaders httpHeaders) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "https://api.spotify.com/v1/albums/" + id,
                HttpMethod.GET,
                requestEntity,
                Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonEntity = objectMapper.writeValueAsString(responseEntity.getBody());
        AlbumDetailResponse response = objectMapper.readValue(jsonEntity, AlbumDetailResponse.class);

        return response;

    }
}

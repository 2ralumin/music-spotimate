package kodong.spotimate.music.album.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kodong.spotimate.music.album.dto.response.AlbumDetailResponse;
import kodong.spotimate.music.album.dto.response.TracksListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
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
    public AlbumDetailResponse getAlbumDetail(String id, String market, HttpHeaders httpHeaders) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        String uriComponents = UriComponentsBuilder
                .fromHttpUrl("https://api.spotify.com/v1/albums/{id}")
                .queryParam("market", market)
                .buildAndExpand(id) // {id} 부분을 확장
                .toUriString();

        log.info(uriComponents);


        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                uriComponents,
                HttpMethod.GET,
                requestEntity,
                Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonEntity = objectMapper.writeValueAsString(responseEntity.getBody());
        AlbumDetailResponse response = objectMapper.readValue(jsonEntity, AlbumDetailResponse.class);

        return response;

    }

    public TracksListResponse getTracks(String id, String market, int limit, int offset, HttpHeaders httpHeaders) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        String uriComponents = UriComponentsBuilder
                .fromHttpUrl("https://api.spotify.com/v1")
                .path("/albums/{id}/tracks")
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .buildAndExpand(id)
                .toUriString();
        log.info(uriComponents);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                uriComponents,
                HttpMethod.GET,
                requestEntity,
                Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonEntity = objectMapper.writeValueAsString(responseEntity.getBody());
        TracksListResponse response = objectMapper.readValue(jsonEntity, TracksListResponse.class);
        return response;
    }
}

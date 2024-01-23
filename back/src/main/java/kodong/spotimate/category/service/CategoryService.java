package kodong.spotimate.category.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kodong.spotimate.category.dto.response.category.CategoryDto;
import kodong.spotimate.category.dto.response.category.CategoryListDto;
import kodong.spotimate.category.dto.response.playlist.CategoryPlayListDto;
import kodong.spotimate.category.dto.response.playlist.PlayListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class CategoryService {

    public CategoryListDto getCategory(String country,
                                   int limit,
                                   int offset,
                                   HttpHeaders httpHeaders) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        String uriComponents = UriComponentsBuilder
                .fromHttpUrl("https://api.spotify.com/v1/browse/categories")
                .queryParam("country", country)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .toUriString();


        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                uriComponents,
                HttpMethod.GET,
                requestEntity,
                Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonEntity = objectMapper.writeValueAsString(responseEntity.getBody());
        CategoryDto response = objectMapper.readValue(jsonEntity, CategoryDto.class);

        log.info(jsonEntity);
        log.info(response.toString());
        return response.getCategories();

    }

    public CategoryPlayListDto getCategoryPlayList(String categoryId,
                                                   String country,
                                                   int limit,
                                                   int offset,
                                                   HttpHeaders httpHeaders) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        String uriComponents = UriComponentsBuilder
                .fromHttpUrl("https://api.spotify.com/v1/browse/categories/{category_id}/playlists")
                .queryParam("country", country)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .buildAndExpand(categoryId)
                .toUriString();


        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                uriComponents,
                HttpMethod.GET,
                requestEntity,
                Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonEntity = objectMapper.writeValueAsString(responseEntity.getBody());
        PlayListDto response = objectMapper.readValue(jsonEntity, PlayListDto.class);

        log.info(jsonEntity);
        return response.getPlaylists();

    }
}

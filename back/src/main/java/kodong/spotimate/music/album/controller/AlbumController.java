package kodong.spotimate.music.album.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import kodong.spotimate.music.album.dto.response.AlbumDetailResponse;
import kodong.spotimate.music.album.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/api/music/album/{id}")
    public ResponseEntity<AlbumDetailResponse> getAlbumDetail(@PathVariable String id, HttpServletRequest httpServletRequest) throws JsonProcessingException {

        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = albumService.makeHeader(accessToken);
        AlbumDetailResponse response = albumService.getAlbumDetail(id, httpHeaders);

        return ResponseEntity.ok().body(response);
    }
}

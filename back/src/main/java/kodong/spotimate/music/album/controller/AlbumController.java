package kodong.spotimate.music.album.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import kodong.spotimate.global.header.HeaderService;
import kodong.spotimate.music.album.dto.response.AlbumDetailResponse;
import kodong.spotimate.music.album.dto.response.TracksListResponse;
import kodong.spotimate.music.album.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlbumController {
    private final AlbumService albumService;
    private final HeaderService headerService;

    @GetMapping("/api/music/album/{id}")
    public ResponseEntity<AlbumDetailResponse> getAlbumDetail(@PathVariable String id,
                                                              @RequestParam String market,
                                                              HttpServletRequest httpServletRequest) throws JsonProcessingException {

        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = headerService.makeHeader(accessToken);
        AlbumDetailResponse response = albumService.getAlbumDetail(id, market,httpHeaders);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/music/album/{id}/tracks")
    public ResponseEntity<TracksListResponse> getAlbumTracks(@PathVariable String id,
                                                             @RequestParam String market,
                                                             @RequestParam int limit,
                                                             @RequestParam int offset,
                                                             HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = headerService.makeHeader(accessToken);
        TracksListResponse response = albumService.getTracks(id, market, limit, offset, httpHeaders);
        return ResponseEntity.ok().body(response);
    }
}

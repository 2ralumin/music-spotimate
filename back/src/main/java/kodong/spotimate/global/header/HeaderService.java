package kodong.spotimate.global.header;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HeaderService {

    //헤더 생성
    public HttpHeaders makeHeader(String accessToken){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", accessToken);
        httpHeaders.add("Host", "api.spotify.com");
        httpHeaders.add("Content-type", "application/json");
        return httpHeaders;
    }
}

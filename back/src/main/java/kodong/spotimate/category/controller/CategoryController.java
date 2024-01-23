package kodong.spotimate.category.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import kodong.spotimate.category.dto.response.category.CategoryListDto;
import kodong.spotimate.category.dto.response.playlist.CategoryPlayListDto;
import kodong.spotimate.category.service.CategoryService;
import kodong.spotimate.global.header.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final HeaderService headerService;
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<CategoryListDto> getCategory(@RequestParam String country,
                                                       @RequestParam int limit,
                                                       @RequestParam int offset,
                                                       HttpServletRequest httpServletRequest) throws JsonProcessingException {

        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = headerService.makeHeader(accessToken);

        CategoryListDto response = categoryService.getCategory(country, limit, offset, httpHeaders);
        return ResponseEntity.ok().body(response);

    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryPlayListDto> getCategoryPlayList(@PathVariable String categoryId,
                                                                   @RequestParam String country,
                                                                   @RequestParam int limit,
                                                                   @RequestParam int offset,
                                                                   HttpServletRequest httpServletRequest) throws JsonProcessingException {

        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = headerService.makeHeader(accessToken);

        CategoryPlayListDto response = categoryService.getCategoryPlayList(categoryId,country, limit, offset, httpHeaders);
        return ResponseEntity.ok().body(response);
    }
}

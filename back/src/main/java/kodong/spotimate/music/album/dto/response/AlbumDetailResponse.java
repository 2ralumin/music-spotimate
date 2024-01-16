package kodong.spotimate.music.album.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDetailResponse {
    private String id;
    private String name;
    private List<Images> images;
    private String release_date;
    private int total_tracks;
    private List<String> genres;

    private TracksListResponse tracks;

}

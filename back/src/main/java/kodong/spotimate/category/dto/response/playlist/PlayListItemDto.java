package kodong.spotimate.category.dto.response.playlist;

import kodong.spotimate.global.dto.Images;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListItemDto {
    private String id;
    private String name;
    private String description;
    private List<Images> images;
}

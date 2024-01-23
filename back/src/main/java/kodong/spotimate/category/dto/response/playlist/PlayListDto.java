package kodong.spotimate.category.dto.response.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import kodong.spotimate.category.dto.response.category.CategoryListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDto {
    @JsonProperty("playlists")
    private CategoryPlayListDto playlists;
}

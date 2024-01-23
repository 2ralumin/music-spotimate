package kodong.spotimate.category.dto.response.playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPlayListDto {
    private int total;
    private int limit;
    private int offset;
    private List<PlayListItemDto> items;
}

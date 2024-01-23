package kodong.spotimate.category.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItemDto {
    private String id;
    private String name;
}

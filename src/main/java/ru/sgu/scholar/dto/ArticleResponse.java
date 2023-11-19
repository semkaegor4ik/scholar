package ru.sgu.scholar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Citation citation;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Citation {
        @JsonProperty("total_citations")
        private TotalCitations totalCitations;
        private String link;
        private String description;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotalCitations {
        @JsonProperty("cited_by")
        private CitedBy citedBy;
        private List<CitateTable> table;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CitedBy {
        private int total;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CitateTable {
        int year;
        int citations;
    }
}

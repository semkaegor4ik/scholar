package ru.sgu.scholar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    @JsonProperty("cited_by")
    private CitedBy citedBy;
    private List<Article> articles;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CitedBy {
        private List<TableObject> table;
        private List<GraphData> graph;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GraphData {
        private int year;
        private int citations;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Article {
        private String link;
        private String title;
        @JsonProperty("citation_id")
        private String citationId;
        private String year;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TableObject {
        private CitationsObject citations;
        @JsonProperty("h_index")
        private HIndexObject hIndex;
        @JsonProperty("i10_index")
        private ITenObject i10Object;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CitationsObject {
        private int all;
        @JsonProperty("since_2018")
        private int sinceYear;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class HIndexObject {
        private int all;
        @JsonProperty("since_2018")
        private int sinceYear;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ITenObject {
        private int all;
        @JsonProperty("since_2018")
        private int sinceYear;
    }
}

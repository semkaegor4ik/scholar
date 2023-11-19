package ru.sgu.scholar.convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import ru.sgu.scholar.dto.ArticleResponse;

@Converter(autoApply = true)
public class TotalCitationsConvertorJson implements AttributeConverter<ArticleResponse.TotalCitations, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ArticleResponse.TotalCitations meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public ArticleResponse.TotalCitations convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, ArticleResponse.TotalCitations.class);
        } catch (IOException ex) {
            return null;
        }
    }
}

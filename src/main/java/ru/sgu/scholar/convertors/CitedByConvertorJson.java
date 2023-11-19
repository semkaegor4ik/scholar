package ru.sgu.scholar.convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import ru.sgu.scholar.dto.AuthorResponse;

@Converter(autoApply = true)
public class CitedByConvertorJson implements AttributeConverter<AuthorResponse.CitedBy, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AuthorResponse.CitedBy meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public AuthorResponse.CitedBy convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, AuthorResponse.CitedBy.class);
        } catch (IOException ex) {
            return null;
        }
    }
}

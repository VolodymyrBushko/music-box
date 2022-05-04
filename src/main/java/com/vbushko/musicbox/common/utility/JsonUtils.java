package com.vbushko.musicbox.common.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String fromValueToJson(final Object value) {
        return objectMapper.writeValueAsString(value);
    }

    @SneakyThrows
    public static <T> T fromJsonToValue(final String json, final Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}

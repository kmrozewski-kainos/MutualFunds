package com.mutualfunds.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSONParser {

    private static final String JSON_DELIMITER = "\n";

    private JSONParser() {
    }

    public static <T> T fromJSON(String fileName, Class className, TypeReference<T> type) {
        try {
            return new ObjectMapper().readValue(readJSONAsString(className, fileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readJSONAsString(Class className, String fileName) {
        return new BufferedReader(new InputStreamReader(className.getClassLoader().getResourceAsStream(fileName)))
            .lines()
            .collect(Collectors.joining(JSON_DELIMITER));
    }
}

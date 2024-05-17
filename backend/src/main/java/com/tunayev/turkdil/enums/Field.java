package com.tunayev.turkdil.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Field {
    /**
     * Template fields are used to create a post. They are used to fill the post body in json format.
     * A field is an object with name, type and value(if in post). Types are: text, image, audio, geolocation, video
     */

    TEXT,
    IMAGE,
    AUDIO,
    GEOLOCATION,
    VIDEO;

    /**
     * @return Field
     */

    public static Field fromString(String field) {
        return Field.valueOf(field.toUpperCase());
    }

    /**
     * @return String
     */
    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * @return List<String>
     */
    public static List<String> getFieldNames() {
        return Arrays.stream(Field.values())
                .map(Field::toString)
                .collect(Collectors.toList());
    }

    }

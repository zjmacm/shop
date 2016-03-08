package com.shop.domain;


import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by ldz on 18/12/14.
 * A custom date serializer for Jackson JSON which will serialize a date in the
 */
public class ExtDateSerializer extends JsonSerializer<Date> {

    private final static String FORMAT = "yyyy-MM-dd";

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        DateFormat formatter = new SimpleDateFormat(FORMAT);
        String formattedDate = formatter.format(value);

        gen.writeString(formattedDate);
    }
}

package com.shop.domain;


import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ldz on 18/12/14.
 * Deserializer to deal with dates coming in via JSON. It recognizes dates
 */
public class ExtDateDeserializer extends JsonDeserializer<Date> {

    private final Logger logger = LoggerFactory.getLogger(ExtDateSerializer.class);

    // the date format to use
    private final static String FORMAT = "yyyy-MM-dd";

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        String val = jsonParser.getText();

        DateFormat formatter = new SimpleDateFormat(FORMAT);
        Date date = null;
        try {
            date = formatter.parse(val);

        } catch(ParseException pex) {
            logger.error("Failed to parse date " + val, pex);
        }

        return date;
    }
}

package com.shop.domain;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ldz on 18/12/14.
 * 封装要返回的数据
 * serialized by Jackson JSON in the JSON format as long as you annotate
 * the controller method return value as @ResponseBody.
 */
public class ExtData extends ExtResponse {

    @JsonProperty("data")
    private final List<Object> data = new ArrayList<Object>();

    @JsonProperty("total")
    private int total = 0;

    /**
     * Add a single Object to the data array
     * @param item the Object to add to the array
     */
    public void add(Object item) {

        if(item == null) return;

        if(item instanceof Collection) {

            for(Object object : (Collection) item) {
                data.add(object);
                total++;
            }

        } else {
            data.add(item);
            total++;
        }
    }
}

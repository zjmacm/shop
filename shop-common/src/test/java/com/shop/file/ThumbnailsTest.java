package com.shop.file;

import com.shop.image.ImageProcess;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by yj on 14-11-23.
 */
public class ThumbnailsTest {

    @Test
    public void createTest() throws IOException {
        ImageProcess.createThumbnail(
                new File("/home/yj/Pictures/git.jpg"),
                60,60,new File("/home/yj/Pictures/thum.jpg"));
    }

    @Test
    public void createWithFormatTest() throws IOException {
        ImageProcess.createThumbnail(
                new File("/home/yj/Pictures/git.jpg"),
                60,60,new File("/home/yj/Pictures/thumb"),"png");
    }

}

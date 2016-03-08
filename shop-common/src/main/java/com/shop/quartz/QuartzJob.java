package com.shop.quartz;

import java.util.Date;

/**
 * Created by qf on 2014/11/22.
 */
public class QuartzJob {
    private static int counter = 0;
    public void execute()  {
        long ms = System.currentTimeMillis();
        System.out.println("\t\t" + new Date(ms));
        System.out.println("(" + counter++ + ")");
    }


}

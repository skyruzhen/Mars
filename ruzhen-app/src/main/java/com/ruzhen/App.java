package com.ruzhen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
       LOGGER.info("Hello world! {}", "ss");
       LOGGER.error("ERROR'S color is ! {}", "ss");
    }

}

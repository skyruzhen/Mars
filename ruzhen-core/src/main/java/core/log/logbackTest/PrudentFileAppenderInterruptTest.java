package core.log.logbackTest; /**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.FileAppender;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import ch.qos.logback.core.encoder.EchoEncoder;
import ch.qos.logback.core.status.OnConsoleStatusListener;

public class PrudentFileAppenderInterruptTest
{
    FileAppender<Object> fa = new FileAppender<Object>();
    Context context = new ContextBase();
    String outputDirStr =  "D:\\logs\\logback\\";
    String logfileStr = outputDirStr + "TEST_WARN-2018-11-12.log";

    @Before
    public void setUp() throws InterruptedException {
        context.getStatusManager().add(new OnConsoleStatusListener());

        File outputDir = new File(outputDirStr);
        outputDir.mkdirs();

        fa.setContext(context);
        fa.setName( "FILE" );
        fa.setPrudent(true);
        fa.setEncoder(new EchoEncoder<Object>());
       // fa.setFile(logfileStr);
        fa.start();
    }

    @Test
    public void smoke() throws InterruptedException, IOException {
        Runner runner = new Runner(fa);
        Thread t = new Thread(runner);
        t.start();

        runner.latch.await();

        fa.doAppend( "hello not interrupted" );

        FileReader fr = new FileReader(logfileStr);
        BufferedReader br = new BufferedReader(fr);

        int totalLines = 0;
        while (br.readLine() != null)
        {
            totalLines++; // For this test I don't really care about content
        }
        fr.close();
        br.close();

        assertTrue( "There should be either 1 or 2 logged lines but there were " + totalLines + ".", totalLines == 1 || totalLines == 2 );
    }

    class Runner implements Runnable {
        FileAppender<Object> fa;
        CountDownLatch latch = new CountDownLatch(1); // Just to make sure this is executed before we log in the test method

        Runner(FileAppender<Object> fa) {
            this.fa = fa;
        }

        public void run() {
            Thread.currentThread().interrupt();
            fa.doAppend("hello interrupted");
            latch.countDown();
        }
    }
}
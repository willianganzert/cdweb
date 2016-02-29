package br.com.cdweb.server.timer;

import java.io.IOException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BroadcastJob implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {
    	try {
			new BroadcartNetwork().run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(String.format("[%s] Executing Job", new Date().toString()));
        System.out.println(String.format("[%s] Job", this));

    }
}

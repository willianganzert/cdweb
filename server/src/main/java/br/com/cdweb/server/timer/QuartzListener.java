package br.com.cdweb.server.timer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class QuartzListener extends QuartzInitializerListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	System.out.println("Iniciando servico de agendamento Broadcast");
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(BroadcastJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple").withSchedule(
                    CronScheduleBuilder.cronSchedule("0/10 * * * * ? *")).startNow().build();
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("Executando serviço de Broadcast");
            scheduler.start();
        } catch (Exception e) {
            ctx.log("There was an error scheduling the job.", e);
        }
    }

}
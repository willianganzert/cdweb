/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cdweb.dispositivos.configuracoes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import br.com.cdweb.dispositivos.processos.RegistraDispositivo;

/**
 *
 * @author Willian
 */
public class DispositivosCDWEB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        if(args.length>0){
            Configuracoes.INSTANCE.setName(args[0]);            
        }
        
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new RegistraDispositivo(), 5,5, TimeUnit.SECONDS);
        
        
//        Thread.sleep(10000);
    }
    
}

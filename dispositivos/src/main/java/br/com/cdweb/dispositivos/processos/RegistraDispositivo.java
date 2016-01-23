/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cdweb.dispositivos.processos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
import br.com.cdweb.dispositivos.configuracoes.DispositivosCDWEB;

/**
 *
 * @author Willian
 */
public class RegistraDispositivo implements Runnable {

    private String myNetworkIP = "";

    @Override
    public void run() {        
        System.out.println("['"+myNetworkIP+"'] - verificando");
        try {
            if (atualizaIPs()) {
                enviaIpServidor(Configuracoes.INSTANCE.getName(), myNetworkIP);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(DispositivosCDWEB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    private boolean atualizaIPs() throws UnknownHostException {
        String ipLocalAtual = InetAddress.getLocalHost().getHostAddress();
        if (ipLocalAtual != null && !ipLocalAtual.equals(myNetworkIP)) {
            myNetworkIP = ipLocalAtual;
            return true;
        }
        return false;
    }

    private void enviaIpServidor(String name, String myNetworkIP) {
        System.out.println("Nome:" + name + " | IP:" + myNetworkIP);
    }
}

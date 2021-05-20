/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import services.HL7Services;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import java.util.logging.Level;
import java.util.logging.Logger; 
import services.RGVReceiverApplication;

/**
 *
 * @author davidaastrom
 */
public class PrescriptionReciever {
    private static HL7Service server;
    
    public static void main(String args[]){
        int port = 5647;
        HapiContext ctx = new DefaultHapiContext();
        server = ctx.newServer(port, false);
        System.out.println("hallå");
        ReceivingApplication<Message> handler = new RGVReceiverApplication();
        server.registerApplication("RGV", "O01", handler);
        
        try {
            System.out.println("hej"); 
            server.startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }   
    
}

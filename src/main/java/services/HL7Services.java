/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author arthu
 */
public class HL7Services {
    
    public void ReceivingPrescription(int port, boolean useTls){
        
        HapiContext context = new DefaultHapiContext();
        HL7Service svr = context.newServer(port, useTls);
        
        ReceivingApplication<Message> handler = new RGVReceiverApplication();
        svr.registerApplication("RGV", "O01", handler);
        
        try {
            svr.startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

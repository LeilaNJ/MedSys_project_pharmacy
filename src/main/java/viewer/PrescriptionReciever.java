/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import services.HL7Services;

/**
 *
 * @author davidaastrom
 */
public class PrescriptionReciever {
    private static HL7Services hl7services;
    
    public static void main(String args[]){
        hl7services = new HL7Services(); 
        hl7services.ReceivingPrescription(5647, false);
      
    }   
    
}

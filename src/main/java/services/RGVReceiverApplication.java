/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controller.DoctorJpaController;
import controller.MedicineJpaController;
import controller.PatientJpaController;
import model.Doctor;
import model.Medicine;
import model.Patient;
import model.Prescription;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.RGV_O01;
import ca.uhn.hl7v2.model.v23.segment.ORC;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.RXG;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author arthu
 */
public class RGVReceiverApplication implements ReceivingApplication<Message> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmacy");
    private final PatientJpaController patientCtrl = new PatientJpaController(emf);
    private final DoctorJpaController doctorCtrl = new DoctorJpaController(emf);
    private final MedicineJpaController medicineCtrl = new MedicineJpaController(emf);
    
    public RGVReceiverApplication() {
    }

    @Override
    public Message processMessage(Message t, Map<String, Object> map) throws ReceivingApplicationException, HL7Exception {
        
        // Recovery RGV_O01
        RGV_O01 rgv = (RGV_O01) t;
        
        // Recovery PID Segment
        PID pid = rgv.getPATIENT().getPID();
        String idpatient = pid.getPatientIDInternalID(0).getID().getValue();
        
        // Recovery ORC Order
                
        ORC orc = rgv.getPATIENT().getORDER().getORC();
        String idprescription = orc.getOrc1_OrderControl().getValue();
        Date datepresc = orc.getOrc9_DateTimeOfTransaction().getTimeOfAnEvent().getValueAsDate();
        String iddoctor = orc.getOrc10_EnteredBy().getIDNumber().getValue();
        String signature = orc.getOrc10_EnteredBy().getGivenName().getValue();
        Date deliverydate =orc.getOrc15_OrderEffectiveDateTime().getTimeOfAnEvent().getValueAsDate();
        
        // ORDER GIVE
        
        RXG rxg = rgv.getPATIENT().getORDER().getGIVE().getRXG();
        String idmedicine = rxg.getGiveCode().getIdentifier().getValue();
        String duration = rxg.getQuantityTiming().getDuration().getValue();
        String intvadmin = rxg.getQuantityTiming().getInterval().getExplicitTimeInterval().getValue();
        String dosage = rxg.getQuantityTiming().getQuantity().getQuantity().getValue();
        String renewable = rxg.getQuantityTiming().getOrderSequencing().getCm_osd7_MaximumNumberOfRepeats().getValue();
        String adminform = rxg.getGiveDosageForm().getText().getValue();
        
        // Add the values in the DB
        
        Prescription prescription = new Prescription();
        
        Integer intidprescription = Integer.valueOf(idprescription);
        prescription.setIdprescription(intidprescription);
        
        Integer intidpatient = Integer.valueOf(idpatient);
        Patient pduplicate = patientCtrl.findPatient(intidpatient);
        if(pduplicate == null) {
            Patient patient = new Patient();
            patient.setIdpatient(intidpatient);
            prescription.setPatient(patient);
        }
        else{
            prescription.setPatient(pduplicate);
        }

        Integer intiddoctor = Integer.valueOf(iddoctor); 
        Doctor dduplicate = doctorCtrl.findDoctor(intiddoctor);
        if(dduplicate == null) {
            Doctor doctor = new Doctor();
            doctor.setIddoctor(intiddoctor);
            prescription.setDoctor(doctor);
        }
        else {
            prescription.setDoctor(dduplicate);
        }
        
        Integer intidmedicine = Integer.valueOf(idmedicine);
        Medicine mduplicate = medicineCtrl.findMedicine(intidmedicine);
        if(mduplicate == null) {
            Medicine medicine = new Medicine();
            medicine.setIdmedicine(intidmedicine);
            prescription.setMedicine(medicine);
        }
        else {
            prescription.setMedicine(mduplicate);
        }
        
        prescription.setSignature(signature);
        
        prescription.setDatePresc(datepresc);
        
        prescription.setDateDelivery(deliverydate);

        Integer intdosage = Integer.valueOf(dosage);
        prescription.setDosage(intdosage);
        
        prescription.setFreqAdministration(intvadmin);
        
        prescription.setDurationofTreatment(duration);
        
        prescription.setFormofAdministration(adminform);
        
        byte[] brenewable = renewable.getBytes();
        prescription.setRenewable(brenewable);
        
        //Final messsage
        
        String encodedMessage = new DefaultHapiContext().getPipeParser().encode(t);
        System.out.println("Received message:\n" + encodedMessage + "\n\n");
        // Now generate a simple acknowledgment message and return it
        try {
        	return t.generateACK();
        } catch (IOException e) {
            throw new HL7Exception(e);
        }
    }

    @Override
    public boolean canProcess(Message t) {
        return true;
    }   
}

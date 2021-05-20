/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidaastrom
 */
@Entity
@Table(name = "prescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p"),
    @NamedQuery(name = "Prescription.findByIdprescription", query = "SELECT p FROM Prescription p WHERE p.idprescription = :idprescription"),
    @NamedQuery(name = "Prescription.findByDatePresc", query = "SELECT p FROM Prescription p WHERE p.datePresc = :datePresc"),
    @NamedQuery(name = "Prescription.findByDateDelivery", query = "SELECT p FROM Prescription p WHERE p.dateDelivery = :dateDelivery"),
    @NamedQuery(name = "Prescription.findByDosage", query = "SELECT p FROM Prescription p WHERE p.dosage = :dosage"),
    @NamedQuery(name = "Prescription.findByFreqAdministration", query = "SELECT p FROM Prescription p WHERE p.freqAdministration = :freqAdministration"),
    @NamedQuery(name = "Prescription.findByDurationofTreatment", query = "SELECT p FROM Prescription p WHERE p.durationofTreatment = :durationofTreatment"),
    @NamedQuery(name = "Prescription.findByFormofAdministration", query = "SELECT p FROM Prescription p WHERE p.formofAdministration = :formofAdministration")})
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprescription")
    private Integer idprescription;
    @Basic(optional = false)
    @Lob
    @Column(name = "signature")
    private String signature;
    @Basic(optional = false)
    @Column(name = "DatePresc")
    @Temporal(TemporalType.DATE)
    private Date datePresc;
    @Basic(optional = false)
    @Column(name = "DateDelivery")
    @Temporal(TemporalType.DATE)
    private Date dateDelivery;
    @Basic(optional = false)
    @Column(name = "FreqAdministration")
    private String freqAdministration;
    @JoinColumn(name = "Patient", referencedColumnName = "idpatient")
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "Doctor", referencedColumnName = "iddoctor")
    @ManyToOne(optional = false)
    private Doctor doctor;
    @JoinColumn(name = "Medicine", referencedColumnName = "idmedicine")
    @ManyToOne(optional = false)
    private Medicine medicine;
    
     @Column(name = "Dosage")
    private Integer dosage;
    @Size(max = 255)
    @Column(name = "Duration_of_Treatment")
    private String durationofTreatment;
    @Size(max = 255)
    @Column(name = "Form_of_Administration")
    private String formofAdministration;
    @Lob
    @Column(name = "Renewable")
    private byte[] renewable;


    public Prescription() {
    }

    public Prescription(Integer idprescription) {
        this.idprescription = idprescription;
    }

    public Prescription(Integer idprescription, String signature, Date datePresc, Date dateDelivery, int dosage, String freqAdministration, String durationofTreatment, String formofAdministration, byte[] renewable) {
        this.idprescription = idprescription;
        this.signature = signature;
        this.datePresc = datePresc;
        this.dateDelivery = dateDelivery;
        this.dosage = dosage;
        this.freqAdministration = freqAdministration;
        this.durationofTreatment = durationofTreatment;
        this.formofAdministration = formofAdministration;
        this.renewable = renewable;
    }

    public Integer getIdprescription() {
        return idprescription;
    }

    public void setIdprescription(Integer idprescription) {
        this.idprescription = idprescription;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getDatePresc() {
        return datePresc;
    }

    public void setDatePresc(Date datePresc) {
        this.datePresc = datePresc;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }


    public String getFreqAdministration() {
        return freqAdministration;
    }

    public void setFreqAdministration(String freqAdministration) {
        this.freqAdministration = freqAdministration;
    }

    public String getDurationofTreatment() {
        return durationofTreatment;
    }

    public void setDurationofTreatment(String durationofTreatment) {
        this.durationofTreatment = durationofTreatment;
    }

    public String getFormofAdministration() {
        return formofAdministration;
    }

    public void setFormofAdministration(String formofAdministration) {
        this.formofAdministration = formofAdministration;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprescription != null ? idprescription.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.idprescription == null && other.idprescription != null) || (this.idprescription != null && !this.idprescription.equals(other.idprescription))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Prescription[ idprescription=" + idprescription + " ]";
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public byte[] getRenewable() {
        return renewable;
    }

    public void setRenewable(byte[] renewable) {
        this.renewable = renewable;
    }
    
}

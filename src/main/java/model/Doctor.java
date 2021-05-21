/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidaastrom
 */
@Entity
@Table(name = "doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findByIddoctor", query = "SELECT d FROM Doctor d WHERE d.iddoctor = :iddoctor"),
    @NamedQuery(name = "Doctor.findByINAMInbr", query = "SELECT d FROM Doctor d WHERE d.iNAMInbr = :iNAMInbr")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddoctor")
    private Integer iddoctor;
    @Column(name = "INAMInbr")
    private Integer iNAMInbr;
    @JoinColumn(name = "Person", referencedColumnName = "idperson")
    @ManyToOne(optional = false)
    private Person person;

    public Doctor() {
    }

    public Doctor(Integer iddoctor) {
        this.iddoctor = iddoctor;
    }

    public Integer getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(Integer iddoctor) {
        this.iddoctor = iddoctor;
    }

    public Integer getINAMInbr() {
        return iNAMInbr;
    }

    public void setINAMInbr(Integer iNAMInbr) {
        this.iNAMInbr = iNAMInbr;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddoctor != null ? iddoctor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.iddoctor == null && other.iddoctor != null) || (this.iddoctor != null && !this.iddoctor.equals(other.iddoctor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Doctor[ iddoctor=" + iddoctor + " ]";
    }
    
}

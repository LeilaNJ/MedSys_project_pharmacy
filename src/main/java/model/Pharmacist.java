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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "pharmacist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pharmacist.findAll", query = "SELECT p FROM Pharmacist p"),
    @NamedQuery(name = "Pharmacist.findByIdpharmacist", query = "SELECT p FROM Pharmacist p WHERE p.idpharmacist = :idpharmacist"),
    @NamedQuery(name = "Pharmacist.findByINAMInbr", query = "SELECT p FROM Pharmacist p WHERE p.iNAMInbr = :iNAMInbr")})
public class Pharmacist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpharmacist")
    private Integer idpharmacist;
    @Basic(optional = false)
    @Column(name = "INAMInbr")
    private int iNAMInbr;

    public Pharmacist() {
    }

    public Pharmacist(Integer idpharmacist) {
        this.idpharmacist = idpharmacist;
    }

    public Pharmacist(Integer idpharmacist, int iNAMInbr) {
        this.idpharmacist = idpharmacist;
        this.iNAMInbr = iNAMInbr;
    }

    public Integer getIdpharmacist() {
        return idpharmacist;
    }

    public void setIdpharmacist(Integer idpharmacist) {
        this.idpharmacist = idpharmacist;
    }

    public int getINAMInbr() {
        return iNAMInbr;
    }

    public void setINAMInbr(int iNAMInbr) {
        this.iNAMInbr = iNAMInbr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpharmacist != null ? idpharmacist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pharmacist)) {
            return false;
        }
        Pharmacist other = (Pharmacist) object;
        if ((this.idpharmacist == null && other.idpharmacist != null) || (this.idpharmacist != null && !this.idpharmacist.equals(other.idpharmacist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pharmacist[ idpharmacist=" + idpharmacist + " ]";
    }
    
}

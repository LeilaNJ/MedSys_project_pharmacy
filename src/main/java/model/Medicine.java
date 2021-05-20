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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidaastrom
 */
@Entity
@Table(name = "medicine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicine.findAll", query = "SELECT m FROM Medicine m"),
    @NamedQuery(name = "Medicine.findByIdmedicine", query = "SELECT m FROM Medicine m WHERE m.idmedicine = :idmedicine"),
    @NamedQuery(name = "Medicine.findByName", query = "SELECT m FROM Medicine m WHERE m.name = :name"),
    @NamedQuery(name = "Medicine.findByCompany", query = "SELECT m FROM Medicine m WHERE m.company = :company"),
    @NamedQuery(name = "Medicine.findByActivesubstance", query = "SELECT m FROM Medicine m WHERE m.activesubstance = :activesubstance")})
public class Medicine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedicine")
    private Integer idmedicine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Company")
    private String company;
    @Size(max = 255)
    @Column(name = "Activesubstance")
    private String activesubstance;

    public Medicine() {
    }

    public Medicine(Integer idmedicine) {
        this.idmedicine = idmedicine;
    }

    public Medicine(Integer idmedicine, String name) {
        this.idmedicine = idmedicine;
        this.name = name;
    }

    public Integer getIdmedicine() {
        return idmedicine;
    }

    public void setIdmedicine(Integer idmedicine) {
        this.idmedicine = idmedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getActivesubstance() {
        return activesubstance;
    }

    public void setActivesubstance(String activesubstance) {
        this.activesubstance = activesubstance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicine != null ? idmedicine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicine)) {
            return false;
        }
        Medicine other = (Medicine) object;
        if ((this.idmedicine == null && other.idmedicine != null) || (this.idmedicine != null && !this.idmedicine.equals(other.idmedicine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Medicine[ idmedicine=" + idmedicine + " ]";
    }
    
}

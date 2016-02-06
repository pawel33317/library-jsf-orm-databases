/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bibl.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "szafki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Szafki.findAll", query = "SELECT s FROM Szafki s"),
    @NamedQuery(name = "Szafki.findByIdszafki", query = "SELECT s FROM Szafki s WHERE s.idszafki = :idszafki"),
    @NamedQuery(name = "Szafki.findBySzafka", query = "SELECT s FROM Szafki s WHERE s.szafka = :szafka")})
public class Szafki implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idszafki")
    private Integer idszafki;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "szafka")
    private String szafka;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idszafki")
    private Collection<Publikacje> publikacjeCollection;

    public Szafki() {
    }

    public Szafki(Integer idszafki) {
        this.idszafki = idszafki;
    }

    public Szafki(Integer idszafki, String szafka) {
        this.idszafki = idszafki;
        this.szafka = szafka;
    }

    public Integer getIdszafki() {
        return idszafki;
    }

    public void setIdszafki(Integer idszafki) {
        this.idszafki = idszafki;
    }

    public String getSzafka() {
        return szafka;
    }

    public void setSzafka(String szafka) {
        this.szafka = szafka;
    }

    @XmlTransient
    public Collection<Publikacje> getPublikacjeCollection() {
        return publikacjeCollection;
    }

    public void setPublikacjeCollection(Collection<Publikacje> publikacjeCollection) {
        this.publikacjeCollection = publikacjeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idszafki != null ? idszafki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Szafki)) {
            return false;
        }
        Szafki other = (Szafki) object;
        if ((this.idszafki == null && other.idszafki != null) || (this.idszafki != null && !this.idszafki.equals(other.idszafki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Szafki[ idszafki=" + idszafki + " ]";
    }
    
}

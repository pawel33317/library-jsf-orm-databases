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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "egzemplarze")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egzemplarze.findAll", query = "SELECT e FROM Egzemplarze e"),
    @NamedQuery(name = "Egzemplarze.findByIDegzemplarza", query = "SELECT e FROM Egzemplarze e WHERE e.iDegzemplarza = :iDegzemplarza"),
    @NamedQuery(name = "Egzemplarze.findByIDpublikacji", query = "SELECT e FROM Egzemplarze e WHERE e.iDpublikacji = :iPublikacji"),
    @NamedQuery(name = "Egzemplarze.findByUbytki", query = "SELECT e FROM Egzemplarze e WHERE e.ubytki = :ubytki")})
public class Egzemplarze implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDegzemplarza")
    private Integer iDegzemplarza;
    @Size(max = 250)
    @Column(name = "Ubytki")
    private String ubytki;
    @JoinColumn(name = "IDpublikacji", referencedColumnName = "idpublikacji")
    @ManyToOne(optional = false)
    private Publikacje iDpublikacji;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDegzemplarza")
    private Collection<Wypozyczenia> wypozyczeniaCollection;

    public Egzemplarze() {
    }

    public Egzemplarze(Integer iDegzemplarza) {
        this.iDegzemplarza = iDegzemplarza;
    }

    public Integer getIDegzemplarza() {
        return iDegzemplarza;
    }

    public void setIDegzemplarza(Integer iDegzemplarza) {
        this.iDegzemplarza = iDegzemplarza;
    }

    public String getUbytki() {
        return ubytki;
    }

    public void setUbytki(String ubytki) {
        this.ubytki = ubytki;
    }

    public Publikacje getIDpublikacji() {
        return iDpublikacji;
    }

    public void setIDpublikacji(Publikacje iDpublikacji) {
        this.iDpublikacji = iDpublikacji;
    }

    @XmlTransient
    public Collection<Wypozyczenia> getWypozyczeniaCollection() {
        return wypozyczeniaCollection;
    }

    public void setWypozyczeniaCollection(Collection<Wypozyczenia> wypozyczeniaCollection) {
        this.wypozyczeniaCollection = wypozyczeniaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDegzemplarza != null ? iDegzemplarza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egzemplarze)) {
            return false;
        }
        Egzemplarze other = (Egzemplarze) object;
        if ((this.iDegzemplarza == null && other.iDegzemplarza != null) || (this.iDegzemplarza != null && !this.iDegzemplarza.equals(other.iDegzemplarza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Egzemplarze[ iDegzemplarza=" + iDegzemplarza + " ]";
    }
    
}

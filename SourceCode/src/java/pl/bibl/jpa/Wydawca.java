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
@Table(name = "wydawca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wydawca.findAll", query = "SELECT w FROM Wydawca w"),
    @NamedQuery(name = "Wydawca.findByIdwydawcy", query = "SELECT w FROM Wydawca w WHERE w.idwydawcy = :idwydawcy"),
    @NamedQuery(name = "Wydawca.findByWydawca", query = "SELECT w FROM Wydawca w WHERE w.wydawca = :wydawca"),
    @NamedQuery(name = "Wydawca.findByNazwa", query = "SELECT w FROM Wydawca w WHERE w.nazwa = :nazwa")})
public class Wydawca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idwydawcy")
    private Integer idwydawcy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "wydawca")
    private String wydawca;
    @Size(max = 45)
    @Column(name = "nazwa")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idwydawcy")
    private Collection<Publikacje> publikacjeCollection;

    public Wydawca() {
    }

    public Wydawca(Integer idwydawcy) {
        this.idwydawcy = idwydawcy;
    }

    public Wydawca(Integer idwydawcy, String wydawca) {
        this.idwydawcy = idwydawcy;
        this.wydawca = wydawca;
    }

    public Integer getIdwydawcy() {
        return idwydawcy;
    }

    public void setIdwydawcy(Integer idwydawcy) {
        this.idwydawcy = idwydawcy;
    }

    public String getWydawca() {
        return wydawca;
    }

    public void setWydawca(String wydawca) {
        this.wydawca = wydawca;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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
        hash += (idwydawcy != null ? idwydawcy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wydawca)) {
            return false;
        }
        Wydawca other = (Wydawca) object;
        if ((this.idwydawcy == null && other.idwydawcy != null) || (this.idwydawcy != null && !this.idwydawcy.equals(other.idwydawcy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Wydawca[ idwydawcy=" + idwydawcy + " ]";
    }
    
}

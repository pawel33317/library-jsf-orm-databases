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
@Table(name = "kategorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorie.findAll", query = "SELECT k FROM Kategorie k"),
    @NamedQuery(name = "Kategorie.findByIdkategorii", query = "SELECT k FROM Kategorie k WHERE k.idkategorii = :idkategorii"),
    @NamedQuery(name = "Kategorie.findByKategoria", query = "SELECT k FROM Kategorie k WHERE k.kategoria = :kategoria")})
public class Kategorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkategorii")
    private Integer idkategorii;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "kategoria")
    private String kategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idkategorii")
    private Collection<Publikacje> publikacjeCollection;

    public Kategorie() {
    }

    public Kategorie(Integer idkategorii) {
        this.idkategorii = idkategorii;
    }

    public Kategorie(Integer idkategorii, String kategoria) {
        this.idkategorii = idkategorii;
        this.kategoria = kategoria;
    }

    public Integer getIdkategorii() {
        return idkategorii;
    }

    public void setIdkategorii(Integer idkategorii) {
        this.idkategorii = idkategorii;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
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
        hash += (idkategorii != null ? idkategorii.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorie)) {
            return false;
        }
        Kategorie other = (Kategorie) object;
        if ((this.idkategorii == null && other.idkategorii != null) || (this.idkategorii != null && !this.idkategorii.equals(other.idkategorii))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Kategorie[ idkategorii=" + idkategorii + " ]";
    }
    
}

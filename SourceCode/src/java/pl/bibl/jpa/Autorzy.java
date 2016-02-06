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
@Table(name = "autorzy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autorzy.findAll", query = "SELECT a FROM Autorzy a"),
    @NamedQuery(name = "Autorzy.findByIdautora", query = "SELECT a FROM Autorzy a WHERE a.idautora = :idautora"),
    @NamedQuery(name = "Autorzy.findByNazwisko", query = "SELECT a FROM Autorzy a WHERE a.nazwisko = :nazwisko"),
    @NamedQuery(name = "Autorzy.findByImie", query = "SELECT a FROM Autorzy a WHERE a.imie = :imie"),
    @NamedQuery(name = "Autorzy.findByUwagi", query = "SELECT a FROM Autorzy a WHERE a.uwagi = :uwagi")})
public class Autorzy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idautora")
    private Integer idautora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "imie")
    private String imie;
    @Size(max = 250)
    @Column(name = "uwagi")
    private String uwagi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautora")
    private Collection<Publikacje> publikacjeCollection;

    public Autorzy() {
    }

    public Autorzy(Integer idautora) {
        this.idautora = idautora;
    }

    public Autorzy(Integer idautora, String nazwisko, String imie) {
        this.idautora = idautora;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    public Integer getIdautora() {
        return idautora;
    }

    public void setIdautora(Integer idautora) {
        this.idautora = idautora;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
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
        hash += (idautora != null ? idautora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorzy)) {
            return false;
        }
        Autorzy other = (Autorzy) object;
        if ((this.idautora == null && other.idautora != null) || (this.idautora != null && !this.idautora.equals(other.idautora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Autorzy[ idautora=" + idautora + " ]";
    }
    
}

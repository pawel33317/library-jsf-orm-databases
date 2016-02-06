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
@Table(name = "czytelnicy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Czytelnicy.findAll", query = "SELECT c FROM Czytelnicy c"),
    @NamedQuery(name = "Czytelnicy.findByNRkarty", query = "SELECT c FROM Czytelnicy c WHERE c.nRkarty = :nRkarty"),
    @NamedQuery(name = "Czytelnicy.findByNazwisko", query = "SELECT c FROM Czytelnicy c WHERE c.nazwisko = :nazwisko"),
    @NamedQuery(name = "Czytelnicy.findByImie", query = "SELECT c FROM Czytelnicy c WHERE c.imie = :imie"),
    @NamedQuery(name = "Czytelnicy.findByIddokumentu", query = "SELECT c FROM Czytelnicy c WHERE c.iddokumentu = :iddokumentu"),
    @NamedQuery(name = "Czytelnicy.findByNRdokumentu", query = "SELECT c FROM Czytelnicy c WHERE c.nRdokumentu = :nRdokumentu"),
    @NamedQuery(name = "Czytelnicy.findByDataUr", query = "SELECT c FROM Czytelnicy c WHERE c.dataUr = :dataUr"),
    @NamedQuery(name = "Czytelnicy.findByMiejsceUr", query = "SELECT c FROM Czytelnicy c WHERE c.miejsceUr = :miejsceUr"),
    @NamedQuery(name = "Czytelnicy.findByKod", query = "SELECT c FROM Czytelnicy c WHERE c.kod = :kod"),
    @NamedQuery(name = "Czytelnicy.findByMiasto", query = "SELECT c FROM Czytelnicy c WHERE c.miasto = :miasto"),
    @NamedQuery(name = "Czytelnicy.findByIDulicy", query = "SELECT c FROM Czytelnicy c WHERE c.iDulicy = :iDulicy"),
    @NamedQuery(name = "Czytelnicy.findByUlica", query = "SELECT c FROM Czytelnicy c WHERE c.ulica = :ulica"),
    @NamedQuery(name = "Czytelnicy.findByTelefon", query = "SELECT c FROM Czytelnicy c WHERE c.telefon = :telefon")})
public class Czytelnicy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NRkarty")
    private Integer nRkarty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Imie")
    private String imie;
    @Size(max = 30)
    @Column(name = "Iddokumentu")
    private String iddokumentu;
    @Size(max = 15)
    @Column(name = "NRdokumentu")
    private String nRdokumentu;
    @Size(max = 15)
    @Column(name = "DataUr")
    private String dataUr;
    @Size(max = 15)
    @Column(name = "MiejsceUr")
    private String miejsceUr;
    @Size(max = 15)
    @Column(name = "Kod")
    private String kod;
    @Size(max = 15)
    @Column(name = "Miasto")
    private String miasto;
    @Size(max = 15)
    @Column(name = "IDulicy")
    private String iDulicy;
    @Size(max = 70)
    @Column(name = "Ulica")
    private String ulica;
    @Size(max = 15)
    @Column(name = "telefon")
    private String telefon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nRkarty")
    private Collection<Wypozyczenia> wypozyczeniaCollection;

    public Czytelnicy() {
    }

    public Czytelnicy(Integer nRkarty) {
        this.nRkarty = nRkarty;
    }

    public Czytelnicy(Integer nRkarty, String nazwisko, String imie) {
        this.nRkarty = nRkarty;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    public Integer getNRkarty() {
        return nRkarty;
    }

    public void setNRkarty(Integer nRkarty) {
        this.nRkarty = nRkarty;
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

    public String getIddokumentu() {
        return iddokumentu;
    }

    public void setIddokumentu(String iddokumentu) {
        this.iddokumentu = iddokumentu;
    }

    public String getNRdokumentu() {
        return nRdokumentu;
    }

    public void setNRdokumentu(String nRdokumentu) {
        this.nRdokumentu = nRdokumentu;
    }

    public String getDataUr() {
        return dataUr;
    }

    public void setDataUr(String dataUr) {
        this.dataUr = dataUr;
    }

    public String getMiejsceUr() {
        return miejsceUr;
    }

    public void setMiejsceUr(String miejsceUr) {
        this.miejsceUr = miejsceUr;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getIDulicy() {
        return iDulicy;
    }

    public void setIDulicy(String iDulicy) {
        this.iDulicy = iDulicy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
        hash += (nRkarty != null ? nRkarty.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Czytelnicy)) {
            return false;
        }
        Czytelnicy other = (Czytelnicy) object;
        if ((this.nRkarty == null && other.nRkarty != null) || (this.nRkarty != null && !this.nRkarty.equals(other.nRkarty))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Czytelnicy[ nRkarty=" + nRkarty + " ]";
    }
    
}

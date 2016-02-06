/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bibl.jpa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "wypozyczenia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wypozyczenia.findByNrKarty", query = "SELECT w FROM Wypozyczenia w WHERE w.nRkarty = :nrkarty"),
    @NamedQuery(name = "Wypozyczenia.findAll", query = "SELECT w FROM Wypozyczenia w"),
    @NamedQuery(name = "Wypozyczenia.findByDataWyp", query = "SELECT w FROM Wypozyczenia w WHERE w.dataWyp = :dataWyp"),
    @NamedQuery(name = "Wypozyczenia.findByDataPrzed", query = "SELECT w FROM Wypozyczenia w WHERE w.dataPrzed = :dataPrzed"),
    @NamedQuery(name = "Wypozyczenia.findByDaraZwrot", query = "SELECT w FROM Wypozyczenia w WHERE w.daraZwrot = :daraZwrot"),
    @NamedQuery(name = "Wypozyczenia.findByIDwypozyczenia", query = "SELECT w FROM Wypozyczenia w WHERE w.iDwypozyczenia = :iDwypozyczenia"),
    @NamedQuery(name = "Wypozyczenia.findByIDegzemplarza", query = "SELECT w FROM Wypozyczenia w WHERE w.iDegzemplarza = :iEgzemplarza"),
    @NamedQuery(name = "Wypozyczenia.findByWypozyczeniacol", query = "SELECT w FROM Wypozyczenia w WHERE w.wypozyczeniacol = :wypozyczeniacol"),
    @NamedQuery(name = "Wypozyczenia.findByOdebrano", query = "SELECT w FROM Wypozyczenia w WHERE w.odebrano = :odebrano")})
public class Wypozyczenia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_Wyp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWyp;
    @Column(name = "Data_Przed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPrzed;
    @Column(name = "Dara_Zwrot")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daraZwrot;
    @Column(name = "IDwypozyczenia")
    private Integer iDwypozyczenia;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wypozyczeniacol")
    private Integer wypozyczeniacol;
    @Column(name = "odebrano")
    private Integer odebrano;
    @JoinColumn(name = "IDegzemplarza", referencedColumnName = "IDegzemplarza")
    @ManyToOne(optional = false)
    private Egzemplarze iDegzemplarza;
    @JoinColumn(name = "NRkarty", referencedColumnName = "NRkarty")
    @ManyToOne(optional = false)
    private Czytelnicy nRkarty;

    public Wypozyczenia() {
    }

    public Wypozyczenia(Integer wypozyczeniacol) {
        this.wypozyczeniacol = wypozyczeniacol;
    }

    public Wypozyczenia(Integer wypozyczeniacol, Date dataWyp) {
        this.wypozyczeniacol = wypozyczeniacol;
        this.dataWyp = dataWyp;
    }

    public Date getDataWyp() {
        return dataWyp;
    }

    public void setDataWyp(Date dataWyp) {
        this.dataWyp = dataWyp;
    }

    public Date getDataPrzed() {
        return dataPrzed;
    }

    public void setDataPrzed(Date dataPrzed) {
        this.dataPrzed = dataPrzed;
    }

    public Date getDaraZwrot() {
        return daraZwrot;
    }

    public void setDaraZwrot(Date daraZwrot) {
        this.daraZwrot = daraZwrot;
    }

    public Integer getIDwypozyczenia() {
        return iDwypozyczenia;
    }

    public void setIDwypozyczenia(Integer iDwypozyczenia) {
        this.iDwypozyczenia = iDwypozyczenia;
    }

    public Integer getWypozyczeniacol() {
        return wypozyczeniacol;
    }

    public void setWypozyczeniacol(Integer wypozyczeniacol) {
        this.wypozyczeniacol = wypozyczeniacol;
    }

    public Integer getOdebrano() {
        return odebrano;
    }

    public void setOdebrano(Integer odebrano) {
        this.odebrano = odebrano;
    }

    public Egzemplarze getIDegzemplarza() {
        return iDegzemplarza;
    }

    public void setIDegzemplarza(Egzemplarze iDegzemplarza) {
        this.iDegzemplarza = iDegzemplarza;
    }

    public Czytelnicy getNRkarty() {
        return nRkarty;
    }

    public void setNRkarty(Czytelnicy nRkarty) {
        this.nRkarty = nRkarty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wypozyczeniacol != null ? wypozyczeniacol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wypozyczenia)) {
            return false;
        }
        Wypozyczenia other = (Wypozyczenia) object;
        if ((this.wypozyczeniacol == null && other.wypozyczeniacol != null) || (this.wypozyczeniacol != null && !this.wypozyczeniacol.equals(other.wypozyczeniacol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Wypozyczenia[ wypozyczeniacol=" + wypozyczeniacol + " ]";
    }
    
}

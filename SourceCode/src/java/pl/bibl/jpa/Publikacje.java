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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "publikacje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publikacje.findAll", query = "SELECT p FROM Publikacje p"),
    @NamedQuery(name = "Publikacje.likeByTytul", query = "SELECT p FROM Publikacje p WHERE p.tytul LIKE :tytul"),
    @NamedQuery(name = "Publikacje.findByIdpublikacji", query = "SELECT p FROM Publikacje p WHERE p.idpublikacji = :idpublikacji"),
    @NamedQuery(name = "Publikacje.findByTytul", query = "SELECT p FROM Publikacje p WHERE p.tytul = :tytul"),
    @NamedQuery(name = "Publikacje.findByRok", query = "SELECT p FROM Publikacje p WHERE p.rok = :rok"),
    @NamedQuery(name = "Publikacje.findByMiejsce", query = "SELECT p FROM Publikacje p WHERE p.miejsce = :miejsce"),
    @NamedQuery(name = "Publikacje.findBySlowaKluczowe", query = "SELECT p FROM Publikacje p WHERE p.slowaKluczowe = :slowaKluczowe"),
    @NamedQuery(name = "Publikacje.findByAuthor", query = "SELECT p FROM Publikacje p WHERE p.idautora = :idautora"),
    @NamedQuery(name = "Publikacje.findByWydawnictwo", query = "SELECT p FROM Publikacje p WHERE p.idwydawcy = :idwydawcy")})
public class Publikacje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpublikacji")
    private Integer idpublikacji;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "tytul")
    private String tytul;
    @Size(max = 4)
    @Column(name = "rok")
    private String rok;
    @Size(max = 50)
    @Column(name = "miejsce")
    private String miejsce;
    @Size(max = 250)
    @Column(name = "slowa_kluczowe")
    private String slowaKluczowe;
    @JoinColumn(name = "idkategorii", referencedColumnName = "idkategorii")
    @ManyToOne(optional = false)
    private Kategorie idkategorii;
    @JoinColumn(name = "idautora", referencedColumnName = "idautora")
    @ManyToOne(optional = false)
    private Autorzy idautora;
    @JoinColumn(name = "idwydawcy", referencedColumnName = "idwydawcy")
    @ManyToOne(optional = false)
    private Wydawca idwydawcy;
    @JoinColumn(name = "idszafki", referencedColumnName = "idszafki")
    @ManyToOne(optional = false)
    private Szafki idszafki;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDpublikacji")
    private Collection<Egzemplarze> egzemplarzeCollection;

    public Publikacje() {
    }

    public Publikacje(Integer idpublikacji) {
        this.idpublikacji = idpublikacji;
    }

    public Publikacje(Integer idpublikacji, String tytul) {
        this.idpublikacji = idpublikacji;
        this.tytul = tytul;
    }

    public Integer getIdpublikacji() {
        return idpublikacji;
    }

    public void setIdpublikacji(Integer idpublikacji) {
        this.idpublikacji = idpublikacji;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getSlowaKluczowe() {
        return slowaKluczowe;
    }

    public void setSlowaKluczowe(String slowaKluczowe) {
        this.slowaKluczowe = slowaKluczowe;
    }

    public Kategorie getIdkategorii() {
        return idkategorii;
    }

    public void setIdkategorii(Kategorie idkategorii) {
        this.idkategorii = idkategorii;
    }

    public Autorzy getIdautora() {
        return idautora;
    }

    public void setIdautora(Autorzy idautora) {
        this.idautora = idautora;
    }

    public Wydawca getIdwydawcy() {
        return idwydawcy;
    }

    public void setIdwydawcy(Wydawca idwydawcy) {
        this.idwydawcy = idwydawcy;
    }

    public Szafki getIdszafki() {
        return idszafki;
    }

    public void setIdszafki(Szafki idszafki) {
        this.idszafki = idszafki;
    }

    @XmlTransient
    public Collection<Egzemplarze> getEgzemplarzeCollection() {
        return egzemplarzeCollection;
    }

    public void setEgzemplarzeCollection(Collection<Egzemplarze> egzemplarzeCollection) {
        this.egzemplarzeCollection = egzemplarzeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpublikacji != null ? idpublikacji.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publikacje)) {
            return false;
        }
        Publikacje other = (Publikacje) object;
        if ((this.idpublikacji == null && other.idpublikacji != null) || (this.idpublikacji != null && !this.idpublikacji.equals(other.idpublikacji))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bibl.jpa.Publikacje[ idpublikacji=" + idpublikacji + " ]";
    }
    
}

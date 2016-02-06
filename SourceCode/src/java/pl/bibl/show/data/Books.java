/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bibl.show.data;

import java.io.Serializable;
import pl.bibl.jpa.*;

public class Books  implements Serializable{
    private String nazwa;
    private String autor;
    private String kategoria;
    private String wydawca;
    private String rok;
    private String iloscDostepnych;

    public String getIloscDostepnych() {
        return iloscDostepnych;
    }

    public void setIloscDostepnych(String iloscDostepnych) {
        this.iloscDostepnych = iloscDostepnych;
    }

    public Integer getIntSize() {
        return intSize;
    }

    public void setIntSize(Integer intSize) {
        this.intSize = intSize;
    }

    public String getIdPublikacji() {
        return idPublikacji;
    }

    public void setIdPublikacji(String idPublikacji) {
        this.idPublikacji = idPublikacji;
    }
    private Integer intSize;
    private String id;
    private String idPublikacji;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Books(String id, String nazwa, String autor, String kategoria, String wydawca, String rok, String iloscDostepnych, Integer getIntSize, String idPublikacji) {
        this.nazwa = nazwa;
        this.autor = autor;
        this.kategoria = kategoria;
        this.wydawca = wydawca;
        this.rok = rok;
        this.iloscDostepnych = iloscDostepnych;
        this.intSize = getIntSize;
        this.id = id;
        this.idPublikacji = idPublikacji;
    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getWydawca() {
        return wydawca;
    }

    public void setWydawca(String wydawca) {
        this.wydawca = wydawca;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }
}

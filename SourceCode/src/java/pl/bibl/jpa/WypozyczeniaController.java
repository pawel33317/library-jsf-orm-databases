package pl.bibl.jpa;

import java.io.IOException;
import pl.bibl.jpa.util.JsfUtil;
import pl.bibl.jpa.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;
import org.primefaces.model.chart.PieChartModel;
import pl.bibl.show.data.MojeWypozyczenie;

@ManagedBean(name = "wypozyczeniaController")
@SessionScoped
public class WypozyczeniaController implements Serializable {

    @EJB
    private pl.bibl.jpa.WypozyczeniaFacade ejbFacade;
    private List<Wypozyczenia> items = null;
    private Wypozyczenia selected;

    public WypozyczeniaController() {
    }

    public Wypozyczenia getSelected() {
        return selected;
    }

    public void setSelected(Wypozyczenia selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WypozyczeniaFacade getFacade() {
        return ejbFacade;
    }
    private PieChartModel booksChart;
    private PieChartModel userChart;
    public PieChartModel getBooksChart() {
        List<Wypozyczenia> allWyp = new ArrayList<Wypozyczenia>();
        
        Query wypp = getFacade().getEntityManager().createNamedQuery("Wypozyczenia.findAll");
        Collection wyppp = wypp.getResultList();
        List<Wypozyczenia> ll = new ArrayList(wyppp);

        int wypozyczone = 0;
        int zarezerwowane = 0;
        int wszystkie = 0;

        for (Wypozyczenia item1 :ll) {
            if (item1.getOdebrano() == 0) {
                zarezerwowane++;
            }else{
                wypozyczone++;
            }
        };
        
        Query egz = getFacade().getEntityManager().createNamedQuery("Egzemplarze.findAll");
        
        wszystkie = egz.getResultList().size()-zarezerwowane-wypozyczone;
        
        booksChart = new PieChartModel();

        booksChart.set("Wypożyczone", wypozyczone);
        booksChart.set("Zarezerwowane", zarezerwowane);
        booksChart.set("Inne", wszystkie);

        booksChart.setTitle("Statystyki książek");
        booksChart.setLegendPosition("w");
        booksChart.setShowDataLabels(true);
        return booksChart;
    }

    public PieChartModel getUserChart() {
        
        Query allUsers = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findAll");
        Collection users = allUsers.getResultList();
        List<Czytelnicy> ll = new ArrayList(users);
        int zKsiazka = 0;
        
        for (Czytelnicy c : ll){
            Query czyWypozyczaCos = getFacade().getEntityManager().createNamedQuery("Wypozyczenia.findByNrKarty");
            czyWypozyczaCos.setParameter("nrkarty", c);
            if(czyWypozyczaCos.getResultList().size() > 0){
                zKsiazka++;
            }
        }
        booksChart = new PieChartModel();

        booksChart.set("Bez książką", ll.size()-zKsiazka);
        booksChart.set("Z książki", zKsiazka);

        booksChart.setTitle("Statystyki użytkowników");
        booksChart.setLegendPosition("w");
        booksChart.setFill(false);
        booksChart.setShowDataLabels(true);
        booksChart.setDiameter(250);
        return booksChart;
    }

    public void setBooksChart(PieChartModel booksChart) {
        this.booksChart = booksChart;
    }

    public Wypozyczenia prepareCreate() {
        selected = new Wypozyczenia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WypozyczeniaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WypozyczeniaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WypozyczeniaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Wypozyczenia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void anulujButton(ActionEvent actionEvent) {
        String idAnulowania = (String) actionEvent.getComponent().getAttributes().get("action");

        Query anulujRez = getFacade().getEntityManager().createNamedQuery("Wypozyczenia.findByWypozyczeniacol");
        anulujRez.setParameter("wypozyczeniacol", Integer.valueOf(idAnulowania));
        Collection wypozyczenie = anulujRez.getResultList();

        List<Wypozyczenia> list = new ArrayList<Wypozyczenia>(wypozyczenie);
        Wypozyczenia w = list.get(0);
        w.setOdebrano(1);
        this.selected = w;
        update();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("userBookPage.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(WypozyczeniaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void markReserved(ActionEvent actionEvent) {
        String idRezerwacji = (String) actionEvent.getComponent().getAttributes().get("action");

        FacesContext f = FacesContext.getCurrentInstance();
        CzytelnicyController ck = (CzytelnicyController) f.getApplication().getVariableResolver().resolveVariable(f, "czytelnicyController");
        System.out.println(ck.getName());

        Query publikacjaDorezerwacji = getFacade().getEntityManager().createNamedQuery("Publikacje.findByIdpublikacji");
        publikacjaDorezerwacji.setParameter("idpublikacji", Integer.valueOf(idRezerwacji));
        Collection onePub = publikacjaDorezerwacji.getResultList();
        List<Publikacje> list2 = new ArrayList<>(onePub);

        //ustawiamy na pierwszy można zmienić na null
        Publikacje myPublikacja = list2.get(0);

        Query egz = getFacade().getEntityManager().createNamedQuery("Egzemplarze.findByIDpublikacji");
        egz.setParameter("iPublikacji", myPublikacja);
        Collection egzemplarz = egz.getResultList();
        List<Egzemplarze> list = new ArrayList<>(egzemplarz);

        //ustawiamy na pierwszy można zmienić na null
        Egzemplarze rezerwowanyEgzemplarz = list.get(0);

        for (Egzemplarze e : list) {
            Query czyWypozyczanyEgzemplarz = getFacade().getEntityManager().createNamedQuery("Wypozyczenia.findByIDegzemplarza");
            czyWypozyczanyEgzemplarz.setParameter("iEgzemplarza", e);
            if (czyWypozyczanyEgzemplarz.getResultList().isEmpty()) {
                rezerwowanyEgzemplarz = e;
                break;
            }
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^razy");
        }
        Wypozyczenia w = new Wypozyczenia();
        w.setIDegzemplarza(rezerwowanyEgzemplarz);
        w.setOdebrano(0);
        w.setDataWyp(new Date());
        w.setNRkarty(ck.getSelected());
        create();

        this.selected = w;
        update();

        Query queryEmployeesByFirstName = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findByImie");
        queryEmployeesByFirstName.setParameter("imie", ck.getName());

        ck.listaMoichRezerwacjiDoWyswietlenia.add(
                new MojeWypozyczenie(
                        String.valueOf(0),
                        w.getIDegzemplarza().getIDpublikacji().getTytul(),
                        w.getIDegzemplarza().getIDpublikacji().getIdautora().getImie() + " "
                        + w.getIDegzemplarza().getIDpublikacji().getIdautora().getNazwisko(),
                        w.getIDegzemplarza().getIDpublikacji().getIdkategorii().getKategoria(),
                        w.getIDegzemplarza().getIDpublikacji().getIdwydawcy().getWydawca(),
                        String.valueOf(w.getWypozyczeniacol())));

        Query qq = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findAll");
        Collection qqq = queryEmployeesByFirstName.getResultList();
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("userBookPage.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(WypozyczeniaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Wypozyczenia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Wypozyczenia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Wypozyczenia.class)
    public static class WypozyczeniaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WypozyczeniaController controller = (WypozyczeniaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "wypozyczeniaController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Wypozyczenia) {
                Wypozyczenia o = (Wypozyczenia) object;
                return getStringKey(o.getWypozyczeniacol());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Wypozyczenia.class.getName()});
                return null;
            }
        }

    }

}

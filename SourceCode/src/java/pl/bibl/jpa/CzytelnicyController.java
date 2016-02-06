package pl.bibl.jpa;

import java.io.IOException;
import pl.bibl.jpa.util.JsfUtil;
import pl.bibl.jpa.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;
import pl.bibl.show.data.MojeWypozyczenie;
import javax.persistence.QueryHint;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

@ManagedBean(name = "czytelnicyController")
@SessionScoped
public class CzytelnicyController implements Serializable {

    @EJB
    private pl.bibl.jpa.CzytelnicyFacade ejbFacade;
    private List<Czytelnicy> items = null;
    private Czytelnicy selected;

    private String redirectPage = "loginPage.xhtml";
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }
    private String alerts = "Brak powiadomień";

    public String getRedirectPage() {

        Query queryEmployeesByFirstName = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findByImie");
        queryEmployeesByFirstName.setParameter("imie", this.getName());
        Collection employees = queryEmployeesByFirstName.getResultList();
        if (employees.size() < 1) {
            alerts = "Podany użytkownik nie istnieje";
        } else {
            List<Czytelnicy> ll = new ArrayList(employees);
            //alerts=ll.get(0).getNazwisko();

            if (ll.get(0).getNazwisko().equals(this.getPassword())) {
                this.selected = ll.get(0);
                listaMoichWypozyczen = new ArrayList(this.selected.getWypozyczeniaCollection());
                this.alerts = "Zalogowany jako: " + selected.getImie() + " " + selected.getNazwisko();
                return ("userMainPage.xhtml?faces-redirect=true");
            } else {
                alerts = " Hasło jest niepoprawne.";
            }
        }

        //this.items = this.getItemsAvailableSelectOne();
        return redirectPage;
    }

    public MojeWypozyczenie getMojeWypozyczenieSelected() {
        return mojeWypozyczenieSelected;
    }

    public void setMojeWypozyczenieSelected(MojeWypozyczenie mojeWypozyczenieSelected) {
        this.mojeWypozyczenieSelected = mojeWypozyczenieSelected;
    }
    public List<Wypozyczenia> listaMoichWypozyczen;
    public List<MojeWypozyczenie> listaMoichWypozyczenDoWyswietlenia = new ArrayList<MojeWypozyczenie>();
    public MojeWypozyczenie mojeWypozyczenieSelected;
    public List<MojeWypozyczenie> listaMoichRezerwacjiDoWyswietlenia = new ArrayList<MojeWypozyczenie>();

    public List<MojeWypozyczenie> getListaMoichRezerwacjiDoWyswietlenia() {
        if (selected == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("loginPage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(WypozyczeniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        listaMoichRezerwacjiDoWyswietlenia.clear();
        listaMoichRezerwacjiDoWyswietlenia = new ArrayList<MojeWypozyczenie>();
        Query queryEmployeesByFirstName = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findByImie");
        queryEmployeesByFirstName.setParameter("imie", this.getName());

        Collection employees = queryEmployeesByFirstName.setHint("javax.persistence.cache.storeMode", "REFRESH").
                getResultList();
        List<Czytelnicy> ll = new ArrayList(employees);
        this.selected = ll.get(0);

        int i = 0;
        for (Wypozyczenia item1 : this.selected.getWypozyczeniaCollection()) {
            if (item1.getOdebrano() == 0) {
                System.out.println("zmieniam" + i);
                i++;
                listaMoichRezerwacjiDoWyswietlenia.add(new MojeWypozyczenie(
                        String.valueOf(i),
                        item1.getIDegzemplarza().getIDpublikacji().getTytul(),
                        item1.getIDegzemplarza().getIDpublikacji().getIdautora().getImie() + " "
                        + item1.getIDegzemplarza().getIDpublikacji().getIdautora().getNazwisko(),
                        item1.getIDegzemplarza().getIDpublikacji().getIdkategorii().getKategoria(),
                        item1.getIDegzemplarza().getIDpublikacji().getIdwydawcy().getWydawca(),
                        String.valueOf(item1.getWypozyczeniacol())
                ));
                mojeWypozyczenieSelected = listaMoichRezerwacjiDoWyswietlenia.get(0);
            }
        };
        return listaMoichRezerwacjiDoWyswietlenia;
    }

    public void setListaMoichRezerwacjiDoWyswietlenia(List<MojeWypozyczenie> listaMoichRezerwacjiDoWyswietlenia) {
        this.listaMoichRezerwacjiDoWyswietlenia = listaMoichRezerwacjiDoWyswietlenia;
    }

    public List<MojeWypozyczenie> getListaMoichWypozyczenDoWyswietlenia() {
        if (selected == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("loginPage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(WypozyczeniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Query queryEmployeesByFirstName = getFacade().getEntityManager().createNamedQuery("Czytelnicy.findByImie");
        queryEmployeesByFirstName.setParameter("imie", this.getName());
        Collection employees = queryEmployeesByFirstName
                .setHint("javax.persistence.cache.storeMode", "REFRESH")
                .getResultList();
        List<Czytelnicy> ll = new ArrayList(employees);
        this.selected = ll.get(0);

        listaMoichWypozyczenDoWyswietlenia.clear();
        int i = 0;
        for (Wypozyczenia item1 : this.selected.getWypozyczeniaCollection()) {
            i++;
            listaMoichWypozyczenDoWyswietlenia.add(new MojeWypozyczenie(
                    String.valueOf(i),
                    item1.getIDegzemplarza().getIDpublikacji().getTytul(),
                    item1.getIDegzemplarza().getIDpublikacji().getIdautora().getImie() + " "
                    + item1.getIDegzemplarza().getIDpublikacji().getIdautora().getNazwisko(),
                    item1.getIDegzemplarza().getIDpublikacji().getIdkategorii().getKategoria(),
                    item1.getIDegzemplarza().getIDpublikacji().getIdwydawcy().getWydawca(),
                    item1.getIDegzemplarza().getIDpublikacji().getRok()
            ));
            mojeWypozyczenieSelected = listaMoichWypozyczenDoWyswietlenia.get(0);
        };
        return listaMoichWypozyczenDoWyswietlenia;
    }

    public void setListaMoichWypozyczenDoWyswietlenia(List<MojeWypozyczenie> listaMoichWypozyczenDoWyswietlenia) {
        this.listaMoichWypozyczenDoWyswietlenia = listaMoichWypozyczenDoWyswietlenia;
    }

    public CzytelnicyController() {
    }

    public Czytelnicy getSelected() {
        return selected;
    }

    public void setSelected(Czytelnicy selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CzytelnicyFacade getFacade() {
        return ejbFacade;
    }

    public Czytelnicy prepareCreate() {
        selected = new Czytelnicy();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CzytelnicyCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CzytelnicyUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CzytelnicyDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Czytelnicy> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public List<Czytelnicy> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Czytelnicy> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Czytelnicy.class)
    public static class CzytelnicyControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CzytelnicyController controller = (CzytelnicyController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "czytelnicyController");
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
            if (object instanceof Czytelnicy) {
                Czytelnicy o = (Czytelnicy) object;
                return getStringKey(o.getNRkarty());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Czytelnicy.class.getName()});
                return null;
            }
        }

    }

}

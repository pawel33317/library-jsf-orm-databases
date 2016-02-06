package pl.bibl.jpa;

import pl.bibl.jpa.util.JsfUtil;
import pl.bibl.jpa.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
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
import javax.persistence.Query;
import pl.bibl.show.data.Books;
import pl.bibl.show.data.MojeWypozyczenie;

@ManagedBean(name = "publikacjeController")
@SessionScoped
public class PublikacjeController implements Serializable {

    @EJB
    private pl.bibl.jpa.PublikacjeFacade ejbFacade;
    private List<Publikacje> items = null;
    private Publikacje selected;

    private String searchType = "1";
    private String searchText;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public PublikacjeController() {
    }
    public List<Books> allBooks;

    public List<Books> searchedBooks;

    public void setSearchedBooks(List<Books> searchedBooks) {
        this.searchedBooks = searchedBooks;
    }

    public List<Books> getSearchedBooks() {
        searchedBooks = new ArrayList<>();
        int i = 0;
        Query qsb = getFacade().getEntityManager().createNamedQuery("Publikacje.findAll");

        if (this.searchType.equals("1")) {
            qsb = getFacade().getEntityManager().createNamedQuery("Publikacje.likeByTytul");
            qsb.setParameter("tytul", "%" + this.searchText + "%");

            List<Publikacje> ll = qsb.getResultList();
            for (Publikacje item1 : ll) {
                i++;
                searchedBooks.add(new Books(
                        String.valueOf(i),
                        item1.getTytul(),
                        item1.getIdautora().getImie() + " " + item1.getIdautora().getNazwisko(),
                        item1.getIdkategorii().getKategoria(),
                        item1.getIdwydawcy().getWydawca(),
                        item1.getRok(),
                        String.valueOf(ll.size()),
                        ll.size(),
                        String.valueOf(item1.getIdpublikacji())
                ));
            }

        } else if (this.searchType.equals("2")) {
            Query qsa = getFacade().getEntityManager().createNamedQuery("Autorzy.findByNazwisko");
            qsa.setParameter("nazwisko", this.searchText);
            List<Autorzy> qsal = qsa.getResultList();
            System.out.println(".............................wszedlem..................");
            for (Autorzy item : qsal) {
                qsb = getFacade().getEntityManager().createNamedQuery("Publikacje.findByAuthor");
                qsb.setParameter("idautora", item);
                List<Publikacje> ll = qsb.getResultList();
                for (Publikacje item1 : ll) {
                    i++;
                    searchedBooks.add(new Books(
                            String.valueOf(i),
                            item1.getTytul(),
                            item1.getIdautora().getImie() + " " + item1.getIdautora().getNazwisko(),
                            item1.getIdkategorii().getKategoria(),
                            item1.getIdwydawcy().getWydawca(),
                            item1.getRok(),
                            String.valueOf(ll.size()),
                            ll.size(),
                            String.valueOf(item1.getIdpublikacji())
                    ));
                }
            }

        } else if (this.searchType.equals("3")) {
            Query qsa = getFacade().getEntityManager().createNamedQuery("Wydawca.findByWydawca");
            qsa.setParameter("wydawca", this.searchText);
            List<Wydawca> qsal = qsa.getResultList();
            System.out.println(".............................wszedlem..................");
            for (Wydawca item : qsal) {
                qsb = getFacade().getEntityManager().createNamedQuery("Publikacje.findByWydawnictwo");
                qsb.setParameter("idwydawcy", item);
                List<Publikacje> ll = qsb.getResultList();
                for (Publikacje item1 : ll) {
                    i++;
                    searchedBooks.add(new Books(
                            String.valueOf(i),
                            item1.getTytul(),
                            item1.getIdautora().getImie() + " " + item1.getIdautora().getNazwisko(),
                            item1.getIdkategorii().getKategoria(),
                            item1.getIdwydawcy().getWydawca(),
                            item1.getRok(),
                            String.valueOf(ll.size()),
                            ll.size(),
                            String.valueOf(item1.getIdpublikacji())
                    ));
                }
            }
        }

        return searchedBooks;
    }

    public List<Books> getAllBooks() {
        //Query queryEmployeesByFirstName = getFacade().getEntityManager().createNamedQuery("Publikacje.findAll");
        items = getFacade().findAll();

        //queryEmployeesByFirstName.setParameter("imie", this.getName());
        //Collection employees = queryEmployeesByFirstName.getResultList();
        //List<Czytelnicy> ll = new ArrayList(employees);
        selected = items.get(0);

        //listaMoichRezerwacjiDoWyswietlenia.clear();
        allBooks = new ArrayList<>();
        int i = 0;
        for (Publikacje item1 : items) {
            i++;

            Query q = getFacade().getEntityManager().createNamedQuery("Egzemplarze.findByIDpublikacji");
            q.setParameter("iPublikacji", item1);

            //lista na egzemplarze
            //sprawdza czy nie jest wypożyczony
            List<Egzemplarze> ll = new ArrayList(q.getResultList());

            for (Iterator<Egzemplarze> iter = ll.listIterator(); iter.hasNext();) {
                Egzemplarze sq = iter.next();
                Query q1 = getFacade().getEntityManager().createNamedQuery("Wypozyczenia.findByIDegzemplarza");
                q1.setParameter("iEgzemplarza", sq);
                if (q1.getResultList().size() > 0) {
                    iter.remove();
                }
            }

            //wrzuca do listy do wyświetlenia
            allBooks.add(new Books(
                    String.valueOf(i),
                    item1.getTytul(),
                    item1.getIdautora().getImie() + " " + item1.getIdautora().getNazwisko(),
                    item1.getIdkategorii().getKategoria(),
                    item1.getIdwydawcy().getWydawca(),
                    item1.getRok(),
                    String.valueOf(ll.size()),
                    ll.size(),
                    String.valueOf(item1.getIdpublikacji())
            ));
        };
        return allBooks;
    }

    public Publikacje getSelected() {
        return selected;
    }

    public void setSelected(Publikacje selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PublikacjeFacade getFacade() {
        return ejbFacade;
    }

    public Publikacje prepareCreate() {
        selected = new Publikacje();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PublikacjeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PublikacjeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PublikacjeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Publikacje> getItems() {
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

    public List<Publikacje> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Publikacje> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Publikacje.class)
    public static class PublikacjeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PublikacjeController controller = (PublikacjeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "publikacjeController");
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
            if (object instanceof Publikacje) {
                Publikacje o = (Publikacje) object;
                return getStringKey(o.getIdpublikacji());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Publikacje.class.getName()});
                return null;
            }
        }

    }

}

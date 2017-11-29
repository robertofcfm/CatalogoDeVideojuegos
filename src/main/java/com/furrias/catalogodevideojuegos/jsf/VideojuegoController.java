package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Etiqueta;
import com.furrias.catalogodevideojuegos.entidad.Saga;
import com.furrias.catalogodevideojuegos.entidad.Videojuego;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil.PersistAction;
import com.furrias.catalogodevideojuegos.sessionBean.VideojuegoFacade;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named("videojuegoController")
@SessionScoped
public class VideojuegoController implements Serializable {

    @EJB
    private com.furrias.catalogodevideojuegos.sessionBean.VideojuegoFacade ejbFacade;
    private List<Videojuego> items = null;
    private Videojuego selected;
    protected int first = 0;

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public int getFirst() {
        return first;
    }
    
    public void setFirst(int first){
        this.first = first;
    }
    
    public VideojuegoController() {
    }

    public Videojuego getSelected() {
        return selected;
    }

    public void setSelected(Videojuego selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VideojuegoFacade getFacade() {
        return ejbFacade;
    }

    public Videojuego prepareCreate() {
        selected = new Videojuego();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VideojuegoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideojuegoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VideojuegoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Videojuego> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            Collections.sort(items, new CompararVideojuegos());
        }
        return items;
    }
    
        public String filtrarSagas(List<Saga> varSagas) {
        String resultado = "";
        for (Saga elemento : varSagas) {
            resultado += " " + elemento.getSaga();
        }
        return resultado;
    }

    public String ordenarSagas(List<Saga> varSagas) {
        String resultado = "";
        for (Saga elemento : varSagas) {
            resultado += " " + elemento.getSaga();
        }
        return resultado;
    }

    public String filtrarEtiquetas(List<Etiqueta> varEtiquetas) {
        String resultado = "";
        for (Etiqueta elemento : varEtiquetas) {
            resultado += " " + elemento.getEtiqueta();
        }
        return resultado;
    }

    public String ordenarEtiquetas(List<Etiqueta> varEtiquetas) {
        String resultado = "";
        for (Etiqueta elemento : varEtiquetas) {
            resultado += " " + elemento.getEtiqueta();
        }
        return resultado;
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

    public Videojuego getVideojuego(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Videojuego> getItemsAvailableSelectMany() {
        List<Videojuego> listVideojuegos = getFacade().findAll();
        Collections.sort(listVideojuegos, new CompararVideojuegos());
        return listVideojuegos;
    }

    public List<Videojuego> getItemsAvailableSelectOne() {
        List<Videojuego> listVideojuegos = getFacade().findAll();
        Collections.sort(listVideojuegos, new CompararVideojuegos());
        return listVideojuegos;
    }

    private class CompararVideojuegos implements Comparator<Videojuego> {

        @Override
        public int compare(Videojuego o1, Videojuego o2) {
            return o1.getVideojuego().compareTo(o2.getVideojuego());
        }
    }

    @FacesConverter(forClass = Videojuego.class)
    public static class VideojuegoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VideojuegoController controller = (VideojuegoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "videojuegoController");
            return controller.getVideojuego(getKey(value));
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
            if (object instanceof Videojuego) {
                Videojuego o = (Videojuego) object;
                return getStringKey(o.getIdVideojuego());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Videojuego.class.getName()});
                return null;
            }
        }

    }

}

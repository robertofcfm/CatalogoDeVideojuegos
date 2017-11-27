package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Plataforma;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil.PersistAction;
import com.furrias.catalogodevideojuegos.sessionBean.PlataformaFacade;

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

@Named("plataformaController")
@SessionScoped
public class PlataformaController implements Serializable {

    @EJB
    private com.furrias.catalogodevideojuegos.sessionBean.PlataformaFacade ejbFacade;
    private List<Plataforma> items = null;
    private Plataforma selected;

    public PlataformaController() {
    }

    public Plataforma getSelected() {
        return selected;
    }

    public void setSelected(Plataforma selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlataformaFacade getFacade() {
        return ejbFacade;
    }

    public Plataforma prepareCreate() {
        selected = new Plataforma();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlataformaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlataformaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlataformaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Plataforma> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            Collections.sort(items, new CompararPlataformas());
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

    public Plataforma getPlataforma(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Plataforma> getItemsAvailableSelectMany() {
        List<Plataforma> listPlataformas = getFacade().findAll();
        Collections.sort(listPlataformas, new CompararPlataformas());
        return listPlataformas;
    }

    public List<Plataforma> getItemsAvailableSelectOne() {
        List<Plataforma> listPlataformas = getFacade().findAll();
        Collections.sort(listPlataformas, new CompararPlataformas());
        return listPlataformas;
    }

    private class CompararPlataformas implements Comparator<Plataforma> {

        @Override
        public int compare(Plataforma o1, Plataforma o2) {
            return o1.getPlataforma().compareTo(o2.getPlataforma());
        }
    }

    @FacesConverter(forClass = Plataforma.class)
    public static class PlataformaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlataformaController controller = (PlataformaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "plataformaController");
            return controller.getPlataforma(getKey(value));
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
            if (object instanceof Plataforma) {
                Plataforma o = (Plataforma) object;
                return getStringKey(o.getIdPlataforma());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Plataforma.class.getName()});
                return null;
            }
        }

    }

}

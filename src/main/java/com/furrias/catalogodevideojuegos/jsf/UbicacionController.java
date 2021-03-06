package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Ubicacion;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil.PersistAction;
import com.furrias.catalogodevideojuegos.sessionBean.UbicacionFacade;

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

@Named("ubicacionController")
@SessionScoped
public class UbicacionController implements Serializable {

    @EJB
    private com.furrias.catalogodevideojuegos.sessionBean.UbicacionFacade ejbFacade;
    private List<Ubicacion> items = null;
    private Ubicacion selected;

    public UbicacionController() {
    }

    public Ubicacion getSelected() {
        return selected;
    }

    public void setSelected(Ubicacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UbicacionFacade getFacade() {
        return ejbFacade;
    }

    public Ubicacion prepareCreate() {
        selected = new Ubicacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UbicacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UbicacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UbicacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ubicacion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            Collections.sort(items, new CompararUbicaciones());
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

    public Ubicacion getUbicacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ubicacion> getItemsAvailableSelectMany() {
        List<Ubicacion> listUbicaciones = getFacade().findAll();
        Collections.sort(listUbicaciones, new CompararUbicaciones());
        return listUbicaciones;
    }

    public List<Ubicacion> getItemsAvailableSelectOne() {
        List<Ubicacion> listUbicaciones = getFacade().findAll();
        Collections.sort(listUbicaciones, new CompararUbicaciones());
        return listUbicaciones;
    }

    private class CompararUbicaciones implements Comparator<Ubicacion> {

        @Override
        public int compare(Ubicacion o1, Ubicacion o2) {
            return o1.getUbicacion().compareTo(o2.getUbicacion());
        }
    }

    @FacesConverter(forClass = Ubicacion.class)
    public static class UbicacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UbicacionController controller = (UbicacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ubicacionController");
            return controller.getUbicacion(getKey(value));
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
            if (object instanceof Ubicacion) {
                Ubicacion o = (Ubicacion) object;
                return getStringKey(o.getIdUbicacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ubicacion.class.getName()});
                return null;
            }
        }

    }

}

package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Saga;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil.PersistAction;
import com.furrias.catalogodevideojuegos.sessionBean.SagaFacade;

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

@Named("sagaController")
@SessionScoped
public class SagaController implements Serializable {

    @EJB
    private com.furrias.catalogodevideojuegos.sessionBean.SagaFacade ejbFacade;
    private List<Saga> items = null;
    private Saga selected;

    public SagaController() {
    }

    public Saga getSelected() {
        return selected;
    }

    public void setSelected(Saga selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SagaFacade getFacade() {
        return ejbFacade;
    }

    public Saga prepareCreate() {
        selected = new Saga();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SagaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SagaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SagaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Saga> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            Collections.sort(items, new CompararSagas());
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

    public Saga getSaga(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Saga> getItemsAvailableSelectMany() {
        List<Saga> listSagas = getFacade().findAll();
        Collections.sort(listSagas, new CompararSagas());
        return listSagas;
    }

    public List<Saga> getItemsAvailableSelectOne() {
        List<Saga> listSagas = getFacade().findAll();
        Collections.sort(listSagas, new CompararSagas());
        return listSagas;
    }

    private class CompararSagas implements Comparator<Saga> {

        @Override
        public int compare(Saga o1, Saga o2) {
            return o1.getSaga().compareTo(o2.getSaga());
        }
    }

    @FacesConverter(forClass = Saga.class)
    public static class SagaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SagaController controller = (SagaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sagaController");
            return controller.getSaga(getKey(value));
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
            if (object instanceof Saga) {
                Saga o = (Saga) object;
                return getStringKey(o.getIdSaga());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Saga.class.getName()});
                return null;
            }
        }

    }

}

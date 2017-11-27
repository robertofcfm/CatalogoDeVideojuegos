package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Etiqueta;
import com.furrias.catalogodevideojuegos.entidad.Grupoetiqueta;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil;
import com.furrias.catalogodevideojuegos.jsf.util.JsfUtil.PersistAction;
import com.furrias.catalogodevideojuegos.sessionBean.EtiquetaFacade;

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

@Named("etiquetaController")
@SessionScoped
public class EtiquetaController implements Serializable {

    @EJB
    private com.furrias.catalogodevideojuegos.sessionBean.EtiquetaFacade ejbFacade;
    private List<Etiqueta> items = null;
    private Etiqueta selected;

    public EtiquetaController() {
    }

    public Etiqueta getSelected() {
        return selected;
    }

    public void setSelected(Etiqueta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EtiquetaFacade getFacade() {
        return ejbFacade;
    }

    public Etiqueta prepareCreate() {
        selected = new Etiqueta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EtiquetaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EtiquetaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EtiquetaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Etiqueta> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            Collections.sort(items, new CompararEtiquetas());
        }
        return items;
    }

    public String filtrarGrupos(List<Grupoetiqueta> varEtiquetas) {
        String resultado = "";
        for (Grupoetiqueta elemento : varEtiquetas) {
            resultado += " " + elemento.getGrupoEtiqueta();
        }
        return resultado;
    }

    public String ordenarGrupos(List<Grupoetiqueta> varEtiquetas) {
        String resultado = "";
        for (Grupoetiqueta elemento : varEtiquetas) {
            resultado += " " + elemento.getGrupoEtiqueta();
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

    public Etiqueta getEtiqueta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Etiqueta> getItemsAvailableSelectMany() {
        List<Etiqueta> listEtiquetas = getFacade().findAll();
        Collections.sort(listEtiquetas, new CompararEtiquetas());
        return listEtiquetas;
    }

    public List<Etiqueta> getItemsAvailableSelectOne() {
        List<Etiqueta> listEtiquetas = getFacade().findAll();
        Collections.sort(listEtiquetas, new CompararEtiquetas());
        return listEtiquetas;
    }

    private class CompararEtiquetas implements Comparator<Etiqueta> {

        @Override
        public int compare(Etiqueta o1, Etiqueta o2) {
            return o1.getEtiqueta().compareTo(o2.getEtiqueta());
        }
    }

    @FacesConverter(forClass = Etiqueta.class, value = "converterEtiqueta")
    public static class EtiquetaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EtiquetaController controller = (EtiquetaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "etiquetaController");
            return controller.getEtiqueta(getKey(value));
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
            if (object instanceof Etiqueta) {
                Etiqueta o = (Etiqueta) object;
                return getStringKey(o.getIdEtiqueta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Etiqueta.class.getName()});
                return null;
            }
        }

    }

}

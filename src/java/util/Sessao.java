package util;

import Controlador.TerapeutaBean;
import javax.faces.context.FacesContext;
import Modelo.Terapeuta;

public class Sessao {

    public static Terapeuta obterTerapeutaLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        TerapeutaBean terapeutaBean = (TerapeutaBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "terapeutaBean");
        if (terapeutaBean != null) {
            return terapeutaBean.getTerapeutaLogado();
        }
        return null;
    }

}

package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagens {

    public static void adicionarMensagem(Severity sev, String msg, String componente) {
        FacesMessage fm = new FacesMessage(sev, msg, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(componente, fm);
    }
}

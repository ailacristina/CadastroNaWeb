package util;

import Controlador.TerapeutaBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class FiltroAutenticacao implements PhaseListener {

    private List<String> allowedPages = new ArrayList<String>();

    public FiltroAutenticacao() {
        allowedPages.add("/index.xhtml");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        if (!allowedPages.contains(viewId)) {
            if (Sessao.obterTerapeutaLogado() == null) {
                NavigationHandler navigator = context
                        .getApplication()
                        .getNavigationHandler();
                Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
                        "Terapeuta não logado!", null);
                navigator.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
            }
        } else {
            if (Sessao.obterTerapeutaLogado() != null) {
                NavigationHandler navigator = context
                        .getApplication()
                        .getNavigationHandler();
                navigator.handleNavigation(context, null, "/PaginaInicial.xhtml?faces-redirect=true");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}

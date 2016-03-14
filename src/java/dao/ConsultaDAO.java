package dao;

import java.util.List;
import Modelo.Consulta;

public interface ConsultaDAO {

    public void salvar(Consulta consulta);

    public List<Consulta> todos(Long terapeutaId);

    public void concluir(Long consultaId);
}

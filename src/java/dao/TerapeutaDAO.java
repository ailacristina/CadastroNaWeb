package dao;

import Modelo.Terapeuta;

public interface TerapeutaDAO {

    public void salvar(Terapeuta terapeuta);

    public boolean existeLogin(String login);

    public Terapeuta porLogineSenha(String login, String senha);
}

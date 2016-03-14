/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class DataBean implements Serializable{

    public Date getDataAtual() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }
}

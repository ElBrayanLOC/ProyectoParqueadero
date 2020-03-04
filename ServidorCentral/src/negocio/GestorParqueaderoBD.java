package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestorParqueaderoBD {
    private  conectorJDBC conector;

    public GestorParqueaderoBD() {
        conector = conector.getConnection();
    }
    public void registrarIngreso(Ingreso prmIngreso) throws ClassNotFoundException, SQLException{
        String sql1, sql2;
        String vehplaca = prmIngreso.getVehPlaca();
        String bahId = prmIngreso.getBahId();
        String fecIngre = prmIngreso.getFechaIngreso();
        String fecSali = prmIngreso.getFechaSalida();
        conector.conectarse();
        sql1 = "INSERT INTO INGRESO (VEHPLACA,BAHID,INGFECHAINGRESO,INGFECHASALIDA) VALUES ('" + vehplaca + "','"+bahId+"','"+fecIngre+"','"+fecSali+"')"; 
        
        sql2 = "UPDATE BAHIA set BAHESTADO= 1 where BAHID= '"+bahId+"'";
        conector.actualizar(sql1);
        conector.actualizar(sql2);
        conector.desconectarse();
    }
    
    public void registrarSalida(String bahId, String fecSalida) throws ClassNotFoundException, SQLException{
        String sql1, sql2;
        conector.conectarse();
        sql1 = "UPDATE INGRESO set INGFECHASALIDA= '"+fecSalida+"' where BAHID= "+Integer.parseInt(bahId); 
        
        sql2 = "UPDATE BAHIA set BAHESTADO= 0 where BAHID= "+Integer.parseInt(bahId);
        conector.actualizar(sql1);
        conector.actualizar(sql2);
        conector.desconectarse();
    }
     public ArrayList<Bahia> buscarBahias() throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        conector.crearConsulta("select bahId,zonId,bahEstado from BAHIA order by bahId");

        ArrayList<Bahia> Bahias = new ArrayList();
        while (conector.getResultado().next()) {
            Bahia bah = new Bahia(conector.getResultado().getInt("bahId"), conector.getResultado().getInt("zonId"), conector.getResultado().getInt("bahEstado"));
            Bahias.add(bah);
        }
        conector.desconectarse();
        return Bahias;
    }
}

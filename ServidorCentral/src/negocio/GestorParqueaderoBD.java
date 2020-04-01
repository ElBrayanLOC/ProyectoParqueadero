package negocio;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GestorParqueaderoBD {
    private  conectorJDBC conector;

    public GestorParqueaderoBD() {
        conector = conector.getConnection();
    }
    public void registrarIngreso(Ingreso prmIngreso) throws ClassNotFoundException, SQLException, ParseException{
        String sql1, sql2;
        String vehplaca = prmIngreso.getVehPlaca();
        String bahId = prmIngreso.getBahId();
        Date fecha;
        String fecIngre = prmIngreso.getFechaIngreso();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fecha = date.parse(fecIngre);
        String fecSali = prmIngreso.getFechaSalida();
        conector.conectarse();
        sql1 = "INSERT INTO INGRESO (VEHPLACA,BAHID,INGFECHAINGRESO) VALUES ('" + vehplaca + "','"+bahId+"','"+fecha+"')"; 
        
        sql2 = "UPDATE BAHIA set BAHESTADO= 1 where BAHID= '"+bahId+"'";
        conector.actualizar(sql1);
        conector.actualizar(sql2);
        conector.desconectarse();
    }
    
    public void registrarSalida(String bahId, String fecSalida) throws ClassNotFoundException, SQLException, ParseException{
        String sql1, sql2;
        conector.conectarse();
        Date fecha;
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fecha = date.parse(fecSalida);
        sql1 = "UPDATE INGRESO set INGFECHASALIDA= '"+fecha+"' where BAHID= "+Integer.parseInt(bahId); 
        
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
    public ArrayList<Reporte> reporteIngreso() throws ClassNotFoundException, SQLException{
        String sql = "SELECT CONCAT( EXTRACT(HOUR FROM ingfechaingreso), ' to ', CONCAT( extract (HOUR from ingfechaingreso), ':59:59' ) ) as hora,COUNT(*) as numIngresos" 
                + " FROM ingreso GROUP BY DATE(ingfechaingreso), extract (HOUR from ingfechaingreso)"
                + " ORDER BY DATE (ingfechaingreso), extract (HOUR from ingfechaingreso)";
        conector.conectarse();
        conector.crearConsulta(sql);
        ArrayList<Reporte> reporte = new ArrayList();
        while (conector.getResultado().next()) {
            Reporte rep = new Reporte(conector.getResultado().getInt("numIngresos"), conector.getResultado().getString("hora"));
            reporte.add(rep);
        }
        conector.desconectarse();
        return reporte;
    }
    public ArrayList<Reporte> verReporte(String prmPlaca) throws ClassNotFoundException, SQLException{
        String sql = "select count(*) as cantidad, extract (week from ingfechaingreso) as fecha,vehplaca as placa from ingreso where extract (week from ingfechaingreso) between extract (week from current_date)-3 and extract (week from current_date) and vehplaca= '"+prmPlaca+"' group by fecha,placa order by fecha";
        System.out.println(sql);
        conector.conectarse();
        conector.crearConsulta(sql);
        ArrayList<Reporte> listare = new ArrayList();
        while(conector.getResultado().next()){
            Reporte re = new Reporte(conector.getResultado().getString("cantidad"), conector.getResultado().getString("fecha"),conector.getResultado().getString("placa"));
            listare.add(re);
        }
        conector.desconectarse();
        return listare;
    }
}

package acceso;

import negocio.Ingreso;

public interface IServicioIngreso {
    public String registrarIngreso(String prmPlaca, String bahId, String fechaEntrada, String fechaSalida);
    public String registrarSalida(int bahId, String prmFechaSalida);
    public String buscarBahias();
}

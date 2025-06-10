package Domain;
import java.io.Serializable;
import java.util.*;


public class Pokemon implements Serializable {
    private final String nombre;
    private final String tipo;
    private final String descripcion;
    private int ps;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int ataqueEspecial;
    private int defensaEspecial;
    private Boolean estado;
    private ArrayList<Movimiento> movimientos;
    private final int maxPS;
    private String fotoFrontal;
    private String fotoBack;
    private String icono;
    private final EfectividadesTipo efectividadesTipo;




    public Pokemon(String nombre, String tipo, String descripcion, int ps, int ataque, int defensa, int velocidad, int ataqueEspecial, int defensaEspecial, String fotoFrontal,String fotoBack,String icono) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.ps = ps;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.maxPS = ps;
        this.estado = true;
        this.movimientos = new ArrayList<>();
        this.fotoFrontal = fotoFrontal;
        this.fotoBack = fotoBack;
        this.icono = icono;
        this.efectividadesTipo = crearEfectividadesTipo(tipo);
        crearMovimientos();
    }

    private void crearMovimientos() {
        try{
            String nombreClase = "Domain.Tipo" + tipo;
            Class<?> clase = Class.forName(nombreClase);
           EfectividadesTipo claseTipo =  (EfectividadesTipo) clase.getDeclaredConstructor().newInstance();
            this.movimientos.addAll(claseTipo.movimientosPredeterminados());
            this.movimientos.add(new Movimiento("Embestida", "Normal", "Físico", 40, 100, 35,null));
        }catch(Exception e){
            throw new InvalidPokeTypeClass("No se pudo instanciar la clase de tipo para: " + tipo,e);
        }
    }


    private EfectividadesTipo crearEfectividadesTipo(String tipo) {
        try {
            String nombreClase = "Domain.Tipo" + tipo;
            Class<?> clase = Class.forName(nombreClase);
            return (EfectividadesTipo) clase.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new InvalidPokeTypeClass("No se pudo instanciar la clase de tipo para: " + tipo, e);
        }
    }



    public double getEfectividadContra(String tipoDefensor) {
        return efectividadesTipo.obtenerEfectividadContra(tipoDefensor);
    }

    public EfectividadesTipo getEfectividadesTipo() {
        return efectividadesTipo;
    }

    public Map<String, Double> getEfectividades() {
        return efectividadesTipo.definirEfectividades();
    }



    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPs() {
        return ps;
    }
    public int getAtaque() {
        return ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
    public int getDefensaEspecial() {
        return defensaEspecial;
    }
    public Boolean getEstado() {
        return estado;
    }
    public int getMaxPs() {
        return maxPS;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getFotoFrontal() {
        return fotoFrontal;
    }
    public String getFotoBack() {
        return fotoBack;
    }
    public String getIcono() {
        return icono;
    }


    public void RecibirDano(int dano){
        this.ps -= dano;
        if(ps <= 0){
            estado = false;
        }
    }

    public void AumentarPs(int vida){
        if(vida + ps > maxPS) {
            this.ps += maxPS-ps;
        }else{
            this.ps += vida;
        }
    }

    public void AumentarAtaque(int atk){
        ataque += atk;
    }

    public void bajarAtaque(int atk){
        ataque -= atk;
    }

    public void AumentarDefensa(int dfs){
        this.defensa += dfs;
    }

    public void bajarDefensa(int dfs){
        this.defensa -= dfs;
    }

    public void AumentarAtaqueEspecial(int atk){
        ataqueEspecial += atk;
    }

    public void bajarAtaqueEspecial(int atk){
        ataqueEspecial -= atk;
    }

    public void AumentarDefensaEspecial(int dfs){
        defensaEspecial += dfs;
    }

    public void bajarDefensaEspecial(int dfs){
        defensaEspecial -= dfs;
    }

    public ArrayList<Movimiento> getPokeMoves(){
        if(this.movimientos==null || this.movimientos.isEmpty() || this.movimientos.size()>4){
            throw new InvalidMove("Movimientos del poke no validos");
        }

        return this.movimientos;
    }

    public void cambiarMovimiento(Movimiento movimientoNuevo, Movimiento movimientoActual){
        if(movimientoActual == null || movimientoNuevo == null){
            throw new InvalidMove("Movimientos de cambios no validos");
        }
        this.movimientos.remove(movimientoActual);
        this.movimientos.add(movimientoNuevo);
    }


}
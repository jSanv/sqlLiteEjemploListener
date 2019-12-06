package entidades;

public class productos {

    private int codigo;
    private String nombre;
    private Double precio;

    public productos() {
    }

    public productos(int codigo, String nombre, Double precio) {
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setPrecio(precio);
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}

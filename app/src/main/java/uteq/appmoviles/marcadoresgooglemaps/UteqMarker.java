package uteq.appmoviles.marcadoresgooglemaps;

public class UteqMarker {
    private int id;
    private double latitud;
    private String logo_url;
    private double longitud;
    private String nom_decano;
    private String nom_fac;
    private String ubicacion;

    public UteqMarker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNom_decano() {
        return nom_decano;
    }

    public void setNom_decano(String nom_decano) {
        this.nom_decano = nom_decano;
    }

    public String getNom_fac() {
        return nom_fac;
    }

    public void setNom_fac(String nom_fac) {
        this.nom_fac = nom_fac;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

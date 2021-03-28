package com.brikton.labapps.msusuario.domain;

public class TipoObra {
    
    private Integer id;
    private String descripcion;
    
    /**
     * porfa usar bien esto, no tengo ganas de validar
     * REFORMA CASA EDIFICIO VIAL   
     * @param descripcion
     */
    public TipoObra(String descripcion) {
        this.descripcion = descripcion;

        switch (descripcion) {
            case "REFORMA": {
                this.id = 1;
                break;
            }
            case "CASA": {
                this.id = 2;
                break;
            }
            case "EDIFICIO": {
                this.id = 3;
                break;
            }
            case "VIAL": {
                this.id = 4;
                break;
            }
            default : this.id = 0;
        }
    }

    public Integer getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * porfa usar bien esto, no tengo ganas de validar
     * REFORMA CASA EDIFICIO VIAL   
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    
        switch (descripcion) {
            case "REFORMA": {
                this.id = 1;
                break;
            }
            case "CASA": {
                this.id = 2;
                break;
            }
            case "EDIFICIO": {
                this.id = 3;
                break;
            }
            case "VIAL": {
                this.id = 4;
                break;
            }
            default : this.id = 0;
        }
    }
    
}

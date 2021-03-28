package com.brikton.labapps.msusuario.domain;

public class TipoUsuario {
    
    private Integer id;
    private String tipo;

    /**
     * porfa usar bien esto, no tengo ganas de validar
     * CLIENTE VENDEDOR   
     * @param tipo
     */
    public TipoUsuario(String tipo) {
        this.tipo = tipo;

        switch (tipo) {
            case "CLIENTE" : {
                this.id = 1;
                break;
            }
            case "VENDEDOR" : {
                this.id = 2;
                break;
            }
            default: {
                this.id = 0;
            };
        }    
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
    /**
     * porfa usar bien esto, no tengo ganas de validar
     * CLIENTE VENDEDOR   
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;

        switch (tipo) {
            case "CLIENTE" : {
                this.id = 1;
                break;
            }
            case "VENDEDOR" : {
                this.id = 2;
                break;
            }
            default: {
                this.id = 0;
            };
        }
    }

    public TipoUsuario(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }    
}

package br.com.caelum.estoque.modelo.item;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
//é um pequeno wrapper para embrulhar um filtro e deixar o XML mais expressivo:
public class Filtros {

    @XmlElement(name = "filtro")
    private List<Filtro> filtros;

    public Filtros(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    Filtros() {
    }

    public List<Filtro> getLista() {
        return filtros;
    }

}

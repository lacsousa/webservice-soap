package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.*;

@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens") // Anotações que melhoram o resultado do WSDL
    @WebResult(name = "item") // Anotações do JAX-B ( Binding do XML para objetos do Java
    // WebResult configura o retorno no método e WebMethod muda o nome da operação
    public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");

        List<Filtro> listaFiltros = filtros.getLista();
        List<Item> itemList = dao.todosItens(listaFiltros);
        return new ListaItens(itemList);
    }

    @WebMethod(operationName = "CadastrarItem")
    @WebResult(name = "item")
    public Item cadastrarItem(@WebParam(name = "item") Item item) {

        System.out.println("Cadastrando Item: " + item);
        this.dao.cadastrar(item);
        return item;
    }
}
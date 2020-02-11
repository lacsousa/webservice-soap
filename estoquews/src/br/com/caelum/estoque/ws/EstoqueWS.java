package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens") // Anotações que melhoram o resultado do WSDL
    @WebResult(name = "itens") // Anotações do JAX-B ( Binding do XML para objetos do Java
    // WebResult configura o retorno no método e WebMethod muda o nome da operação
    //    public List<Item> getItens() {
    public ListaItens getItens() {

        System.out.println("Chamando getItens()");

//     return dao.todosItens();
        List<Item> itemList = dao.todosItens();
        return new ListaItens(itemList);
    }

}
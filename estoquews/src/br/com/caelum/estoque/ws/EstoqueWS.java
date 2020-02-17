package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

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
    public Item cadastrarItem(@WebParam(name = "token", header = true) TokenUsuario token, @WebParam(name = "item") Item item)
            throws AutorizacaoException {

        boolean tokenValido = new TokenDao().ehValido(token);
        if (!tokenValido){
            System.out.println("Não foi possível cadastrar o item. Autorização falhou !!!" + "Token: " + token );
            throw new AutorizacaoException("Autorização falhou !!!");
        }

        new ItemValidador(item).validate();

        System.out.println("Cadastrando Item: " + item + ", Token: " + token);
        this.dao.cadastrar(item);
        return item;
    }
}
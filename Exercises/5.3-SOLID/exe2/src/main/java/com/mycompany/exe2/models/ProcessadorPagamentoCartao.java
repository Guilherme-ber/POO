package com.mycompany.exe2.models;

// Interfaces
import com.mycompany.exe2.models.interfaces.IProcessadorPagamento;

/**
 *
 * @author guilherme
 */
public class ProcessadorPagamentoCartao implements IProcessadorPagamento {
    @Override
    public void processarPagamento () {
        System.out.println("Pagando com Cartao");
    }
}

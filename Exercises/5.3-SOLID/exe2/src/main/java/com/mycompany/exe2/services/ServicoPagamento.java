package com.mycompany.exe2.services;

// Interfaces
import com.mycompany.exe2.models.interfaces.IProcessadorPagamento;

/**
 *
 * @author guilherme
 */
public class ServicoPagamento {
    private IProcessadorPagamento processador;
    
    public ServicoPagamento(IProcessadorPagamento processador) {
        this.processador = processador;
    }
    
    public void realizarPagamento() {
        processador.processarPagamento();
    }
}

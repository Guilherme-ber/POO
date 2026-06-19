package com.mycompany.exe2;

// Interfaces
import com.mycompany.exe2.models.interfaces.IProcessadorPagamento;

// Services
import com.mycompany.exe2.services.ServicoPagamento;

// Models
import com.mycompany.exe2.models.*;

/**
 *
 * @author guilherme
 */
public class Main {
    public static void main(String[] args) {
        IProcessadorPagamento proce = new ProcessadorPagamentoCartao();
        
        ServicoPagamento servico = new ServicoPagamento(proce);
        servico.realizarPagamento();
    }
}

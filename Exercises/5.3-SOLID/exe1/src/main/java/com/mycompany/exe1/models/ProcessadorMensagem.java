package com.mycompany.exe1.models;

// Interfaces
import com.mycompany.exe1.models.interfaces.IEnviadorMensagem;

/**
 *
 * @author guilherme
 */
public class ProcessadorMensagem {
    private final IEnviadorMensagem enviador;

    public ProcessadorMensagem(IEnviadorMensagem enviador) {
        this.enviador = enviador;
    }

    public void processar() {
        enviador.enviarMensagem();
    }
}

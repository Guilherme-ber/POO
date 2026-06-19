package com.mycompany.exe1.models;

// Interfaces
import com.mycompany.exe1.models.interfaces.IEnviadorMensagem;

/**
 *
 * @author guilherme
 */
public class EnviadorEmail implements IEnviadorMensagem {
    @Override
    public void enviarMensagem() {
        System.out.println("Enviando mensagem por EMAIL");
    }
}

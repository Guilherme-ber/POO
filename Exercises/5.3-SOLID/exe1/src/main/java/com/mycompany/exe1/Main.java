package com.mycompany.exe1;

// Models
import com.mycompany.exe1.models.*;

// Interfaces
import com.mycompany.exe1.models.interfaces.IEnviadorMensagem;

/**
 *
 * @author guilherme
 */
public class Main {
    public void main(String args[]) {
        IEnviadorMensagem envi = new EnviadorSMS();

        ProcessadorMensagem processador = new ProcessadorMensagem(envi);
        processador.processar();
    }
}

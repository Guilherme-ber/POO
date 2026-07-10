package com.mycompany.main.model.validations;

/**
 *
 * @author guilh
 */
public class BaseValidator {
    protected void validateBaseData(int id, String name, char sex, int age, String cpf) throws Exception {
        if(name.isEmpty()) throw new Exception("Error - Campo vazio: 'Nome'");
        
        if(sex == '\0') throw new Exception("Error - Campo vazio: 'Sexo'");
        if(sex != 'M' && sex != 'F') throw new Exception("Error - Campo vazio: 'Sexo'");
        
        if(age <= 0) throw new Exception("Error - Idade invalida");
        
        if(cpf.isEmpty()) throw new Exception("Error - Campo vazio: 'CPF'");
    }
}

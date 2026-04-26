package com.mycompany.project.enterprise;

// Models
import com.mycompany.project.enterprise.model.*;

/**
 *
 * @author guilh
 */
public class Main {
    public static void main(String[] args) {
        Enterprise enterprise = new Enterprise("Tech Solutions");

        Employee e1 = new Employee("F001", "Ana");
        Employee e2 = new Employee("F002", "Carlos");
        enterprise.addEmployee(e1);
        enterprise.addEmployee(e2);

        Project p1 = new Project("P001", "Desenvolvimento de Software", "Projeto para desenvolvimento de um aplicativo.");
        Project p2 = new Project("P002", "Pesquisa de Mercado", "Projeto para realizar uma pesquisa de mercado.");
        enterprise.addProject(p1);
        enterprise.addProject(p2);

        p1.addEmployee(e1);
        p2.addEmployee(e1); // Ana está em dois projetos
        p1.addEmployee(e2); // Carlos está em um projeto

        System.out.println("Funcionario mais produtivo: " + enterprise.employeeMoreProductive().getName());
        System.out.println("Funcionario menos produtivo: " + enterprise.employeeLessProductive().getName());
    }
}

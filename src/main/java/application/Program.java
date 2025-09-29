package application;

import dao.EmployeeDao;
import dao.factory.DaoFactory;
import dao.impl.EmployeeDaoImplJDBC;
import model.Departament;
import model.Employee;
import util.DB;

import java.sql.Connection;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Connection conn = DB.getConnection();
        EmployeeDao emp = DaoFactory.criarEmployeeDaoImpl();
        int opcao, departamentId;
        String nome, email;


                do {
                    System.out.println("===== MENU =====");
                    System.out.println("1 - Create");
                    System.out.println("2 - Update");
                    System.out.println("3 - Delete");
                    System.out.println("4 - Find by ID");
                    System.out.println("5 - Find All");
                    System.out.println("6 - Find by Department");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha uma opção: ");

                     opcao = input.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Executando Create...");
                            System.out.print("Name: ");
                             nome = input.nextLine();
                            System.out.print("Email: ");
                            email = input.nextLine();
                            System.out.print("Departament Id: ");
                            departament = input.nextInt();
                            input.nextLine();

                            Employee employee = new Employee(nome, email, departament);
                            emp.create(employee);
                            break;
                        case 2:
                            System.out.println("Executando Update...");

                            break;
                        case 3:
                            System.out.println("Executando Delete...");

                            break;
                        case 4:
                            System.out.println("Executando Find by ID...");

                            break;
                        case 5:
                            System.out.println("Executando Find All...");

                            break;
                        case 6:
                            System.out.println("Executando Find by Department...");

                            break;
                        case 0:
                            System.out.println("Saindo do programa...");
                            break;
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                    }

                    System.out.println();
                } while (opcao != 0);
        input.close();
            }
        }




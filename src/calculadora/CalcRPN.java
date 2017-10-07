/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.io.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
        
public class CalcRPN {

    // vari´aveis da instancia :
    // uma pilha para os c´alculos
    // uma pilha para registrar o historico
    Pilha<Double> aPilha;
    Pilha<Operacao> hist;

    // construtor
    CalcRPN() {
        aPilha = new Pilha();
        hist = new Pilha();
    }

    // Adi¸c~ao de dois elementos do topo da pilha
    void mais() {
        double a, b;
        if (aPilha.size() >= 2) {
            a = aPilha.desempilha();
            b = aPilha.desempilha();
            aPilha.empilha(a + b);
        } else {
            throw new Error("a ser completado");
        }
        hist.empilha(new Operacao(a, b, '+'));
    }

    // Subtra¸c~ao de dois elementos do topo da pilha
    void menos() {
        double a, b;
        if (aPilha.size() >= 2) {
            a = aPilha.desempilha();
            b = aPilha.desempilha();
            aPilha.empilha(b - a);
        } else {
            throw new Error("a ser completado");
        }
        hist.empilha(new Operacao(a, b, '-'));
    }

    // Multiplica¸c~ao de dois elementos do topo da pilha
    void vezes() {
        double a, b;
        if (aPilha.size() >= 2) {
            a = aPilha.desempilha();
            b = aPilha.desempilha();
            aPilha.empilha(a * b);
        } else {
            throw new Error("a ser completado");
        }
        hist.empilha(new Operacao(a, b, '*'));
    }

    // Divis~ao de dois elementos no topo da pilha
    void dividido() {
        double a, b;
        if (aPilha.size() >= 2) {
            a = aPilha.desempilha();
            b = aPilha.desempilha();
            aPilha.empilha(b / a);
        } else {
            throw new Error("a ser completado");
        }
        hist.empilha(new Operacao(a, b, '/'));
    }

    // retorna o conteudo do topo da pilha
    Double resultado() {
        if (!aPilha.estaVazia()) {
            return aPilha.topo();
        } else {
            throw new Error("a ser completado");
        }
    }

    // interpretador de comandos
    public void exec(String cmd) {
        try {
            Double valor = Double.parseDouble(cmd);
            aPilha.empilha(valor);
            hist.empilha(new Operacao(valor));
        } catch (Exception ex) {
            if (cmd.equals("+")) {
                mais();
            } else if (cmd.equals("-")) {
                menos();
            } else if (cmd.equals("*")) {
                vezes();
            } else if (cmd.equals("/")) {
                dividido();
            } else if (cmd.equals("hist")) {
                System.out.println("Historico " + hist.toStringInverse());
            } else if (cmd.equals("clear")) {
                aPilha.reinicialize();
                hist.reinicialize();
            }
            else if (cmd.equals("undo")) {
                cancela();
            }
        }
    }

    public void cancela(){
        if(hist.topo().code == 'e'){
            aPilha.desempilha();
        }
        else{
            aPilha.desempilha();
            aPilha.empilha(hist.topo().b);
            aPilha.empilha(hist.topo().a);
        }
        hist.desempilha();
    }
    
    static void test() {
        CalcRPN calc = new CalcRPN();
        System.out.print("3 2 + = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 - = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.menos();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 * = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.vezes();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 / = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.dividido();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("1 2 + 3 4 - / 10 3 - * = ");
        calc.aPilha.empilha(1.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(4.0);
        calc.menos();
        calc.dividido();
        calc.aPilha.empilha(10.0);
        calc.aPilha.empilha(3.0);
        calc.menos();
        calc.vezes();
        System.out.println(calc.resultado());
    }

    static void interfaceUsuario() throws IOException {
        CalcRPN calc = new CalcRPN();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            for (String s : line.split(" ")) {
                calc.exec(s);
            }
            System.out.println("Pilha = " + calc.aPilha);
        }
        System.out.println("At´e logo");
    }

    public static void main(String[] args) throws IOException {
        interfaceUsuario();
    }

}

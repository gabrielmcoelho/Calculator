/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.LinkedList;

public class Pilha<T> {

    private LinkedList<T> conteudo;

    public Pilha() {
        conteudo = new LinkedList<>();
    }

    public boolean estaVazia() {
        return conteudo.isEmpty();
    }

    public void empilha(T novoElemento) {
        conteudo.addFirst(novoElemento);
    }

    public T desempilha() {
        if (size() > 0) {
            return conteudo.removeFirst();
        } else {
            throw new Error("Pilha Vazia");
        }
    }

    public T topo() {
        if (size() > 0) {
            return conteudo.getFirst();
        } else {
            throw new Error("Pilha Vazia");
        }
    }

    public int size() {
        return conteudo.size();
    }

    public void reinicialize() {
        conteudo.clear();
    }

    public String toString() {
        return conteudo.toString();
    }
    
    public String toStringInverse() {
        int i = this.size() - 1;
        String s = "[";
        while (i>=0){
            s += this.conteudo.get(i);
            if(i-- != 0){
                s += ',';
            }
        }
        return s + ']';
    }

    static void test1() {
        Pilha<Double> aPilha = new Pilha<Double>();
        aPilha.empilha(1.1);
        aPilha.empilha(2.1);
        aPilha.empilha(3.1);
        aPilha.empilha(4.1);
        aPilha.empilha(5.1);
        double valor = 0.0;
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
    }

    static void test2() {
        Pilha<Double> aPilha = new Pilha<Double>();
        System.out.println(aPilha);
        aPilha.empilha(1.1);
        System.out.println(aPilha);
        aPilha.empilha(2.1);
        System.out.println(aPilha);
        aPilha.empilha(3.1);
        System.out.println(aPilha);
        double valor = 0.0;
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
    }

    public static void main(String[] args) {
        test2();
    }
}

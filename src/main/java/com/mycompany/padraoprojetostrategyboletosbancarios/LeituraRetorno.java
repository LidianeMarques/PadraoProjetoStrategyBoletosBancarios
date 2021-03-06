/*
 * Implementação do Retorno Boleto
A seção 1.5.1 do material disponível aqui apresenta o problema de leitura de 
arquivos de retorno de boletos bancários, que podemos utilizar o padrão Strategy 
para resolver. Lá é descrito o problema e fornecido um diagrama. Relacione este 
diagrama concreto com o diagrama teórico do padrão e assim implemente uma aplicação 
console em Java que aplica o padrão Strategy e faça a leitura de arquivos para o 
Banco do Brasil e Bradesco.

Em anexo são fornecidos dois arquivos de retorno de exemplo para tais bancos, 
para utilizar na implementação. A implementação do Banco do Brasil já está pronta no GitHub,
mas se você apenas olhar tal implementação antes de fazer a sua., não aprenderá nada com isso.
 */
package com.mycompany.padraoprojetostrategyboletosbancarios;

import java.util.List;

/**
 *
 * @author Lidiane
 */
public interface LeituraRetorno {
    
    public List<Boleto> lerArquivo(String nomeArquivo);
    
}

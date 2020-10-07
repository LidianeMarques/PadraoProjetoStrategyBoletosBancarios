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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidiane
 */
public class LeituraRetornoBancoDoBrasil implements LeituraRetorno {

    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        List<Boleto> listaBoletos = new ArrayList<Boleto>();
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {

            br = new BufferedReader(new FileReader(nomeArquivo));
            while ((linha = br.readLine()) != null) {
                Boleto boleto = new Boleto();
                String[] dadosBoleto = linha.split(csvDivisor);
                if (dadosBoleto != null && dadosBoleto.length > 0) {
                    try {
                        boleto.setId(Integer.parseInt(dadosBoleto[0]));
                        boleto.setCodBanco(dadosBoleto[1]);
                        boleto.setDataVencimento(LocalDate.parse(dadosBoleto[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        boleto.setDataPagamento(LocalDateTime.parse(dadosBoleto[3]+" 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                        boleto.setCpfCliente(dadosBoleto[4]);
                        boleto.setValor(Double.parseDouble(dadosBoleto[5]));
                        boleto.setMulta(Double.parseDouble(dadosBoleto[6]));
                        boleto.setJuros(Double.parseDouble(dadosBoleto[7]));
                        listaBoletos.add(boleto);
                    } catch (Exception ex) {
                        System.out.println("ERRO AQUI DENTRO IF DE BOLETOS" + ex);
                    }
                     System.out.println("BANCO DO BRASIL"+"\n"
                        +"Id do boleto: "+boleto.getId()+"\n"
                        + "Cód banco onde o boleto foi pago: "+boleto.getCodBanco()+"\n"
                        + "Data vencimento: "+boleto.getDataVencimento()+"\n"
                        + "Data/hora pagamento: "+boleto.getDataPagamento()+"\n"
                        + "CPF do cliente (sem . e -); "+boleto.getCpfCliente()+"\n"
                        + "Valor do boleto: "+boleto.getValor()+"\n"
                        + "Multa por atraso: "+boleto.getMulta()+"\n"
                        + "Juros no formato: "+boleto.getJuros()+"\n");
                }

            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioex2) {
                    ioex2.printStackTrace();
                }
            }
        }
        return listaBoletos;
    }

}

package recovery;

import parser.*;

@SuppressWarnings({ "unchecked", "removal" })
public class Follow {

	//20 RecoverySet
    public static final RecoverySet main = new RecoverySet();
    public static final RecoverySet comandos = new RecoverySet();
    public static final RecoverySet bloco = comandos;
    public static final RecoverySet repeticao = comandos;
    public static final RecoverySet enquanto = repeticao;
    public static final RecoverySet para = repeticao;
    public static final RecoverySet atribuicao = comandos;
    public static final RecoverySet condicional = comandos;
    public static final RecoverySet tipoDado = new RecoverySet();
    public static final RecoverySet variavel = tipoDado;
    public static final RecoverySet dado = new RecoverySet();
    public static final RecoverySet operacao = new RecoverySet();
    public static final RecoverySet termoOuFatorAritmetico = comandos;
    public static final RecoverySet string = new RecoverySet();
    public static final RecoverySet bool = new RecoverySet();
    public static final RecoverySet expressao = new RecoverySet();
    public static final RecoverySet termoExpressao = expressao;
    public static final RecoverySet fatorExpressao = termoExpressao;
    public static final RecoverySet comparacao = fatorExpressao;
    public static final RecoverySet operadorComparacao = new RecoverySet();

    static {
        main.add(new Integer(Aula1.EOF));

        comandos.add(new Integer(Aula1.FECHA_CHAVE));
        comandos.add(new Integer(Aula1.INTEIRO));
        comandos.add(new Integer(Aula1.FLOAT));
        comandos.add(new Integer(Aula1.STRING));
        comandos.add(new Integer(Aula1.BOLLEAN));
        comandos.add(new Integer(Aula1.IF));
        comandos.add(new Integer(Aula1.FOR));
        comandos.add(new Integer(Aula1.WHILE));

        bloco.add(new Integer(Aula1.ELSE));
        
        tipoDado.add(new Integer(Aula1.VIRGULA));
        tipoDado.add(new Integer(Aula1.IGUAL));
        tipoDado.add(new Integer(Aula1.PONTO_VIRGULA));
        tipoDado.add(new Integer(Aula1.MAISMAIS));
        tipoDado.add(new Integer(Aula1.MENOSMENOS));
        tipoDado.add(new Integer(Aula1.MAISMENOS));
        tipoDado.add(new Integer(Aula1.MENOSMAIS));
        tipoDado.add(new Integer(Aula1.FECHA_PARENTESES));

        dado.add(new Integer(Aula1.PONTO_VIRGULA));

        operacao.add(new Integer(Aula1.PONTO_VIRGULA));
        operacao.add(new Integer(Aula1.FECHA_PARENTESES));

        termoOuFatorAritmetico.add(new Integer(Aula1.FECHA_PARENTESES));
        termoOuFatorAritmetico.add(new Integer(Aula1.MAIS));
        termoOuFatorAritmetico.add(new Integer(Aula1.MENOS));
        termoOuFatorAritmetico.add(new Integer(Aula1.MULTIPLICACAO));
        termoOuFatorAritmetico.add(new Integer(Aula1.DIVISAO));
        termoOuFatorAritmetico.add(new Integer(Aula1.POTENCIA));

        string.add(new Integer(Aula1.PONTO_VIRGULA));

        bool.add(new Integer(Aula1.PONTO_VIRGULA));

        expressao.add(new Integer(Aula1.FECHA_PARENTESES));
        expressao.add(new Integer(Aula1.PONTO_VIRGULA));

        termoExpressao.add(new Integer(Aula1.OPERADOR_LOGICO));
      
        fatorExpressao.add(new Integer(Aula1.IGUALIGUAL));
        fatorExpressao.add(new Integer(Aula1.MAIOR));
        fatorExpressao.add(new Integer(Aula1.MAIORIGUAL));
        fatorExpressao.add(new Integer(Aula1.MENOR));
        fatorExpressao.add(new Integer(Aula1.MENORIGUAL));
        fatorExpressao.add(new Integer(Aula1.MENORMAIOR));
        fatorExpressao.add(new Integer(Aula1.FECHA_PARENTESES));
      
        operadorComparacao.addAll(fatorExpressao);
        operadorComparacao.addAll(string);
    }
}


/*
------------------------------------------------------------------------------------------------------------





main = EOF 

comandos =
first(fechaChave,inteiroTipo,floatTipo,stringTipo,bolleanTipo,ifBloco,forBloco,whileBloco)

bloco = 
follow(condicional,enquanto,para)
first(elseBloco)

enquanto = follow(repeticao)

para = follow(repeticao)

atribuicao= follow(comandos)

condicional= follow(comandos)

repeticao = follow(comandos)

tipoDado = first(variavel)

variavel = first(virgula,igual ,pontoVirgula,maismais,menosmenos, maismenos,menormais,fechaParenteses)
follow(termoOuFatorAritmetico,fatorExpressao)

dado = PONTO_VIRGULA

operacao = follow(dado) first(fechaParenteses)

termoOuFatorAritmetico = 
follow(operacao) 
first(mais,menos,multiplicacao(),divisao(),potencia)

string = follow(dado)

bool= follow(dado)

para = follow(repeticao)

enquanto = follow(repeticao)

expressao = fisrt(fechaParenteses,pontoVirgula)  

termoExpressao = fisrt(operadorLogico) follow(expressao)	 

fatorExpressao = follow(termoExpressao) first(operadorComparacao,fechaParenteses)	

comparacao = follow(fatorExpressao)

operadorComparacao = fisrt(fatorExpressao), follow(string)





------------------------------------------------------------------------------------------------------------






main = EOF	 

comandos = FECHA_CHAVE INTEIRO FLOAT STRING BOLLEAN IF FOR WHILE

bloco = comandos ELSE 

enquanto = comandos

para = comandos

atribuicao= comandos

condicional= comandos

repeticao = comandos

tipoDado = VIRGULA IGUAL PONTO_VIRGULA MAISMAIS MENOSMENOS MAISMENOS MENORMAIS FECHA_PARENTESES

variavel = VIRGULA IGUAL PONTO_VIRGULA MAISMAIS MENOSMENOS MAISMENOS MENORMAIS FECHA_PARENTESES

dado = PONTO_VIRGULA

operacao = PONTO_VIRGULA FECHA_PARENTESES 

termoOuFatorAritmetico = comandos FECHA_PARENTESES MAIS MENOS MULTIPLICACAO DIVISAO POTENCIA

string = PONTO_VIRGULA

bool= PONTO_VIRGULA

para = repeticao

enquanto = repeticao

expressao = FECHA_PARENTESES PONTO_VIRGULA 

termoExpressao = OPERADOR_LOGICO expressao  

fatorExpressao = termoExpressao IGUALIGUAL MAIOR MAIORIGUAL MENOR MENORIGUAL MENORMAIOR FECHA_PARENTESES 	

comparacao = fatorExpressao

operadorComparacao = fatorExpressao string


*/
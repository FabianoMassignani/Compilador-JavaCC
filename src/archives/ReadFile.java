package archives;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import javax.swing.JOptionPane;

public class ReadFile {

	private BufferedReader input;
	private int contLinhaArquivo;

	public void openFile(File arq) {
		try {
			FileReader reader = new FileReader(arq);

			input = new BufferedReader(reader);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir");
		}
	}

	public String readRecords() {
		contLinhaArquivo = 0;
		String result = "";
		String linha = "";
		try {
			while (true) {
				linha = input.readLine();
				if (linha == null)
					break;
				result += linha + "\n";
				contLinhaArquivo++;
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro na leitura, contact o suporte\n Erro: "+e.getStackTrace(),"Erro",0);
		}
		return result;
	}
	

 

	public void closeFile() {

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getContLinhaArquivo(){
		return contLinhaArquivo;
	}
	
	public String numLine(){
		String numeroDeLinhas = new String();
		
		for(int i =1; i<=contLinhaArquivo; i++){
			numeroDeLinhas += i + "\n";
		}
		numeroDeLinhas += (contLinhaArquivo+1)+"";
		return numeroDeLinhas;
	}

}
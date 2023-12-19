package view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import archives.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.text.*;
import java.awt.Component;
import javax.swing.undo.UndoManager;
import javax.swing.UIManager;
 

public class Janela extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botaoCompilador;
	private JButton botaoArvoreSintatica;
	private JPanel tela_Codigo;
	private JPanel panel_console;
	private JScrollPane scroll_console;
	private JScrollPane scroll_codigo;
	private JTextArea textConsole;
	private File file;
	private String campoTexto = null;
	String arvoreString = "";
	JMenu menu, submenu;  
	JTextPane paneLinha = new JTextPane();
	JTextPane paneCodigo = new JTextPane();
	private boolean controleArquivo = false;
	private int numeroDeEnter = 1;
	public Vector<Integer> erros = new Vector<>();
	final static Graphviz graphviz = new Graphviz();
	private UndoManager undoManager;
	 
	public Janela() {
		
		  
		setResizable(false); 
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setBackground(Color.LIGHT_GRAY);
		setTitle("StarCompile");
		setBounds(0, 0, 840, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JMenuBar mb = new JMenuBar(); 
		  	
		menu = new JMenu("File");
		menu.setBackground(Color.BLACK);
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

	      	JMenuItem menuNew = new JMenuItem("New");
	      	menuNew.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	      	JMenuItem menuOpen = new JMenuItem("Open");
	      	menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	      	JMenuItem menuSave = new JMenuItem("Save");
	      	menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	      	JMenuItem menuSaveas = new JMenuItem("Save as");
	      	menuSaveas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        JMenuItem menuFileExit = new JMenuItem("Exit");
	        menuFileExit.setForeground(Color.RED);
	        menuFileExit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	     
          
	        menuNew.addActionListener(new ActionListener() {
	        	@Override
				public void actionPerformed(ActionEvent e) {
					if(paneCodigo.getText().equals("") != true){			
						int resp = JOptionPane.showConfirmDialog(null, "Salvar altera\u00e7\u00f5es? Caso nao salve o arquivo sera perdido!!");
						if(resp ==0){
							if(file == null)
			          	    {
			          	     	salvarComo();
								
			          	    }else
			          	    {
			          	      	salvar();
			          	    }
							sb.delete(0, sb.length());
			          	    paneCodigo.setText("");
			          	  	paneLinha.setText(appendText("1"));
			          	  	numeroDeEnter = 1;
						}else{
							sb.delete(0, sb.length());
							paneCodigo.setText("");
							 
							numeroDeEnter = 17;
							
							sb.delete(0, sb.length());
							
							for(int i = 1 ; i <= numeroDeEnter ; i++) {	 
								paneLinha.setText(appendText(i + " <br>")); 
							}
						}
			      	   
					}else{
						paneCodigo.setText("");
					 
						numeroDeEnter = 17;
						
						sb.delete(0, sb.length());
						
					    for(int i = 1 ; i <= numeroDeEnter ; i++) {	 
								paneLinha.setText(appendText(i + " <br>")); 
						} 
					    
					}
					
				}
	        });

	        menuOpen.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
			
					
					JFileChooser fc = new JFileChooser();
				 
					String userDir = System.getProperty("user.dir");
					String srcPath = userDir + File.separator + "src//Exemplos";
					File srcDirectory = new File(srcPath);
					fc.setCurrentDirectory(srcDirectory);
					
					int returnVal = fc.showOpenDialog(null);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            file = fc.getSelectedFile().getAbsoluteFile();
			           
			            ReadFile read = new ReadFile();
			            read.openFile(file);
			            
			            paneCodigo.setText("");
			            
			            String campoTexto = read.readRecords();
			            campoTexto = stripHtmlTags(campoTexto);

			            paneCodigo.setText(campoTexto);
			            controleArquivo = true;
			            numeroDeEnter = read.getContLinhaArquivo();
			    		 
			    		numeroDeEnter++;
			    		sb.delete(0, sb.length());
			    		
			    		for(int i =1 ; i < numeroDeEnter; i++){
							paneLinha.setText(appendText(i + " <br>"));
						}
			    		
			    		read.closeFile();
			        } 
				}
	        });
	        
	        menuSave.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	salvar();
	            }
	        });
	        
	        menuSaveas.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	salvarComo();
	            }
	        });
       
	        menuFileExit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0); 
	            }
	        });
	        
	    menu.add(menuNew); 
	    menu.add(menuOpen); 
	    menu.add(menuSave); 
	    menu.add(menuSaveas); 
	    menu.add(menuFileExit); 
        
	    mb.add(menu);  
        setJMenuBar(mb);  
		
		tela_Codigo = new JPanel();
		tela_Codigo.setBounds(10, 10, 805, 360);
		tela_Codigo.setLayout(new BorderLayout(0, 0));	
		getContentPane().add(tela_Codigo);

		scroll_codigo = new JScrollPane ();
		scroll_codigo.setViewportView(paneCodigo);
		tela_Codigo.add(scroll_codigo);
		paneLinha.setBackground(UIManager.getColor("CheckBox.light"));
		paneLinha.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		paneLinha.setAlignmentX(Component.RIGHT_ALIGNMENT);
		paneLinha.setEditable(false);
		paneLinha.setForeground(new Color(128, 0, 255));
		paneLinha.setFont(new Font("Arial", Font.PLAIN, 16));
		paneLinha.setContentType("text/html");
		for(int i = 1 ; i <= 17 ; i++) {	 
			paneLinha.setText(appendText(i + " <br>")); 
		}
		scroll_codigo.setRowHeaderView(paneLinha);
		
		panel_console = new JPanel();
		panel_console.setForeground(Color.WHITE);
		panel_console.setBackground(Color.WHITE);
		panel_console.setBounds(10, 415, 805, 245);
		getContentPane().add(panel_console);
		panel_console.setLayout(null);
		
		scroll_console = new JScrollPane();
		scroll_console.setBounds(0, 0, 805, 245);
		panel_console.add(scroll_console);
		
		textConsole = new JTextArea();
		textConsole.setBackground(new Color(25, 25, 25));
		textConsole.setForeground(Color.RED);
		textConsole.setText("->");
		scroll_console.setViewportView(textConsole);
		textConsole.setFont(new Font("Consolas", Font.BOLD, 12));	
		
		botaoCompilador = new JButton("Compile code");	
		botaoCompilador.setBounds(10, 381, 128, 23);
		getContentPane().add(botaoCompilador);
		botaoCompilador.setFont(new Font("Tahoma", Font.BOLD, 13));
		botaoCompilador.setForeground(Color.BLACK);
		botaoCompilador.setBackground(Color.WHITE);
		 
		
		botaoArvoreSintatica = new JButton("Tree syntaxe");
		botaoArvoreSintatica.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoArvoreSintatica.setForeground(Color.BLACK);
		botaoArvoreSintatica.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Graphviz graphviz = new Graphviz();
		            graphviz.runGraphviz(arvoreString);
		        } catch (IOException f) {
		            f.printStackTrace();
		        }
		    }
		});
		botaoArvoreSintatica.setBackground(Color.WHITE);
		botaoArvoreSintatica.setBounds(148, 381, 125, 23);
		getContentPane().add(botaoArvoreSintatica);	
		
		undoManager = new UndoManager();
		paneCodigo.setFont(new Font("Consolas", Font.PLAIN, 16));
		paneCodigo.setBounds(10, 55, 890, 285);
        paneCodigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });

        InputMap inputMap = paneCodigo.getInputMap(JTextComponent.WHEN_FOCUSED);
        ActionMap actionMap = paneCodigo.getActionMap();
        
		paneCodigo.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent arg0) {
		        handleKeyPress(arg0.getKeyCode());
		    }
		});
		
		
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "Undo");
        actionMap.put("Undo", new AbstractAction("Undo") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });

      
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "Redo");
        actionMap.put("Redo", new AbstractAction("Redo") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
        
        undoManager = new UndoManager();
        paneCodigo.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
    
		
		updateButtonState();
		 
	}
	
    private void updateButtonState() {
        botaoCompilador.setEnabled(!paneCodigo.getText().trim().isEmpty()); 
    }
	 
	public void handleKeyPress(int keyCode) {
	    switch (keyCode) {
	        case KeyEvent.VK_UP:
	        case KeyEvent.VK_DOWN:
	            scroll_codigo.getViewport().getViewPosition();
	            break;

	        case KeyEvent.VK_ENTER:
	            handleEnterKey();
	            break;

	        case KeyEvent.VK_BACK_SPACE:
	            handleBackspaceKey();
	            break;
	    }
	}

	public void handleEnterKey() {
	    sb.delete(0, sb.length());

	    if (!paneCodigo.getText().trim().isEmpty()) {
	        numeroDeEnter++;
	        contaLinha(10);
	    } else {
	        updateLineNumberText();
	    }
	}

	public void handleBackspaceKey() {
	    sb.delete(0, sb.length());

	    if (!paneCodigo.getText().trim().isEmpty()) {
	        numeroDeEnter--;
	        contaLinha(8);
	    } else {
	        handleBackspaceWithEmptyText();
	    }
	}

	public void updateLineNumberText() {
	    paneLinha.setText(appendText(""));

	    numeroDeEnter++;

	    for (int i = 1; i < numeroDeEnter; i++) {
	        paneLinha.setText(appendText(i + " <br>"));
	    }

	    paneLinha.setText(appendText(numeroDeEnter + ""));
	}

	public void handleBackspaceWithEmptyText() {
	    if (numeroDeEnter > 1) {
	        numeroDeEnter--;

	        paneLinha.setText(appendText(""));
	        for (int i = 1; i < numeroDeEnter; i++) {
	            paneLinha.setText(appendText(i + " <br>"));
	        }
	        paneLinha.setText(appendText(numeroDeEnter + ""));
	    } else if (numeroDeEnter == 1) {
	       
	        paneLinha.setText(appendText(" 1"));
	    }
	}

	public void contaLinha(int key) {
	    String[] separa = paneCodigo.getText().split("\n");
	    paneLinha.setText(appendText(""));

	    if (numeroDeEnter >= separa.length) {
	        for (int i = 1; i < numeroDeEnter; i++) {
	            paneLinha.setText(appendText(i + " <br>"));
	        }

	        paneLinha.setText(appendText(numeroDeEnter + " "));
	    } else {
	        for (int i = 1; i < separa.length; i++) {
	            paneLinha.setText(appendText(i + " <br>"));
	        }

	        paneLinha.setText(appendText(separa.length + ""));

	        if (key == 10) paneLinha.setText(appendText((separa.length + 1) + ""));

	        numeroDeEnter = separa.length;
	    }
	}

	 private static int extrairNumeroLinha(String mensagemErro) {
	      
	        String padrao = "linha (\\d+),";
	        Pattern pattern = Pattern.compile(padrao);
	        Matcher matcher = pattern.matcher(mensagemErro);

	      
	        if (matcher.find()) {
	            String numeroLinhaStr = matcher.group(1);
	            return Integer.parseInt(numeroLinhaStr);
	        } else {
	            
	            return -1;
	        }
	    }
	
	 private static int extrairNumeroColuna(String mensagemErro) {
	        String padrao = "coluna (\\d+).";
	        Pattern pattern = Pattern.compile(padrao);
	        Matcher matcher = pattern.matcher(mensagemErro);

	        if (matcher.find()) {
	            String numeroColunaStr = matcher.group(1);
	            return Integer.parseInt(numeroColunaStr);
	        } else {
	            return -1;
	        }
	    }
	 
	 public void MarcaLinhaErro(String mensagemDeErro) {
		    sb.delete(0, sb.length());
		    
		   
		    
		    int numeroLinha = extrairNumeroLinha(mensagemDeErro);
		    int numeroColuna = extrairNumeroColuna(mensagemDeErro);
		    
		    erros.add(numeroLinha);
		    
		    StyledDocument doc = paneCodigo.getStyledDocument();

		    String[] separa = paneCodigo.getText().split("\n");

		    int startIndex = paneCodigo.getText().indexOf(separa[numeroLinha - 1]);
		    int endIndex = startIndex + separa[numeroLinha - 1].length();

		    Style style = paneCodigo.addStyle("highlight", null);
		    StyleConstants.setForeground(style, new Color(255, 128, 128));

		    startIndex = startIndex + numeroColuna - 1;
		    endIndex = startIndex + 1;

		    StyleConstants.setForeground(style, Color.RED);

		    doc.setCharacterAttributes(startIndex, endIndex - startIndex, doc.getStyle("highlight"), true);

		    for (int i = 1; i < numeroDeEnter; i++) {
		        if (!erros.contains(i)) {
		            paneLinha.setText(appendText(i + " <br>"));
		        } else {
		            paneLinha.setText(appendText("<span style=\"color:red\"><b>" + i + "</b></span> <br>"));
		        }
		    }
		}

	
	public void removerFormatacaoCor() {
	    StyledDocument doc = paneCodigo.getStyledDocument();
	    Style defaultStyle = paneCodigo.getStyle(StyleContext.DEFAULT_STYLE);

	    try {
	        doc.setCharacterAttributes(0, doc.getLength(), defaultStyle, true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    sb.delete(0, sb.length());
	    
	    for(int i = 1 ; i <= numeroDeEnter ; i++) {	 
				paneLinha.setText(appendText(i + " <br>")); 
		} 
	}
	
 

	public void verificaSeCompilou(){
		 if(erros.isEmpty()) {
			 
			 textConsole.append("\n-> Compilou"); 
		 }
	}
	
	public JButton botaoCompilador() {
		return botaoCompilador;
	}

	public JTextPane getTextArquivo(){
		return paneCodigo;
	}
	
	public void setConsole(String msg){
		  textConsole.append(msg + "\n->");
	}
	
	public void setMsgArvore(String msg){
		arvoreString = arvoreString + msg;
	}
	
 
	public File getFile(){
		return file;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public String getCampoTexto(){
		return campoTexto;
	}
	
	public void setCampoTexto(String campoTexto){
		this.campoTexto = campoTexto;
	}
	
	public boolean getControleArquivo(){return controleArquivo;}
	
	public void setControleArquivo(boolean controle){
		this.controleArquivo = controle;
	}
	
	public void setVazioConsole(){
		textConsole.setText("->");
		arvoreString = "";
	}

 
	
	public void salvar(){
		campoTexto = new String();	            
		controleArquivo = true;
		
		if(file !=null){
			campoTexto = new String();	            
  		controleArquivo = true;
			CreateFile create = new CreateFile();
			
			create.openFile(file);
			
			create.addRecords(paneCodigo.getText());
			
			create.closeFile();
			
			campoTexto = paneCodigo.getText();
		}else{
			salvarComo();
		}	
	}
	
	public void salvarComo(){
		JFileChooser jc = new JFileChooser();
		
		String userDir = System.getProperty("user.dir");
		String srcPath = userDir + File.separator + "src//Exemplos";
		File srcDirectory = new File(srcPath);
		jc.setCurrentDirectory(srcDirectory);
		jc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i= jc.showSaveDialog(null);

		if(i!=1) {
			file = jc.getSelectedFile();
			campoTexto = new String();	            
			controleArquivo = true;
			CreateFile create = new CreateFile();
			
			create.openFile(file);
			create.addRecords(paneCodigo.getText());
			create.closeFile();
			
			campoTexto = paneCodigo.getText();
		}
	}
	
	  private String stripHtmlTags(String htmlText) {
	        // Simple method to strip HTML tags using a regular expression
	        return htmlText.replaceAll("<[^>]*>", "");
	    }
	
	private StringBuilder sb = new StringBuilder();
 
	public String appendText(String text) { 
		  return sb.append(text).toString();
	}
}
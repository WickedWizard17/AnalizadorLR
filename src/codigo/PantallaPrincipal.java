/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;

/**
 *
 * @author nesto
 */
public class PantallaPrincipal extends javax.swing.JFrame {
 
    private String[] 
		terminales = new String[] {"num","id","int","float","char",",",";","+","-","*","/","(",")","="},
		noterminales = new String[] {"P","Tipo","V","A","E","T","F"},
		
		encabezados = new String[] {"num","id","int","float","char",",",";","+","-","*","/","(",")","=","$","P","Tipo","V","A","E","T","F"},
		estados = new String[] {"I00","I01","I02","I03","I04","I05","I06","I07","I08","I09","I10","I11","I12","I13","I14","I15","I16",
									"I17","I18","I19","I20","I21","I22","I23","I24","I25","I26","I27","I28","I29","I30","I31","I32"},
		producciones = new String[] {"Q","P","P","V","V","A","E","E","E","T","T","T","F","F","F","Tipo","Tipo","Tipo"};
    private int[] 
		//tamProd = new int [] {2,6,2,6,4,8,6,6,2,6,6,2,6,2,2,2,2,2};
            tamProd = new int [] {1,3,1,3,2,4,3,3,1,3,3,1,3,1,1,1,1,1};
    
    private String[][]
		tablaSint =  new String[][] 
		{//		num		id		int		float		char		,		;		+		-		*		/		(		)		=		$		P		Tipo		V		A		E		T		F
		/*I00*/	{"",	"I07",	"I04",	"I05",		"I06",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"I01",	"I02",		"",		"I03",	"",		"",		""},
		/*I01*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P0",	"",		"",			"",		"",		"",		"",		""},
		/*I02*/	{"",	"I08",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I03*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P2",	"",		"",			"",		"",		"",		"",		""},
		/*I04*/	{"",	"P15",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I05*/	{"",	"P16",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I06*/	{"",	"P17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I07*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"I09",	"",		"",		"",			"",		"",		"",		"",		""},
		/*I08*/	{"",	"",		"",		"",			"",			"I11",	"I12",	"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"I10",	"",		"",		"",		""},
		/*I09*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"I13",	"I14",	"I15"},
		/*I10*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P1",	"",		"",			"",		"",		"",		"",		""},
		/*I11*/	{"",	"I19",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I12*/	{"",	"I07",	"I04",	"I05",		"I06",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"I20",	"I02",		"",		"I03",	"",		"",		""},
		/*I13*/	{"",	"",		"",		"",			"",			"",		"I21",	"I22",	"I23",	"",		"",		"",		"",		"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I14*/	{"",	"",		"",		"",			"",			"",		"P8",	"P8",	"P8",	"I24",	"I25",	"",		"P8",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I15*/	{"",	"",		"",		"",			"",			"",		"P11",	"P11",	"P11",	"P11",	"P11",	"",		"P11",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I16*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"I26",	"I14",	"I15"},
		/*I17*/	{"",	"",		"",		"",			"",			"",		"P13",	"P13",	"P13",	"P13",	"P13",	"",		"P13",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I18*/	{"",	"",		"",		"",			"",			"",		"P14",	"P14",	"P14",	"P14",	"P14",	"",		"P14",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I19*/	{"",	"",		"",		"",			"",			"I11",	"I12",	"",		"",		"",		"",		"",		"",		"",		"",		"",		"",			"I27",	"",		"",		"",		""},
		/*I20*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P4",	"",		"",			"",		"",		"",		"",		""},
		/*I21*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P5",	"",		"",			"",		"",		"",		"",		""},
		/*I22*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"",		"I28",	"I15"},
		/*I23*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"",		"I29",	"I15"},
		/*I24*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"",		"",		"I30"},
		/*I25*/	{"I18",	"I17",	"",		"",			"",			"",		"",		"",		"",		"",		"",		"I16",	"",		"",		"",		"",		"",			"",		"",		"",		"",		"I31"},
		/*I26*/	{"",	"",		"",		"",			"",			"",		"",		"I22",		"I23",		"",		"",		"",		"I32",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I27*/	{"",	"",		"",		"",			"",			"",		"",		"",		"",		"",		"",		"",		"",		"",		"P3",	"",		"",			"",		"",		"",		"",		""},
		/*I28*/	{"",	"",		"",		"",			"",			"",		"P6",	"P6",	"P6",	"I24",	"I25",	"",		"P6",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I29*/	{"",	"",		"",		"",			"",			"",		"P7",	"P7",	"P7",	"I24",	"I25",	"",		"P7",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I30*/	{"",	"",		"",		"",			"",			"",		"P9",	"P9",	"P9",	"P9",	"P9",	"",		"P9",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I31*/	{"",	"",		"",		"",			"",			"",		"P10",	"P10",	"P10",	"P10",	"P10",	"",		"P10",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		/*I32*/	{"",	"",		"",		"",			"",			"",		"P12",	"P12",	"P12",	"P12",	"P12",	"",		"P12",	"",		"",		"",		"",			"",		"",		"",		"",		""},
		
		}; 
    
    private LinkedList<String> entrada = new LinkedList<String>();
    private LinkedList<String> aux = new LinkedList<String>();
    private LinkedList<String> pilaPrincipal = new LinkedList<String>();
    private LinkedList<String> identificadores = new LinkedList<String>();
    private LinkedList<Integer> pilaSaltos = new LinkedList<Integer>();
    private LinkedList<Integer> identificadoresPos = new LinkedList<Integer>();
    private String resultado = "";
    private int numLinea;
    
    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textoEntrada = new javax.swing.JTextArea();
        botonAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoResultado = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textoEntrada.setColumns(20);
        textoEntrada.setRows(5);
        jScrollPane1.setViewportView(textoEntrada);

        botonAnalizar.setText("Analizar");
        botonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnalizarActionPerformed(evt);
            }
        });

        textoResultado.setColumns(20);
        textoResultado.setRows(5);
        jScrollPane2.setViewportView(textoResultado);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Salida:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Cadena de entrada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(194, 194, 194))
            .addGroup(layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAnalizar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonAnalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnalizarActionPerformed
        // TODO add your handling code here:
        pilaPrincipal.clear();
        entrada.clear();
        aux.clear();
        identificadores.clear();
        pilaSaltos.clear();
        identificadoresPos.clear();
        textoResultado.setText("");
        pilaPrincipal.add("$");
	pilaPrincipal.add("I00");
        numLinea = 1;
        int contIds = 0, posIds = 0, pos = -1;
        
        File archivo = new File("archivo.txt");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(textoEntrada.getText() + "$");
            escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
            Lexer lexer = new Lexer(lector);
            resultado = "";
            while (true) {
                Tokens tokens = lexer.yylex();
                if(tokens == null) {
                    pilaSaltos.add(pos);
                    int ultInt = entrada.lastIndexOf("int");
                    int ultFloat = entrada.lastIndexOf("float");
                    int ultChar = entrada.lastIndexOf("char");
                    posIds = Integer.max(Integer.max(ultInt, ultFloat),Integer.max(ultInt, ultChar));
                    for(int i = posIds; i < entrada.size(); i++)
                        if(entrada.get(i).equals(";"))
                        {
                            posIds = i;
                            break;
                        }
                    for(int j = 0; j <= posIds; j++)
                        if(entrada.get(j).equals("id"))
                        {
                            contIds++;
                        }
                    for(int i = 0; i < contIds; i++)
                        for(int j = i+1; j < contIds; j++)
                            if(identificadores.get(i).equals(identificadores.get(j)))
                            {
                                resultado += "¡¡¡ERROR SEMÁNTICO!!!\n";
                                resultado += "Línea " + this.mostrarSaltos2(identificadoresPos.get(i)) + ": ";
                                resultado += "Tienes más de dos identificadores llamados: " + identificadores.get(i);
                                textoResultado.setText(resultado);
                                return;
                            }
                    boolean ban = true;
                    int idInvalido = 0;
                    for(int i = contIds; i < identificadores.size(); i++)
                        for(int j = 0; j < contIds; j++)
                            if(identificadores.get(i).equals(identificadores.get(j))) {
                                ban = true;
                                break; }
                                else
                            {
                                ban = false;
                                idInvalido = i;
                            }
                    if(ban)
                    {
                    aux.addAll(entrada);
                    this.Sintactico(aux);
                    textoResultado.setText(resultado);
                    return; 
                    }
                    else
                    {
                        resultado += "¡¡¡ERROR SEMÁNTICO!!!\n";
                        
                        resultado += "Línea " + numLinea + ": ";
                        resultado += "NO tienes declarado el identificador: " + identificadores.get(idInvalido);
                        textoResultado.setText(resultado);
                        return;
                    }
                    
                }
                switch(tokens) {
                    case ERROR:
                        resultado += "¡¡¡ERROR LÉXICO!!!\n";                      
                        resultado += "Linea " + numLinea + ": El símbolo NO está definido en el lenguaje!";
                        textoResultado.setText(resultado);
                        return;
                    case id:
                        entrada.add("id");   
                        pos++;
                        identificadores.add(lexer.lexeme);
                        identificadoresPos.add(pos);
                        break;
                    case num:
                        entrada.add("num");
                        pos++;
                        break;
                    case SaltoLinea:
                        numLinea++;
                        pilaSaltos.add(pos);
                        break;                        
                    default:
                        pos++;
                        entrada.add(lexer.lexeme+"");     
                }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAnalizarActionPerformed

    public void Sintactico (LinkedList<String> pilaEntrada)
	{
		int x,y,z,pos = 0,rango = 0;
                while(true)
		{
			for(x=0;x<estados.length && !pilaPrincipal.getLast().equals(estados[x]) ;x++);
			for(y=0;y<encabezados.length && !pilaEntrada.getFirst().equals(encabezados[y]) ;y++);
                        
			if(x<estados.length && y<estados.length)
			{
				this.mostrarPilas(pilaPrincipal);
				if(!tablaSint[x][y].isBlank())
				{
					if(tablaSint[x][y].charAt(0)=='I')
					{
						pilaPrincipal.add(tablaSint[x][y]);
                                                pilaEntrada.removeFirst();
                                                pos++;
					}
					else
						if(tablaSint[x][y].charAt(0)=='P')
						{
                                                      pos--;
							rango = Integer.parseInt(tablaSint[x][y].substring(1));
							for(z=0;z<tamProd[rango];z++)
                                                            pilaPrincipal.removeLast();
							if(tablaSint[x][y].equals("P0"))
							{
								resultado += "\n¡¡¡CADENA ACEPTADA!!!\n";
								break;
							}
						pilaEntrada.addFirst(producciones[rango]);                                              
						}
				}
				else
				{
					resultado += "\n¡¡¡ERROR SINTÁCTICO!!!\n";
                                        this.mostrarSaltos(pos);
                                        resultado += "Línea " + this.mostrarSaltos(pos) + ": ";
                                        switch(entrada.get(pos)) {
                                            case "$":
                                                resultado += "Fin de programa inesperado!\n";
                                                break;
                                            case "num":
                                                resultado += "NO se esperaba un número!\n";
                                                break;
                                            case "id":
                                                resultado += "NO se esperaba un identificador!\n";
                                                break;
                                            default:
                                                resultado += "El elemento " + entrada.get(pos) + " NO se esperaba!\n";
                                        }
              					
                                        resultado += "Se esperaba:\n";
                                        for(int i = 0, j = 0; i < tablaSint[x].length && j <= 14 ; i++, j++)
                                            if(!tablaSint[x][i].isBlank()) {
                                                switch(tablaSint[x][i]) {
                                                    case "$":
                                                        resultado += "Fin del programa\t";
                                                        break;
                                                    case "num":
                                                        resultado += "Un número\t";
                                                        break;
                                                    case "id":
                                                        resultado += "Un identificador\t";
                                                        break;
                                                    default:
                                                        resultado += encabezados[j] + "\t";
                                                        break;                                                        
                                                }                 
                                            }
					break;
				}
				
			}
			else
			{
				resultado += "Error en los rangos";
				//break;
			}	
		}
	}
        
    public void mostrarPilas(LinkedList<String> pila) {
		for(int x = 0; x<pila.size();x++)
			resultado += pila.get(x) + " ";
                resultado += "\n";
    }
    
    public void mostrarPilas2(LinkedList<Integer> pila) {
		for(int x = 0; x<pila.size();x++)
			resultado += pila.get(x) + " ";
                resultado += "\n";
    }
    
    public int mostrarSaltos(int pos) {
        //this.mostrarPilas2(pilaSaltos);
        //resultado += pos +"\n";
        int posSalto = 1;
        for(int i = 0; i < pilaSaltos.size(); i++)
            if(Integer.compare(pilaSaltos.get(i), pos) < 0)
                posSalto++;
            else
                break;
        //resultado += posSalto +"\n";
        return posSalto;
    }
    
     public int mostrarSaltos2(int pos) {
        int posSalto = 1;
        for(int i = 0; i < pilaSaltos.size(); i++)
            if(Integer.compare(pilaSaltos.get(i), pos) < 0)
                posSalto++;
            else
                break;
        return posSalto;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
        
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textoEntrada;
    private javax.swing.JTextArea textoResultado;
    // End of variables declaration//GEN-END:variables
}

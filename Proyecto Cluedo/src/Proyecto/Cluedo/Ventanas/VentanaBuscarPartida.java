package Proyecto.Cluedo.Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Proyecto.Cluedo.Datos.ButtonColumn;
import Proyecto.Cluedo.Datos.Partida;
import Proyecto.Cluedo.Datos.Usuario;
import Proyecto.Cluedo.Hilo.comprobador;
import Proyecto.Cluedo.Logica.GestionBaseDeDatos;
import Proyecto.Cluedo.Logica.Jugador;
import Proyecto.Cluedo.Logica.Propiedades;

public class VentanaBuscarPartida extends JFrame {

	private GestionBaseDeDatos gestion = new GestionBaseDeDatos();

	private JLabel labelPGuardada = new JLabel("P.Guardada");

	private JLabel labelPAbierta = new JLabel("P.Abierta");
	
	private ArrayList<String> ordenicon;
	
	private String [] listaDeFichas = {"Imagenes/coche.png","Imagenes/barco.png","Imagenes/dedal.png","Imagenes/sombrero.png",
"Imagenes/perro.png","Imagenes/zapato.png","Imagenes/plancha.png","Imagenes/carretilla.png"};
	
	

	public VentanaBuscarPartida(Connection conexion, Usuario u) {

		// Establecemos el formato al panel

		setResizable(false);

		setSize(new Dimension(800, 600));
		

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getContentPane().setBackground(Color.WHITE);

		// Generamos los componentes

		// Esto para partidas abiertas

		ArrayList <Integer> listaCodigosSinCompletar = gestion.obtenerCodigoPartidasSinCompletar(conexion,u.getUsuario());
		
		ArrayList<String> listaNombrePartidas = gestion.obtenerNombrePartidas(conexion,u.getUsuario());

		ArrayList<Integer> listaJugadoresActual = gestion.obtenerJugadoresPartidas(conexion, "NUMEROJUGADORESACTUAL",listaCodigosSinCompletar);

		ArrayList<Integer> listaJugadoresMaximo = gestion.obtenerJugadoresPartidas(conexion, "NUMEROJUGADORESMAXIMO",listaCodigosSinCompletar);

		// Esto para partidas guardadas del usuario

		ArrayList<Integer> listaCodigos = gestion.obtenerCodigoPartidaJugador(conexion, u.getUsuario());

		ArrayList<String> listaNombre = gestion.obtenerNombreP(conexion, listaCodigos);

		ImageIcon imagen = new ImageIcon();

		Icon icono;

		JLabel carretera = new JLabel();

		JLabel carreteraInvertida = new JLabel();

		JLabel poste = new JLabel();

		JLabel fondo = new JLabel();

		JLabel labelTitulo = new JLabel();

		JLabel labelTitulo2 = new JLabel();


		

		JLabel label = new JLabel("Adentrate en un mundo misterioso");

		JLabel seņal = new JLabel();

		JLabel avisoSeņal = new JLabel(
				"<html><body><DIV ALIGN=center>No se ha encontrado ninguna <br>partida, cree una ahora</DIV></body></html>");

		JLabel seņalInvertida = new JLabel();

		JLabel avisoSeņalInvertida = new JLabel(
				"<html><body><DIV ALIGN=center>No tienes ninguna partida <br>guardada todavia</DIV></body></html>");

		// Establecemos el formato

		seņal.setBounds(535, 183, 200, 200);

		try {
			imagen = new ImageIcon(VentanaBuscarPartida.class.getResource("Imagenes/warning.png").toURI().toURL());
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}

		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(seņal.getWidth(), seņal.getHeight(), Image.SCALE_DEFAULT));

		seņal.setIcon(icono);

		avisoSeņal.setBounds(496, 393, 308, 50);

		avisoSeņal.setFont(new Font("System", Font.BOLD, 20));

		avisoSeņal.setOpaque(true);

		avisoSeņal.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));

		// avisoSeņal.setForeground(new Color(1.0f,0.0f,0.0f,0.8f));

		seņalInvertida.setBounds(63, 183, 200, 200);

		seņalInvertida.setIcon(icono);

		avisoSeņalInvertida.setOpaque(true);

		avisoSeņalInvertida.setBounds(35, 393, 281, 50);

		avisoSeņalInvertida.setFont(new Font("System", Font.BOLD, 20));

		avisoSeņalInvertida.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));

		carretera.setBounds(14, 269, 386, 332);

		try {
			imagen = new ImageIcon(
					VentanaBuscarPartida.class.getResource("Imagenes/carreteraInvertida.png").toURI().toURL());
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}

		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(carretera.getWidth(), carretera.getHeight(), Image.SCALE_DEFAULT));

		carretera.setIcon(icono);

		carreteraInvertida.setBounds(380, 269, 404, 332);

		try {
			imagen = new ImageIcon(VentanaBuscarPartida.class.getResource("Imagenes/carretera.png").toURI().toURL());
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}

		icono = new ImageIcon(imagen.getImage().getScaledInstance(carreteraInvertida.getWidth(),
				carreteraInvertida.getHeight(), Image.SCALE_DEFAULT));

		carreteraInvertida.setIcon(icono);

		poste.setBounds(280, 245, 207, 274);
		try {
			imagen = new ImageIcon(VentanaBuscarPartida.class.getResource("Imagenes/arbol.png").toURI().toURL());
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}

		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(poste.getWidth(), poste.getHeight(), Image.SCALE_DEFAULT));

		poste.setIcon(icono);
		
		labelPGuardada.setBounds(315, 300, 100, 40);
		
		labelPAbierta.setBounds(380,350,100,40);

		labelPGuardada.setFont(new Font("System", Font.BOLD, 16));
		
		labelPAbierta.setFont(new Font("System", Font.BOLD, 16));
		
		fondo.setBounds(0, 52, 800, 297);

		try {
			imagen = new ImageIcon(VentanaBuscarPartida.class.getResource("Imagenes/skyline.jpg").toURI().toURL());
		} catch (Exception e) {
			System.out.println("No se ha encontrado el archivo");
		}

		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_DEFAULT));

		fondo.setIcon(icono);

		String[] columnas={"Nombre Partida", "Continuar"};
		DefaultTableModel tablaModel=new DefaultTableModel(columnas,0){

            boolean[] canEdit = new boolean[]{
                    false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
			
		};
		JTable tabla = new JTable();
		
		//tabla.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		
		tabla.setModel(tablaModel);
		
		
		
		//panelIzquierdo.add(tabla.getTableHeader());	
		
		DefaultTableCellRenderer renderertit = new DefaultTableCellRenderer();
		renderertit.setBackground(Color.DARK_GRAY);
		renderertit.setForeground(Color.white);
		renderertit.setFont(new Font("System", Font.BOLD, 12));

		tabla.getTableHeader().setDefaultRenderer(renderertit);
		
		if (listaNombre.size()>0){
		for (String l: listaNombre){
				  

				   Object[] data = {l, "CONTINUAR"};

				   tablaModel.addRow(data);

				}
		}
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(144);
		
		JFrame v=this;
		
		AbstractAction aņadir2 = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        System.out.println(modelRow);
		        
				Insets insets2 = v.getInsets();

				System.out.println(insets2.top);
		        
		        for (Window window : Window.getWindows()) {
				    window.dispose();
				}
		        
		       Partida p=gestion.obtenerPartida(conexion, listaCodigos.get(modelRow));
		        
		       
		       	Jugador j= gestion.obtenerJu(conexion, p, u);
		       
		      Propiedades prop=new Propiedades(6,8,7,p.getNumeroJugadoresActual(),conexion,gestion,p);
				
		      VentanaTablero ventana = new VentanaTablero(conexion, j, u, gestion, p, prop,false,insets2.top);
		      
		      ventana.setVisible(true);
		      
		    }
		};
		 
		ButtonColumn buttonColumn2 = new ButtonColumn(tabla, aņadir2, 1);
		buttonColumn2.setMnemonic(KeyEvent.VK_ENTER);
		
		
		

		String [] columnas2={"Nombre", "Actual","Maximo","Unirse"};
		DefaultTableModel tablaModel2=new DefaultTableModel(columnas2,0){

            boolean[] canEdit = new boolean[]{
                    false, false,false,true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
			
		};
		JTable tabla2 = new JTable();
		
		//tabla2.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		
		tabla2.setModel(tablaModel2);
		
		
		
		
		
		DefaultTableCellRenderer renderertit2 = new DefaultTableCellRenderer();
		
		renderertit2.setBackground(Color.DARK_GRAY);
		renderertit2.setForeground(Color.white);
		renderertit2.setFont(new Font("System", Font.BOLD, 12));

		tabla2.getTableHeader().setDefaultRenderer(renderertit2);
		
		//panelDerecho.add(tabla2.getTableHeader());
		
		if (listaNombrePartidas.size()>0){
		for (int i=0;i<listaNombrePartidas.size();i++){
				  
			String nombre = listaNombrePartidas.get(i);
			System.out.println(listaNombrePartidas.get(i));
			
			int numeroA= listaJugadoresActual.get(i);
			
			int numeroM=listaJugadoresMaximo.get(i);

				   Object[] data = {nombre, numeroA,numeroM,"GO"};

				   tablaModel2.addRow(data);
				   
				}
		}
		
		
		
		tabla2.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabla2.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabla2.getColumnModel().getColumn(2).setPreferredWidth(55);
		tabla2.getColumnModel().getColumn(3).setPreferredWidth(60);
//		
//		tabla.setBackground(Color.WHITE);
//
//		tabla2.setBackground(Color.WHITE);
		
		AbstractAction aņadir = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        Insets insets2 = v.getInsets();

				System.out.println(insets2.top);
		        
		        for (Window window : Window.getWindows()) {
				    window.dispose();
				}
		        
		       Partida p=gestion.obtenerPartida(conexion, listaCodigosSinCompletar.get(modelRow));
		       
		        

		       //TODO: Hay que hacer aqui para que elija la ficha
		       
				

		      ArrayList<Icon> arraymunyecos= meterImgEnlabel(listaDeFichas,100,100,conexion,p.getCodigo());
		      Object [] arraymunyecosdos=arraymunyecos.toArray();
		      Object elijo= JOptionPane.showInputDialog(null, "Elija su ficha", "Ficha", JOptionPane.QUESTION_MESSAGE, null, arraymunyecosdos,arraymunyecosdos[0]);
		      if(elijo!=null){
		    	  int num= pasarAstring((Icon)elijo,arraymunyecos); 
			      String ficha=ordenicon.get(num);
			       Jugador j= new Jugador(ficha, u.getUsuario(), p.getCodigo(), conexion);
			       
			       	gestion.insertarJugador(conexion, j, p, u);
			       	
			       	gestion.modificar(conexion, "PARTIDA", "NUMEROJUGADORESACTUAL=NUMEROJUGADORESACTUAL+1", "CODIGO="+listaCodigosSinCompletar.get(modelRow));
			        
			       	Partida q=gestion.obtenerPartida(conexion, listaCodigosSinCompletar.get(modelRow));
				    
			       	Propiedades prop=new Propiedades(6,8,7,q.getNumeroJugadoresActual(),conexion,gestion,q);
					
			       	
			        comprobador comp= new comprobador(p,conexion,j,u,gestion,prop,insets2.top);

		      
		       
		       


					
					VentanaConectando ventana = new VentanaConectando();
					
					comp.start();
					
					
					
					ventana.setVisible(true);
					
					
					ventana.revalidate();
					   System.out.println("pq"+p.getCodigo()+" "+q.getCodigo());
					//int numJugadores=gestion.obtenerJugadoresJugando(conexion, listaCodigosSinCompletar.get(modelRow));
					if(q.getNumeroJugadoresActual()==p.getNumeroJugadoresMaximo()){
						ArrayList<Jugador> arrLjug=gestion.consultaATablaJugador(conexion,"COD_PARTIDA="+p.getCodigo());
						Jugador [] arrJug=new Jugador[p.getNumeroJugadoresMaximo()];
						for(int i=0;i<arrLjug.size();i++){
							arrJug[i]=arrLjug.get(i);
						}
						prop.RepartirCartas(arrJug,conexion);
						System.out.println("size"+arrJug.length);
					}
			    
		    	  
		      }else{
		    	  VentanaBuscarPartida ventana = new VentanaBuscarPartida(conexion, u);
		    	  ventana.setVisible(true);
		      }
		      
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(tabla2, aņadir, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		JScrollPane panelIzquierdoS = new JScrollPane(tabla);

		JScrollPane panelDerechoS = new JScrollPane(tabla2);

		JPanel panelIzquierdoN= new JPanel();
		
		JPanel panelDerechoN = new JPanel();
		
		panelIzquierdoN.setBounds(45, 178, 244, 256);

		panelDerechoN.setBounds(518, 178, 244, 256);
		panelIzquierdoN.setLayout(new BorderLayout());
		
		panelDerechoN.setLayout(new BorderLayout());
		
		panelIzquierdoN.setOpaque(false);
		
		panelDerechoN.setOpaque(false);
		
		JPanel panelCentro = new JPanel();
		
		panelCentro.setOpaque(false);
		
		JPanel panelCentro2 = new JPanel();
		
		panelCentro2.setOpaque(false);
		
		panelIzquierdoS.setBounds(45, 178, 244, 256);

		panelDerechoS.setBounds(518, 178, 244, 256);
		

		label.setBounds(148, 32, 531, 41);

		label.setFont(new Font("Comic Sans MS", Font.BOLD, 29));

		// Aņadimos al panel

		getContentPane().setLayout(null);

	
		if (listaNombrePartidas.size() == 0) {
			getContentPane().add(seņal);
			getContentPane().add(avisoSeņal);
		} else {
			if (listaNombrePartidas.size()>14){
			
			getContentPane().add(panelDerechoS);
			
			}else{
				
				panelDerechoN.add(tabla2.getTableHeader(),BorderLayout.NORTH);
				panelDerechoN.add(panelCentro2,BorderLayout.CENTER);
				panelCentro2.add(tabla2);
				getContentPane().add(panelDerechoN);
			}
		}

		if (listaCodigos.size() == 0) {
			getContentPane().add(seņalInvertida);
			getContentPane().add(avisoSeņalInvertida);
		} else {
			if (listaCodigos.size()>14){
				
			getContentPane().add(panelIzquierdoS);
			
			}else{
				panelIzquierdoN.add(tabla.getTableHeader(),BorderLayout.NORTH);
				panelIzquierdoN.add(panelCentro,BorderLayout.CENTER);
				panelCentro.add(tabla);
				getContentPane().add(panelIzquierdoN);
			}
			
		}

		getContentPane().add(label);

		getContentPane().add(carretera);

		getContentPane().add(carreteraInvertida);

		getContentPane().add(labelPAbierta);
		
		getContentPane().add(labelPGuardada);
		
		getContentPane().add(poste);

		getContentPane().add(fondo);

	}
	public ArrayList<Icon> meterImgEnlabel(String [] ruta, int largo, int ancho,Connection con,int codp) {
		ordenicon=new ArrayList<String>();
		
		ArrayList<Jugador> arrjugadores =gestion.consultaATablaJugador(con,"COD_PARTIDA="+codp);
		ArrayList<Icon> array=new ArrayList<Icon>();
		for(int i=0;i<ruta.length;i++){
			int k=0;
			for(int j=0;j<arrjugadores.size();j++){
				if(arrjugadores.get(j).getFicha().equals(ruta[i])){
					k=k+1;
				}
			}
			
			if(k==0){
		JLabel label=new JLabel();
		label.setSize(ancho,largo);
		ImageIcon imicon = new ImageIcon(VentanaBuscarPartida.class.getResource(ruta[i]));
		label.setSize(largo, ancho);
		Icon icono = new ImageIcon(
				imicon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono);
		array.add(icono);
		ordenicon.add(ruta[i]);
			}
			
		}
		
			
		return array;
	}
	public int pasarAstring(Icon icono,ArrayList<Icon> arricono){
		for(int i=0;i<arricono.size();i++){
			if(icono.equals(arricono.get(i))){
				return i;
			}
		}return 10;
		
	}

}

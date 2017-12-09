package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import espectaculo.Espectaculo;

public class PanelPrincipalProveedor extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelCentral;

	private Controlador controlador;
	private String proveedor;
	
	public PanelPrincipalProveedor(Controlador controlador, String proveedor) {
		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;
		this.proveedor = proveedor;
		
		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);
	}

	private class PanelSuperior extends JPanel {

		JPanel panelBotones = new JPanel();

		ImageIcon icono1 = new ImageIcon(PanelRegistro.class.getResource("/paneles/exit.png"));
		ImageIcon icono2 = new ImageIcon(PanelRegistro.class.getResource("/paneles/home.png"));
		ImageIcon icono3 = new ImageIcon(PanelRegistro.class.getResource("/paneles/agregar.png"));
		
		JButton btnSalir = new JButton();
		JButton btnPerfil = new JButton();
		JButton btnAgregar = new JButton();
		
		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));

			panelBotones.setBackground(Color.WHITE);
			add(panelBotones, BorderLayout.EAST);

			inicializarBotones();

		}

		private void inicializarBotones() {
			btnAgregar.setIcon(icono3);
			btnAgregar.setBorderPainted(false);
			btnAgregar.setPreferredSize(new Dimension(icono3.getIconWidth(), icono3.getIconHeight()));
			btnAgregar.setBackground(Color.WHITE);
			panelBotones.add(btnAgregar, BorderLayout.CENTER);
			
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("agregar", PanelPrincipalProveedor.this, controlador, proveedor);
				}
			});
			
			btnPerfil.setIcon(icono2);
			btnPerfil.setBorderPainted(false);
			btnPerfil.setPreferredSize(new Dimension(icono2.getIconWidth(), icono2.getIconHeight()));
			btnPerfil.setBackground(Color.WHITE);
			panelBotones.add(btnPerfil, BorderLayout.CENTER);
			
			btnPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("perfilProveedor", PanelPrincipalProveedor.this, controlador, proveedor);
				}
			});

			btnSalir.setIcon(icono1);
			btnSalir.setBorderPainted(false);
			btnSalir.setPreferredSize(new Dimension(icono1.getIconWidth(), icono1.getIconHeight()));
			btnSalir.setBackground(Color.WHITE);
			panelBotones.add(btnSalir, BorderLayout.EAST);
			
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("menu", PanelPrincipalProveedor.this, controlador);
				}
			});
		}
		
	
	}

	private class PanelCentral extends JPanel {
		
		JPanel panelTabla = new JPanel();
		JTable tabla = new JTable();
		
		JPanel panelTitulo = new JPanel();
		JLabel lblEspectaculosEnCartelera = new JLabel("ESPECTACULOS EN CARTELERA");
		
		JPanel panelCentralInferior = new JPanel();
		JButton btnEliminar = new JButton("Eliminar espectaculo");
		JButton btnModificar = new JButton("Modificar espectaculo");
		
		Set<Espectaculo> espectaculos = controlador.obtenerEspectaculosProveedor(proveedor);
		
		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));
			
			panelTabla.setBackground(Color.WHITE);
			add(panelTabla, BorderLayout.CENTER);
			
			
			if(espectaculos.size() > 0)
				inicializarTabla();
			else {
				JLabel subtitulo = new JLabel("No tienes ningun espectáculo agregado al sistema");
				panelTabla.add(subtitulo);
				subtitulo.setFont(new Font("Dialog", Font.PLAIN, 15));
			}
				
			
			panelTitulo.setForeground(Color.WHITE);
			FlowLayout fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
			fl_panelTitulo.setVgap(10);
			panelTitulo.setBackground(new Color(153, 204, 255));
			add(panelTitulo, BorderLayout.NORTH);
			
			inicializarTitulo();
			
			panelCentralInferior.setBackground(Color.WHITE);
			FlowLayout fl_panelCentralInferior = (FlowLayout) panelCentralInferior.getLayout();
			fl_panelCentralInferior.setHgap(20);
			fl_panelCentralInferior.setVgap(30);
			add(panelCentralInferior, BorderLayout.SOUTH);
			
			inicializarPanelInferior();
		}
		
		private void inicializarTabla() {
			tabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			tabla.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 15));
			tabla.setFont(new Font("Dialog", Font.PLAIN, 13));
			tabla.setForeground(Color.BLACK);
			
			
			DefaultTableModel model = new DefaultTableModel(new String[] { "Espect\u00E1culo", "Vendidas", "Remanentes" }, 0);
			
			for(Espectaculo e : espectaculos)
				model.addRow(new Object[]{e.getNombre(), "0" /*CAMBIAR*/, e.getCantidadEntradas()});
			
			tabla.setModel(model);
			tabla.getColumnModel().getColumn(0).setPreferredWidth(300);
			tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
			tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
			panelTabla.add(tabla.getTableHeader());
			panelTabla.add(tabla);
		}
		
		private void inicializarTitulo() {
			lblEspectaculosEnCartelera.setForeground(Color.WHITE);
			panelTitulo.add(lblEspectaculosEnCartelera);
			lblEspectaculosEnCartelera.setFont(new Font("Dialog", Font.BOLD, 18));
			lblEspectaculosEnCartelera.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		private void inicializarPanelInferior() {
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setFont(new Font("Dialog", Font.BOLD, 14));
			btnModificar.setBackground(new Color(0, 102, 204));
			panelCentralInferior.add(btnModificar);
			
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = tabla.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Por favor, elija el espectáculo a modificar", "Error!", JOptionPane.ERROR_MESSAGE);
					else {
						VistaTicketNow.changePanel("modificar", PanelPrincipalProveedor.this, controlador, proveedor);
					}
				}
			});
			
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Dialog", Font.BOLD, 14));
			btnEliminar.setBackground(Color.RED);
			panelCentralInferior.add(btnEliminar);
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = tabla.getSelectedRow();
					if(row == -1)
						JOptionPane.showMessageDialog(null, "Por favor, elija el espectáculo a eliminar", "Error!", JOptionPane.ERROR_MESSAGE);
					else {
						((DefaultTableModel)tabla.getModel()).removeRow(row);
					}
				}
			});
		}
		
	}
}

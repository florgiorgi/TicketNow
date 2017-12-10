package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controlador.Controlador;
import espectaculo.Espectaculo;

public class PanelPrincipalCliente extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelCentral;

	private Controlador controlador;
	private String cliente;
	
	JTextField txtBuscar = new JTextField();

	public PanelPrincipalCliente(Controlador controlador, String cliente) {
		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;
		this.cliente = cliente;

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

		panelDerecho = new PanelDerecho();
		panelDerecho.setBackground(Color.WHITE);
		add(panelDerecho, BorderLayout.EAST);

	}

	private class PanelSuperior extends JPanel {
		JPanel panelSuperiorCentral = new JPanel();

		JLabel title = new JLabel();

		JPanel panelSuperiorDerecho = new JPanel();
		JPanel panelBotones = new JPanel();

		ImageIcon icono1 = new ImageIcon(PanelRegistro.class.getResource("/paneles/exit.png"));
		ImageIcon icono2 = new ImageIcon(PanelRegistro.class.getResource("/paneles/home.png"));
		ImageIcon icono3 = new ImageIcon(PanelRegistro.class.getResource("/paneles/carrito.png"));

		JButton btnSalir = new JButton("");
		JButton btnPerfil = new JButton("");
		JButton btnCompra = new JButton("");

		JPanel panelSuperiorIzquierdo = new JPanel();

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));

			panelSuperiorCentral.setLayout(new BorderLayout(0, 0));
			panelSuperiorCentral.setBackground(Color.WHITE);
			add(panelSuperiorCentral, BorderLayout.CENTER);

			panelSuperiorDerecho.setBackground(Color.WHITE);
			panelSuperiorDerecho.setLayout(new BorderLayout(0, 0));
			add(panelSuperiorDerecho, BorderLayout.EAST);

			panelSuperiorIzquierdo.setBackground(Color.WHITE);
			panelSuperiorIzquierdo.setLayout(new BorderLayout(0, 0));
			add(panelSuperiorIzquierdo, BorderLayout.WEST);

			inicializarCampos();
		}

		private void inicializarCampos() {

			title.setHorizontalAlignment(SwingConstants.LEFT);
			title.setFont(new Font("Dialog", Font.BOLD, 30));
			panelSuperiorCentral.add(title, BorderLayout.CENTER);

			txtBuscar.setText("Buscar...");
			txtBuscar.setFont(new Font("Dialog", Font.PLAIN, 15));
			txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
			txtBuscar.setColumns(10);
			panelSuperiorCentral.add(txtBuscar, BorderLayout.SOUTH);

			panelSuperiorDerecho.add(panelBotones, BorderLayout.CENTER);
			panelBotones.setBackground(Color.WHITE);
			panelBotones.setLayout(new BorderLayout(0, 0));

			btnSalir.setIcon(icono1);
			btnSalir.setBorderPainted(false);
			btnSalir.setPreferredSize(new Dimension(icono1.getIconWidth(), icono1.getIconHeight()));
			btnSalir.setBackground(Color.WHITE);
			panelBotones.add(btnSalir, BorderLayout.EAST);

			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("menu", PanelPrincipalCliente.this, controlador);
				}
			});

			btnPerfil.setIcon(icono2);
			btnPerfil.setBorderPainted(false);
			btnPerfil.setPreferredSize(new Dimension(icono2.getIconWidth(), icono2.getIconHeight()));
			btnPerfil.setBackground(Color.WHITE);
			panelBotones.add(btnPerfil, BorderLayout.CENTER);

			btnPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("perfilCliente", PanelPrincipalCliente.this, controlador, cliente);
				}
			});

			btnCompra.setIcon(icono3);
			btnCompra.setBorderPainted(false);
			btnCompra.setPreferredSize(new Dimension(icono3.getIconWidth(), icono3.getIconHeight()));
			btnCompra.setBackground(Color.WHITE);
			panelBotones.add(btnCompra, BorderLayout.WEST);

			btnCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("finalizarCompra", PanelPrincipalCliente.this, controlador, cliente);
				}
			});

			JLabel labelSpace = new JLabel("                                       ");

			panelSuperiorDerecho.add(labelSpace, BorderLayout.WEST);

			JLabel labelSpace1 = new JLabel("              ");
			panelSuperiorIzquierdo.add(labelSpace1, BorderLayout.NORTH);
		}
	}

	private class PanelCentral extends JPanel {

		JLabel lblFiltroLugar = new JLabel("Filtrar por lugar");
		JLabel lblFiltrarPromocion = new JLabel("Filtrar por promoción");
		JLabel lblFiltrarEstreno = new JLabel("Filtrar por estreno");

		JList listLugar = new JList();
		JList listPromocion = new JList();
		JList listEstreno = new JList();

		JButton btnBuscar = new JButton("Buscar");

		public PanelCentral() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			inicializarCampos();
		}

		private void inicializarCampos() {
			JLabel lblSpace = new JLabel(" ");
			lblSpace.setFont(new Font("Dialog", Font.PLAIN, 40));
			add(lblSpace);

			lblFiltroLugar.setForeground(new Color(0, 51, 102));
			lblFiltroLugar.setAlignmentX(1.0f);
			lblFiltroLugar.setFont(new Font("Dialog", Font.PLAIN, 20));
			add(lblFiltroLugar);

			
			listLugar.setAlignmentX(1.0f);
			listLugar.setFont(new Font("Dialog", Font.PLAIN, 18));
			listLugar.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			listLugar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listLugar.setToolTipText("");
			listLugar.setModel(new AbstractListModel() {
				String[] values = new String[] { "Teatro", "Cine", "Cancha" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			add(listLugar);

			JLabel lblSpace1 = new JLabel(" ");
			lblSpace1.setFont(new Font("Dialog", Font.PLAIN, 30));
			add(lblSpace1);

			lblFiltrarPromocion.setForeground(new Color(0, 51, 102));
			lblFiltrarPromocion.setFont(new Font("Dialog", Font.PLAIN, 20));
			lblFiltrarPromocion.setAlignmentX(0.75f);
			add(lblFiltrarPromocion);

			listPromocion.setFont(new Font("Dialog", Font.PLAIN, 18));
			listPromocion.setAlignmentX(0.32f);
			listPromocion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			listPromocion.setModel(new AbstractListModel() {
				String[] values = new String[] { "2x1", "Banco Asociados", "Descuento a Jubilados" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			add(listPromocion);

			JLabel lblSpace2 = new JLabel(" ");
			lblSpace2.setFont(new Font("Dialog", Font.PLAIN, 30));
			add(lblSpace2);

			lblFiltrarEstreno.setForeground(new Color(0, 51, 102));
			lblFiltrarEstreno.setFont(new Font("Dialog", Font.PLAIN, 20));
			lblFiltrarEstreno.setAlignmentX(0.85f);
			lblFiltrarEstreno.setAlignmentY(Component.TOP_ALIGNMENT);
			add(lblFiltrarEstreno);

			listEstreno.setFont(new Font("Dialog", Font.PLAIN, 18));
			listEstreno.setAlignmentX(0.4f);
			listEstreno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			listEstreno.setModel(new AbstractListModel() {
				String[] values = new String[] { "En cartelera", "Proximos 6 meses", "Proximo año" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			add(listEstreno);

			JLabel lblSpace3 = new JLabel(" ");
			lblSpace3.setFont(new Font("Dialog", Font.PLAIN, 30));
			add(lblSpace3);
			
				
			btnBuscar.setAlignmentX(0.65f);
			btnBuscar.setFont(new Font("Dialog", Font.BOLD, 15));
			btnBuscar.setForeground(Color.WHITE);
			btnBuscar.setBackground(new Color(0, 102, 204));
			add(btnBuscar);

			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String busqueda = null;
					String lugar = null;
					String promocion = null;
					String estreno = null;
					if(!txtBuscar.getText().equals("Buscar..."))
						busqueda = txtBuscar.getText();
					if(!listLugar.isSelectionEmpty())
						lugar = (String) listLugar.getSelectedValue();
					if(!listPromocion.isSelectionEmpty())
						promocion = (String) listPromocion.getSelectedValue();
					
					
					Set<Espectaculo> espectaculos = controlador.obtenerEspectaculoPorCondicion(busqueda, lugar, promocion, estreno);
					VistaTicketNow.changePanel("lista", PanelPrincipalCliente.this, controlador, cliente, espectaculos);
				}
			});
		}

	}

	private class PanelDerecho extends JPanel {

		JLabel lbl1 = new JLabel();
		JLabel lbl2 = new JLabel();
		JLabel lbl3 = new JLabel();

		public PanelDerecho() {
			setLayout(new GridLayout(0, 1, 0, 0));

			ImageIcon icono1 = new ImageIcon(PanelRegistro.class.getResource("/paneles/cars.png"));
			lbl1.setIcon(icono1);
			lbl1.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbl1);

			ImageIcon icono2 = new ImageIcon(PanelRegistro.class.getResource("/paneles/bm.png"));
			lbl2.setIcon(icono2);
			lbl2.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbl2);

			ImageIcon icono3 = new ImageIcon(PanelRegistro.class.getResource("/paneles/loll.png"));
			lbl3.setIcon(icono3);
			lbl3.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbl3);
		}
	}
}

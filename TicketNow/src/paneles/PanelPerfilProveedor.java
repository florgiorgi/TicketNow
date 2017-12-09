package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import usuario.Proveedor;


public class PanelPerfilProveedor extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;

	private Controlador controlador;
	private String proveedor;
	
	public PanelPerfilProveedor(Controlador controlador, String proveedor) {
		/* Tendria que recibir el usuario para completar los campos modificables con sus datos */
		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;
		this.proveedor = proveedor;

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(Color.WHITE);
		FlowLayout flowLayout1 = (FlowLayout) panelDerecho.getLayout();
		flowLayout1.setHgap(50);
		add(panelDerecho, BorderLayout.WEST);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Color.WHITE);
		FlowLayout flowLayout2 = (FlowLayout) panelIzquierdo.getLayout();
		flowLayout2.setHgap(50);
		add(panelIzquierdo, BorderLayout.EAST);

	}

	private class PanelSuperior extends JPanel {
		private JButton btnVolver = new JButton("");
		private JLabel titulo = new JLabel("      Modifique sus datos:");

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			inicializarTitulo();
			inicializarBotones();
		}

		/**
		 * Método que inicializa el titulo y lo agrega al panel
		 */
		private void inicializarTitulo() {
			titulo.setFont(new Font("Dialog", Font.BOLD, 22));
			add(titulo, BorderLayout.CENTER);
		}

		/**
		 * Método que inicializa el boton volver y lo agrega al panel
		 */
		private void inicializarBotones() {
			btnVolver.setBackground(Color.WHITE);
			ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));
			btnVolver.setIcon(icono);
			btnVolver.setPreferredSize(new Dimension(icono.getIconWidth(), icono.getIconHeight()));
			btnVolver.setBorderPainted(false);
			add(btnVolver, BorderLayout.WEST);

			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("proveedor", PanelPerfilProveedor.this, controlador, proveedor);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {

		private JPanel panelRegistrarse = new JPanel();

		private JLabel lblTipoUsuario = new JLabel("Tipo de usuario:");
		private JLabel lblUsuario = new JLabel("Usuario:");
		private JLabel lblContraseña = new JLabel("Contraseña:");
		private JLabel lblNombre = new JLabel("Nombre:");
		private JLabel lblApellido = new JLabel("Apellido:");
		private JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		private JLabel lblDireccionDeCorreo = new JLabel("E-mail:");
		private JLabel lblTelefono = new JLabel("Teléfono:");
		private JLabel lblTipoDeDocumento = new JLabel("Tipo de documento:");
		private JLabel lblDni = new JLabel("Numero de documento:");
		private JLabel lblPais = new JLabel("Pais:");
		private JLabel lblProvincia = new JLabel("Provincia:");
		private JLabel lblLocalidad = new JLabel("Localidad:");
		private JLabel lblDireccion = new JLabel("Direccion:");
		private JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		
		Proveedor p = controlador.obtenerProveedor(proveedor);
		
		private JComboBox tipoUsuario = new JComboBox();
		private JTextField usuarioField = new JTextField(p.getUsuario());
		private JTextField contraseñaField = new JTextField(p.getContraseña());
		private JTextField nombreField = new JTextField(p.getNombre());
		private JTextField apellidoField = new JTextField(p.getApellido());
		private JTextField fechaNacimientoField = new JTextField(p.getFechaNac());
		private JTextField direccionCorreoField = new JTextField(p.getMail());
		private JTextField telefonoField = new JTextField(p.getTelefono());
		private JComboBox tipoDocumentoBox = new JComboBox();
		private JTextField dniField = new JTextField(p.getDNI());
		private JComboBox paisBox = new JComboBox();
		private JComboBox provinciaBox = new JComboBox();
		private JTextField localidadField = new JTextField(p.getLocalidad());
		private JTextField direccionField = new JTextField(p.getDireccion());
		private JTextField codigoPostalField = new JTextField(p.getCodigoPostal());
		private JPanel panelInferior;

		private Font fuente = new Font("Dialog", Font.BOLD, 14);
		private Font fuente1 = new Font("Dialog", Font.PLAIN, 14);

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelRegistrarse.setLayout(new GridLayout(16, 10, 15, 12));
			panelRegistrarse.setBackground(Color.WHITE);
			add(panelRegistrarse, BorderLayout.CENTER);

			inicializarCampos();

			panelInferior = new PanelInferior();
			panelInferior.setBackground(Color.WHITE);
			add(panelInferior, BorderLayout.SOUTH);
		}

		private void inicializarCampos() {

			lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTipoUsuario);
			lblTipoUsuario.setFont(fuente);

			tipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Proveedor", "Cliente" }));
			tipoUsuario.setEnabled(false);
			panelRegistrarse.add(tipoUsuario);
			tipoUsuario.setFont(fuente1);

			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblUsuario);
			lblUsuario.setFont(fuente);

			usuarioField.setColumns(10);
			panelRegistrarse.add(usuarioField);
			usuarioField.setFont(fuente1);

			lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblContraseña);
			lblContraseña.setFont(fuente);

			contraseñaField.setColumns(10);
			contraseñaField.setEditable(false);
			panelRegistrarse.add(contraseñaField);
			contraseñaField.setFont(fuente1);

			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblNombre);
			lblNombre.setFont(fuente);

			nombreField.setColumns(10);
			panelRegistrarse.add(nombreField);
			nombreField.setFont(fuente1);

			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblApellido);
			lblApellido.setFont(fuente);

			apellidoField.setColumns(10);
			panelRegistrarse.add(apellidoField);
			apellidoField.setFont(fuente1);

			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblFechaNacimiento);
			lblFechaNacimiento.setFont(fuente);

			fechaNacimientoField.setColumns(10);
			panelRegistrarse.add(fechaNacimientoField);
			fechaNacimientoField.setFont(fuente1);

			lblDireccionDeCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDireccionDeCorreo);
			lblDireccionDeCorreo.setFont(fuente);

			direccionCorreoField.setColumns(10);
			direccionCorreoField.setEditable(false);
			panelRegistrarse.add(direccionCorreoField);
			direccionCorreoField.setFont(fuente1);

			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTelefono);
			lblTelefono.setFont(fuente);

			telefonoField.setColumns(10);
			panelRegistrarse.add(telefonoField);
			telefonoField.setFont(fuente1);

			lblTipoDeDocumento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTipoDeDocumento);
			lblTipoDeDocumento.setFont(fuente);

			tipoDocumentoBox.setModel(new DefaultComboBoxModel(new String[] { "DNI", "CI", "Pasaporte" }));
			panelRegistrarse.add(tipoDocumentoBox);
			tipoDocumentoBox.setFont(fuente1);

			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDni);
			lblDni.setFont(fuente);

			dniField.setColumns(10);
			dniField.setEditable(false);
			panelRegistrarse.add(dniField);
			dniField.setFont(fuente1);

			lblPais.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblPais);
			lblPais.setFont(fuente);

			paisBox.setModel(
					new DefaultComboBoxModel(new String[] { "Seleccione pais", "Argentina", "Paraguay", "Uruguay" }));
			panelRegistrarse.add(paisBox);
			paisBox.setSelectedItem(p.getPais());
			paisBox.setFont(fuente1);

			lblProvincia.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblProvincia);
			lblProvincia.setFont(fuente);
			
			DefaultComboBoxModel provinciasArgentina = new DefaultComboBoxModel(new String[] { "Buenos Aires", "Catamarca",
					"Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy",
					"La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro", "Salta",
					"San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero",
					"Tierra del Fuego", "Tucumán" });
			
			DefaultComboBoxModel provinciasUruguay = new DefaultComboBoxModel(new String[] { "Asunción", "Concepcion",
					"San Pedro", "Cordillera", "Guairá", "Caaguazú", "Caazapá", "Itapúa", "Misiones",
					"Paraguarí", "Alto Paraná", "Central", "Ñeembucú", "Amambay", "Canindeyú",
					"Presidente Hayes", "Boquerón", "Alto Paraguay" });
			
			
			DefaultComboBoxModel provinciasParaguay = new DefaultComboBoxModel(new String[] { "Artigas", "Canelones",
					"Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado",
					"Montevideo", "Paysandú", "Rio Negro", "Rivera", "Rocha", "Salto", "San Jose",
					"Soriano", "Tacuarembó", "Treinta y Tres" });
			
			
			switch(p.getPais()) {
				case "Argentina" : provinciaBox.setModel(provinciasArgentina);
				break;
				case "Uruguay" : provinciaBox.setModel(provinciasUruguay);
				break;
				case "Paraguay": provinciaBox.setModel(provinciasParaguay);
				break;
			}
			
			provinciaBox.setSelectedItem(p.getProvincia());
			paisBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						switch (e.getItem().toString()) {
						case "Argentina":
							provinciaBox.setModel(provinciasArgentina);
							break;
						case "Paraguay":
							provinciaBox.setModel(provinciasUruguay);
							break;
						case "Uruguay":
							provinciaBox.setModel(provinciasParaguay);
							break;
						}
					}
				}
			});

			panelRegistrarse.add(provinciaBox);
			provinciaBox.setFont(fuente1);
			provinciaBox.setSelectedItem(p.getProvincia());

			lblLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblLocalidad);
			lblLocalidad.setFont(fuente);

			localidadField.setColumns(10);
			panelRegistrarse.add(localidadField);
			localidadField.setFont(fuente1);

			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDireccion);
			lblDireccion.setFont(fuente);

			direccionField.setColumns(10);
			panelRegistrarse.add(direccionField);
			direccionField.setFont(fuente1);

			lblCodigoPostal.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblCodigoPostal);
			lblCodigoPostal.setFont(fuente);

			codigoPostalField.setColumns(10);
			panelRegistrarse.add(codigoPostalField);
			codigoPostalField.setFont(fuente1);

		}

		private class PanelInferior extends JPanel {
			JButton btnModificarDatos = new JButton("Modificar datos");
			JButton btnEliminarCuenta = new JButton("Eliminar cuenta");
			
			public PanelInferior() {
				inicializarBotones();
			}

			private void inicializarBotones() {
				btnModificarDatos.setFont(new Font("Dialog", Font.BOLD, 15));
				btnModificarDatos.setVerticalAlignment(SwingConstants.TOP);
				btnModificarDatos.setForeground(new Color(255, 255, 255));
				btnModificarDatos.setBackground(new Color(0, 102, 204));
				add(btnModificarDatos);

				btnEliminarCuenta.setFont(new Font("Dialog", Font.BOLD, 15));
				btnEliminarCuenta.setVerticalAlignment(SwingConstants.TOP);
				btnEliminarCuenta.setForeground(new Color(255, 255, 255));
				btnEliminarCuenta.setBackground(Color.RED);
				add(btnEliminarCuenta);	
				
				btnEliminarCuenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VistaTicketNow.changePanel("bajaProveedor", PanelPerfilProveedor.this, controlador, proveedor);
					}
				});
						

		}

	}

	}
}


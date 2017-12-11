package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import compra.Compra;
import controlador.Controlador;
import database.NoHayEntradasRemanentesException;
import espectaculo.Espectaculo;

public class PanelFinalizarCompra extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelCentral;

	private Controlador controlador;
	private String cliente;

	public PanelFinalizarCompra(Controlador controlador, String cliente) {
		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;
		this.cliente = cliente;

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

	}

	private class PanelSuperior extends JPanel {

		JButton btnVolver = new JButton();
		ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));

		JLabel lblNombreDelEspectculo = new JLabel("  Finalizar compra");

		JPanel panelTitulo = new JPanel();
		JLabel lblEspectculosEnEl = new JLabel("Espectáculos en el carrito:");

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			initializePanelSuperior();

		}

		private void initializePanelSuperior() {
			btnVolver.setBackground(Color.WHITE);
			btnVolver.setIcon(icono);
			btnVolver.setPreferredSize(new Dimension(icono.getIconWidth(), icono.getIconHeight()));
			btnVolver.setBorderPainted(false);
			add(btnVolver, BorderLayout.WEST);

			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("cliente", PanelFinalizarCompra.this, controlador, cliente);
				}
			});

			lblNombreDelEspectculo.setFont(new Font("Dialog", Font.BOLD, 25));
			add(lblNombreDelEspectculo);

			panelTitulo.setBackground(Color.WHITE);
			add(panelTitulo, BorderLayout.SOUTH);

			lblEspectculosEnEl.setFont(new Font("Dialog", Font.PLAIN, 20));
			panelTitulo.add(lblEspectculosEnEl);
		}
	}

	private class PanelCentral extends JPanel {

		Set<Compra> compras = controlador.obtenerComprasCliente(cliente);

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			JPanel panelCompras = new JPanel();
			panelCompras.setBackground(Color.WHITE);
			add(panelCompras, BorderLayout.CENTER);
			panelCompras.setLayout(new BoxLayout(panelCompras, BoxLayout.Y_AXIS));

			
			
			JPanel panelTabla = new JPanel();
			panelTabla.setBackground(Color.WHITE);
			panelCompras.add(panelTabla);
			
			
			JTable table_1 = new JTable();
			
			JPanel panelFinalizar = new JPanel();
			panelFinalizar.setBackground(Color.WHITE);
			add(panelFinalizar, BorderLayout.SOUTH);
			panelFinalizar.setLayout(new BoxLayout(panelFinalizar, BoxLayout.Y_AXIS));

			JLabel lblNewLabel = new JLabel(" ");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			panelFinalizar.add(lblNewLabel);

			JPanel panelTotal = new JPanel();
			panelTotal.setBackground(new Color(153, 204, 255));
			panelFinalizar.add(panelTotal);

			JLabel lblSubtotal = new JLabel("Total: ");
			panelTotal.add(lblSubtotal);
			lblSubtotal.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubtotal.setFont(new Font("Dialog", Font.BOLD, 30));
			
			if(compras.size() > 0) {
			

			DefaultTableModel model = new DefaultTableModel(
					new String[] { "Espect\u00E1culo", "Lugar", "Cantidad", "Total" }, 0);

			for (Compra c : compras) {
				model.addRow(new Object[] { c.getEspectaculo(), c.getLugar(), c.getCantidadEntradas(),
						c.getTotal() });
			}

			table_1.setFont(new Font("Dialog", Font.PLAIN, 13));
			table_1.setForeground(Color.BLACK);
			table_1.setModel(model);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(300);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(130);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(130);
			panelTabla.add(table_1.getTableHeader());
			panelTabla.add(table_1);
			} else {
				JLabel labelmsg = new JLabel("No tienes espectaculos agregados al carrito");
				labelmsg.setFont(new Font("Dialog", Font.PLAIN, 14));
				panelTabla.add(labelmsg);
			}
			
			Integer sb = 0;
			for(int i=0; i<table_1.getRowCount(); i++) {
				sb += (Integer)table_1.getValueAt(i, 3);
			}
			String subTotal = "" + sb;
			JLabel subtotal = new JLabel(subTotal);
			panelTotal.add(subtotal);
			subtotal.setAlignmentX(Component.CENTER_ALIGNMENT);
			subtotal.setFont(new Font("Dialog", Font.PLAIN, 16));
			
			
			
			JPanel panelBotones = new JPanel();
			panelBotones.setBackground(Color.WHITE);
			FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
			fl_panelBotones.setHgap(20);
			panelCompras.add(panelBotones);

			JButton btnModificarCantidad = new JButton("Modificar cantidad");
			btnModificarCantidad.setForeground(Color.WHITE);
			btnModificarCantidad.setBackground(new Color(0, 102, 204));
			btnModificarCantidad.setFont(new Font("Dialog", Font.BOLD, 13));
			panelBotones.add(btnModificarCantidad);

			Integer valor = sb;
			btnModificarCantidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(compras.size() > 0) {
					ImageIcon icono_ticket = new ImageIcon(
							PanelRegistro.class.getResource("/paneles/icono-ticket.png"));

					int row = table_1.getSelectedRow();
					if (row == -1)
						JOptionPane.showMessageDialog(null, "Por favor, elija el espectaculo a modificar", "Error!",
								JOptionPane.ERROR_MESSAGE);
					else {
						Integer cantidad = (Integer) table_1.getValueAt(row, 2);
						int c = cantidad;
						SpinnerNumberModel sModel = new SpinnerNumberModel(c, 0,
								30/* Poner el maximo segun lo q hay en la bd */, 1);
						JSpinner spinner = new JSpinner(sModel);
						JOptionPane optionpane = new JOptionPane();
						UIManager UI = new UIManager();
						UI.put("OptionPane.background", Color.white);
						UI.put("Panel.background", Color.white);

						int option = optionpane.showOptionDialog(null, spinner, "Modificar cantidad",
								JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, icono_ticket, null, null);
						if (option == optionpane.OK_OPTION) {
							int precio = (Integer)table_1.getValueAt(table_1.getSelectedRow(), 3) / (Integer)table_1.getValueAt(table_1.getSelectedRow(), 2);
							Integer resta = (Integer)table_1.getValueAt(table_1.getSelectedRow(), 3);
							try {
								controlador.modificarCompra((String) table_1.getValueAt(table_1.getSelectedRow(), 0),
										(String) table_1.getValueAt(table_1.getSelectedRow(), 1),
										(Integer) spinner.getValue(), cliente, precio);
								table_1.setValueAt(spinner.getValue(), row, 2);
								table_1.setValueAt((Integer)spinner.getValue() * precio, table_1.getSelectedRow(), 3);
								Integer dif = (Integer)table_1.getValueAt(table_1.getSelectedRow(), 3) - resta;
								JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
								Integer value = valor;
								if(dif < 0)
									value = valor + dif;
								else
									value = valor - dif;
								
								subtotal.setText(value + "");
							} catch (NoHayEntradasRemanentesException m1) {
								JOptionPane.showMessageDialog(null, m1.getMessage(), "Ocurrió algo inesperado",
										JOptionPane.ERROR_MESSAGE);
							}
							
							
						}
					}
				}
				 else {
					JOptionPane.showMessageDialog(null, "No tienes espectaculos agregados al carrito para modificar", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}}
				
			});

			JButton btnRemoverEspectaculo = new JButton("Remover espectáculo");
			btnRemoverEspectaculo.setForeground(Color.WHITE);
			btnRemoverEspectaculo.setFont(new Font("Dialog", Font.BOLD, 13));
			btnRemoverEspectaculo.setBackground(Color.RED);
			panelBotones.add(btnRemoverEspectaculo);
			table_1.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));

			btnRemoverEspectaculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(compras.size() > 0) {
					int row = table_1.getSelectedRow();
					if (row == -1)
						JOptionPane.showMessageDialog(null, "Por favor, elija el espectáculo a eliminar", "Error!",
								JOptionPane.ERROR_MESSAGE);
					else {
						controlador.removerCompra((String) table_1.getValueAt(table_1.getSelectedRow(), 0),
								(String) table_1.getValueAt(table_1.getSelectedRow(), 1), cliente);
						((DefaultTableModel) table_1.getModel()).removeRow(row);
						if(table_1.getRowCount() == 0) {
							panelTabla.remove(table_1.getTableHeader());
							JLabel label = new JLabel("No tienes espectaculos agregados al carrito");
							label.setFont(new Font("Dialog", Font.PLAIN, 14));
							panelTabla.add(label);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No tienes espectaculos agregados al carrito para eliminar", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}}
			});

			

			JLabel label_1 = new JLabel(" ");
			panelFinalizar.add(label_1);

			JLabel lblIndiqueSuMedio = new JLabel("Indique su medio de pago:");
			lblIndiqueSuMedio.setFont(new Font("Dialog", Font.BOLD, 17));
			lblIndiqueSuMedio.setAlignmentX(Component.CENTER_ALIGNMENT);
			panelFinalizar.add(lblIndiqueSuMedio);

			JLabel label_2 = new JLabel(" ");
			panelFinalizar.add(label_2);

			JPanel panelMedioPago = new JPanel();
			panelMedioPago.setBackground(Color.WHITE);
			panelFinalizar.add(panelMedioPago);
			panelMedioPago.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			JRadioButton rdbtnTransferenciaBancaria = new JRadioButton("Transferencia bancaria");
			panelMedioPago.add(rdbtnTransferenciaBancaria);
			rdbtnTransferenciaBancaria.setFont(new Font("Dialog", Font.BOLD, 14));
			rdbtnTransferenciaBancaria.setBackground(Color.WHITE);

			JRadioButton rdbtnPagoConTarjeta = new JRadioButton("Tarjeta crédito/débito");
			panelMedioPago.add(rdbtnPagoConTarjeta);
			rdbtnPagoConTarjeta.setAlignmentY(Component.TOP_ALIGNMENT);
			rdbtnPagoConTarjeta.setFont(new Font("Dialog", Font.BOLD, 14));
			rdbtnPagoConTarjeta.setBackground(Color.WHITE);

			JRadioButton rdbtnEfectivo = new JRadioButton("Efectivo");
			panelMedioPago.add(rdbtnEfectivo);
			rdbtnEfectivo.setFont(new Font("Dialog", Font.BOLD, 14));
			rdbtnEfectivo.setBackground(Color.WHITE);

			ButtonGroup group = new ButtonGroup();
			group.add(rdbtnEfectivo);
			group.add(rdbtnPagoConTarjeta);
			group.add(rdbtnTransferenciaBancaria);

			JLabel label_3 = new JLabel(" ");
			panelFinalizar.add(label_3);

			JButton btnFinalizar = new JButton("Finalizar compra");
			btnFinalizar.setFont(new Font("Dialog", Font.BOLD, 15));
			btnFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setBackground(new Color(0, 102, 204));
			panelFinalizar.add(btnFinalizar);

			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnEfectivo.isSelected()) {
						JOptionPane.showMessageDialog(null, "Reserva exitosa. Puede retirar sus entradas en boleteria");
						controlador.eliminarCompras(cliente);
						VistaTicketNow.changePanel("cliente", PanelFinalizarCompra.this, controlador, cliente);
					} else if (rdbtnPagoConTarjeta.isSelected() || rdbtnTransferenciaBancaria.isSelected()) {
						JOptionPane.showMessageDialog(null, "Compra exitosa. Puede retirar sus entradas en boleteria");
						controlador.eliminarCompras(cliente);
						VistaTicketNow.changePanel("cliente", PanelFinalizarCompra.this, controlador, cliente);
					} else
						JOptionPane.showMessageDialog(null, "Por favor, elija su medio de pago", "Error!",
								JOptionPane.ERROR_MESSAGE);

				}
			});

			JLabel label_4 = new JLabel(" ");
			panelFinalizar.add(label_4);
			ImageIcon icono2 = new ImageIcon(PanelRegistro.class.getResource("/paneles/carrito.png"));
			Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		}
	}
}

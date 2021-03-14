package es.studium.login;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaDetallesFactura implements ActionListener, WindowListener
{
	// Ventana Consulta de Proyectos
	Frame frmConsultaDetallesFactura = new Frame("Consulta Detalles Factura");
	TextArea listadoDetallesFactura = new TextArea(4, 50);
	Button btnPdfDetallesFactura = new Button("PDF");
	
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public ConsultaDetallesFactura()
	{
		frmConsultaDetallesFactura.setLayout(new FlowLayout());
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM Proyectos
		sentencia = "SELECT * FROM detallesFacturas";
		// La información está en ResultSet
		// Recorrer el RS y por cada registro,
		// meter una línea en el TextArea
		try
		{
			//Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			listadoDetallesFactura.selectAll();
			listadoDetallesFactura.setText("");
			listadoDetallesFactura.append("id\tTotalIva\tTotalCoste\tidFacturaFK\tidProductoFK\n");
			while(rs.next())
			{
				listadoDetallesFactura.append(rs.getInt("idDetallesFactura")
				+"\t"+rs.getString("totalIVA")
				+"\t"+rs.getString("totalCoste")
				+"\t"+"\t"+rs.getString("idFacturaFK")
				+"\t"+"\t"+rs.getString("idProductoFK"
				)+"\n");
			}
			
			
			
		}
		catch (SQLException sqle)
		{
			listadoDetallesFactura.setText("Se ha producido un error en la consulta");
		}
		finally
		{

		}
		listadoDetallesFactura.setEditable(false);
		frmConsultaDetallesFactura.add(listadoDetallesFactura);
		frmConsultaDetallesFactura.add(btnPdfDetallesFactura);
		frmConsultaDetallesFactura.setSize(400,180);
		frmConsultaDetallesFactura.setResizable(false);
		frmConsultaDetallesFactura.setLocationRelativeTo(null);
		frmConsultaDetallesFactura.addWindowListener(this);
		frmConsultaDetallesFactura.setVisible(true);
	}
	@Override
	public void windowActivated(WindowEvent e)
	{}
	@Override
	public void windowClosed(WindowEvent e)
	{}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(frmConsultaDetallesFactura.isActive())
		{
			frmConsultaDetallesFactura.setVisible(false);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
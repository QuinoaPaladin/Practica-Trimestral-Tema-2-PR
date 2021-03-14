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

public class ConsultaFactura implements ActionListener, WindowListener
{
	// Ventana Consulta de Clientes
	Frame frmConsultaFacturas = new Frame("Consulta Facturas");
	TextArea listadoFacturas = new TextArea(4, 30);
	Button btnPdfFacturas = new Button("PDF");
	
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public ConsultaFactura()
	{
		frmConsultaFacturas.setLayout(new FlowLayout());
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM clientes
		sentencia = "SELECT * FROM facturas";
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
			listadoFacturas.selectAll();
			listadoFacturas.setText("");
			listadoFacturas.append("id\tFecha\t\t\tHora\n");
			while(rs.next())
			{
				String[] fechaEuropea = rs.getString("fechaFactura").split("-");
				listadoFacturas.append(rs.getInt("idFactura")+"\t"+fechaEuropea[2]+"\t"+fechaEuropea[1]+"\t"+fechaEuropea[0]+"\t"+rs.getString("horaFactura")+"\n");
			}
		}
		catch (SQLException sqle)
		{
			listadoFacturas.setText("Se ha producido un error en la consulta");
		}
		finally
		{

		}
		listadoFacturas.setEditable(false);
		frmConsultaFacturas.add(listadoFacturas);
		frmConsultaFacturas.add(btnPdfFacturas);
		frmConsultaFacturas.setSize(250,175);
		frmConsultaFacturas.setResizable(false);
		frmConsultaFacturas.setLocationRelativeTo(null);
		frmConsultaFacturas.addWindowListener(this);
		frmConsultaFacturas.setVisible(true);
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
		if(frmConsultaFacturas.isActive())
		{
			frmConsultaFacturas.setVisible(false);
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
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

public class ConsultaDistribuidor implements ActionListener, WindowListener
{
	// Ventana Consulta de Clientes
	Frame frmConsultaDistribuidores = new Frame("Consulta Distribuidores");
	TextArea listadoDistribuidores = new TextArea(4, 30);
	Button btnPdfDistribuidores = new Button("PDF");
	
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public ConsultaDistribuidor()
	{
		frmConsultaDistribuidores.setLayout(new FlowLayout());
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM clientes
		sentencia = "SELECT * FROM distribuidores";
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
			listadoDistribuidores.selectAll();
			listadoDistribuidores.setText("");
			listadoDistribuidores.append("id\tNombre\tUbicacion\n");
			while(rs.next())
			{
				listadoDistribuidores.append(rs.getInt("idDistribuidor")+"\t"+rs.getString("nombreDistribuidor")+"\t"+rs.getString("ubicacionDistribuidor")+"\n");
			}
		}
		catch (SQLException sqle)
		{
			listadoDistribuidores.setText("Se ha producido un error en la consulta");
		}
		finally
		{

		}
		listadoDistribuidores.setEditable(false);
		frmConsultaDistribuidores.add(listadoDistribuidores);
		frmConsultaDistribuidores.add(btnPdfDistribuidores);
		frmConsultaDistribuidores.setSize(250,175);
		frmConsultaDistribuidores.setResizable(false);
		frmConsultaDistribuidores.setLocationRelativeTo(null);
		frmConsultaDistribuidores.addWindowListener(this);
		frmConsultaDistribuidores.setVisible(true);
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
		if(frmConsultaDistribuidores.isActive())
		{
			frmConsultaDistribuidores.setVisible(false);
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
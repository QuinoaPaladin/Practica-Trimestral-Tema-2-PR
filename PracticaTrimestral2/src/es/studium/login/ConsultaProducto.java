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

public class ConsultaProducto implements ActionListener, WindowListener
{
	// Ventana Consulta de Proyectos
	Frame frmConsultaProducto = new Frame("Consulta Productos");
	TextArea listadoProductos = new TextArea(4, 40);
	Button btnPdfProductos = new Button("PDF");
	
	BaseDatos bd;
	String sentencia = "";
	String subSentencia = "";
	Connection connection = null;
	Statement statement = null;
	Statement statementDistribuidores = null;
	ResultSet rs = null;
	ResultSet rsDistribuidores = null;
	
	public ConsultaProducto()
	{
		frmConsultaProducto.setLayout(new FlowLayout());
		// Conectar
		bd = new BaseDatos();
		connection = bd.conectar();
		// Hacer un SELECT * FROM Proyectos
		sentencia = "SELECT * FROM productos";
		// La información está en ResultSet
		// Recorrer el RS y por cada registro,
		// meter una línea en el TextArea
		try
		{
			//Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statementDistribuidores = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			listadoProductos.selectAll();
			listadoProductos.setText("");
			listadoProductos.append("id\tNombre\tPrecio\tIVA\tDistribuidor\n");
			while(rs.next())
			{
				subSentencia = "SELECT * FROM distribuidores WHERE idDistribuidor="
			+ rs.getString("idDistribuidorFK");
				rsDistribuidores = statementDistribuidores.executeQuery(subSentencia);
				rsDistribuidores.next();
				listadoProductos.append(rs.getInt("idProducto")
						+"\t"+rs.getString("nombreProducto")
						+"\t"+rs.getString("precioProducto")
						+"\t"+rs.getString("IVAProducto")
						+"\t"+rsDistribuidores.getString("nombreDistribuidor")
						+"\t"+rsDistribuidores.getString("ubicacionDistribuidor")
						+"\n");
			}
		}
		catch (SQLException sqle)
		{
			listadoProductos.setText("Se ha producido un error en la consulta");
		}
		finally
		{

		}
		listadoProductos.setEditable(false);
		frmConsultaProducto.add(listadoProductos);
		frmConsultaProducto.add(btnPdfProductos);
		frmConsultaProducto.setSize(400,180);
		frmConsultaProducto.setResizable(false);
		frmConsultaProducto.setLocationRelativeTo(null);
		frmConsultaProducto.addWindowListener(this);
		frmConsultaProducto.setVisible(true);
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
		if(frmConsultaProducto.isActive())
		{
			frmConsultaProducto.setVisible(false);
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
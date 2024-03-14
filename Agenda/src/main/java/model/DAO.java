package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Modulo de conexao *. */
	// paramentros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/agendadb?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "Neto010185!";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD CREATE */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros ? pelo conteudo das variaveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a Query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		String read = "select * from contatos order by nome";
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// Laço abaixo será executado enqaunto houver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	// Selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

/**
 * Deletar contato.
 *
 * @param contato the contato
 */
//CRUD DELETE//
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

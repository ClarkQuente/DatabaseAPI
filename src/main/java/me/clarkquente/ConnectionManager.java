package me.clarkquente;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection con = null;
    private static FileConfiguration config = Main.getInstance().getConfig();

    public ConnectionManager() {
        openConnectionMYSQL();
    }

    public void openConnectionMYSQL() {
        ConfigurationSection configurationSection = config.getConfigurationSection("Storage");
        boolean mode = configurationSection.getBoolean("TYPE");

        if(mode) {

            String USER = configurationSection.getString("Username");
            String HOST = configurationSection.getString("Host");
            String PASSWORD = configurationSection.getString("Password");
            String DATABASE = configurationSection.getString("Database");
            int PORT = configurationSection.getInt("Port");

            String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                createTables();
                Main.sendMessage("Conexão com §fMYSQL §eestabelecida com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
                Main.sendMessage("§cHouve um problema ao estabelecer conexão com §fMYSQL§c.");
                openConnectionSQLite();
            }
        } else openConnectionSQLite();
    }

    public void openConnectionSQLite() {
        File file = new File(Main.getInstance().getDataFolder(), "database.db");

        String URL = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            createTables();
            Main.sendMessage("Conexão com §fSQLITE §eestabelecida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            Main.sendMessage("§cHouve um problema ao estabelecer conexão com §fSQLITE§c.");
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
        }
    }

    private void createTable(String tableName, String colunas) {
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `"+ tableName.toLowerCase() +"` (" + colunas + ")");
            stm.execute();
            stm.close();
            Main.sendMessage("Tabela §f" + tableName.toLowerCase() + "§e criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            Main.sendMessage("§cHouve um erro ao criar a tabela §f" + tableName.toLowerCase() + "§c.");
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
        }
    }

    public void createTables() {
        createTable("informacoes", "nome TEXT, qntitens int");
    }

    public static void close() {
        if(con != null) {
            try {
                con.close();
                con = null;
                Main.sendMessage("A conexão com o banco de dados foi fechada.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Main.sendMessage("§cNão foi possível fechar a conexão com o banco de dados.");
            }
        }
    }

    public Connection getConnection() {
        return this.con;
    }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FLOW
 */

public class Database {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    // Constructor untuk menginisialisasi koneksi database
    public Database() {
        try {
            String url = "jdbc:mysql://localhost/perpustakaan";
            String username = "root";
            String password = "";

            // Membuat koneksi ke database
            this.connection = DriverManager.getConnection(url, username, password);
            this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("MySQL successfully connected");
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    // Method untuk membaca data dari tabel anggota
    protected void readData() {
        try {
            this.resultSet = this.statement.executeQuery("SELECT * FROM anggota"); // Menggunakan tabel anggota
            while (this.resultSet.next()) {
                String nama = this.resultSet.getString("nama");
                String alamat = this.resultSet.getString("alamat");
                String noTelp = this.resultSet.getString("no_telp");
                System.out.println("Nama: " + nama + ", Alamat: " + alamat + ", No Telepon: " + noTelp);
            }
        } catch (SQLException ex) {
            System.out.println("Error reading data: " + ex.getMessage());
        }
    }

    // Method untuk menghapus data berdasarkan ID
    protected void deleteData(int id) {
        try {
            this.preparedStatement = this.connection.prepareStatement("DELETE FROM anggota WHERE id_anggota=?"); // Menggunakan id_anggota
            this.preparedStatement.setInt(1, id);
            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Data dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Data dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting data: " + ex.getMessage());
        }
    }

    // Method untuk menambahkan data baru ke tabel anggota
    protected void insertData(String nama, String alamat, String noTelp) { // Hapus id karena auto_increment
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO anggota (nama, alamat, no_telp) VALUES (?, ?, ?)");
            this.preparedStatement.setString(1, nama);
            this.preparedStatement.setString(2, alamat);
            this.preparedStatement.setString(3, noTelp);
            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Data berhasil ditambahkan.");
            } else {
                System.out.println("Data tidak berhasil ditambahkan.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting data: " + ex.getMessage());
        }
    }

    // Method untuk memperbarui data anggota berdasarkan ID
    protected void updateData(int id, String nama, String alamat, String noTelp) {
        try {
            this.preparedStatement = this.connection.prepareStatement("UPDATE anggota SET nama=?, alamat=?, no_telp=? WHERE id_anggota=?");
            this.preparedStatement.setString(1, nama);
            this.preparedStatement.setString(2, alamat);
            this.preparedStatement.setString(3, noTelp);
            this.preparedStatement.setInt(4, id);
            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Data berhasil diperbarui.");
            } else {
                System.out.println("Data tidak berhasil diperbarui.");
            }
        } catch (SQLException ex) {
            System.out.println("Error updating data: " + ex.getMessage());
        }
    }

    // Method untuk menutup semua resource
    protected final void closeConnection() {
        try {
            if (this.resultSet != null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.connection != null) this.connection.close();
            
            System.out.println("Koneksi berhasil ditutup.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
}
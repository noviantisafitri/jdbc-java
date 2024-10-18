/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jdbc;

/**
 *
 * @author FLOW
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Database();
        
        // Memanggil metode untuk mengelola data
        db.readData(); // Membaca data anggota
        db.deleteData(1); 
        db.insertData("Novi", "Jl. Merdeka", "081234567890"); // Menambahkan anggota baru
        db.updateData(2, "Vera", "Jl. Pramuka", "081987654321"); // Memperbarui anggota dengan ID 2

        // Menutup koneksi
        db.closeConnection();
    }
    
}

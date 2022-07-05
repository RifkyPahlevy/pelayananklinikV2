/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik.tampilan;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Petugas extends javax.swing.JInternalFrame {

    
    String kd_petugas;
    
    DefaultTableModel tabel = new DefaultTableModel();
    
    public Petugas(String kode) {
        
        initComponents();
        this.kd_petugas = kode;
        autoNumber();
        loadData();
        txtKdDokter.setEnabled(false);
        kosong();
    }
    
    
   
    public void kosong() {
        txtNmDokter.setText("");
        txtHpDokter.setText("");
        txtPassword.setText("");
        btnTambah.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        autoNumber();
    }
    
    private void autoNumber() {
        try {

            java.sql.Connection line_konek = (com.mysql.jdbc.Connection)klinik.koneksi.koneksi.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "SELECT MAX(RIGHT(kd_dokter,4)) AS nomor FROM t_dokter";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                txtKdDokter.setText("KP0001");
            } else {
                line_result.last();
                int no = line_result.getInt(1) + 1;
                String nomor = String.valueOf(no);
                int oto = nomor.length();
                switch (oto){
                    case 1: nomor ="000" +nomor; break;
                    case 2: nomor ="00" +nomor; break;
                    case 3: nomor ="0" +nomor; break;
                    case 4: nomor ="" +nomor; break;
                        
                }
                txtKdDokter.setText("KP" + nomor);
            }
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    private void loadData(){
        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try{
            java.sql.Connection conn = (Connection)klinik.koneksi.koneksi.getDB();
            java.sql.Statement stm = conn.createStatement();
            String querry = "SELECT * FROM petugas where kd_petugas = '"+kd_petugas+"'";
            java.sql.ResultSet rs = stm.executeQuery(querry);
            tableDokter.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println("Error Load Data : " + e);
        }
    }
    
    private void simpan(){
        String kd = txtKdDokter.getText();
        String nm = txtNmDokter.getText();
        String hp = txtHpDokter.getText();
        String jk = cmbJenisKelamin.getSelectedItem().toString();
        String spesialis = cmbSpesialis.getSelectedItem().toString();
        
       try{
           Connection conn =(Connection)klinik.koneksi.koneksi.getDB();
           java.sql.Statement stm = conn.createStatement();
           String querry ="INSERT INTO petugas VALUES ('"+kd+"','"+nm+"', '"+jk+"','"+hp+"','"+spesialis+"')";
           stm.executeUpdate(querry);
           JOptionPane.showMessageDialog(this, "Berhasil Menambahkan Petugas Baru", "Sukses", JOptionPane.INFORMATION_MESSAGE);
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Gagal Memasukkan Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
       }
    }
    
    private void ubah(){
        try{
            String kd = txtKdDokter.getText();
            String nm = txtNmDokter.getText();
            String hp = txtHpDokter.getText();
            String jk = cmbJenisKelamin.getSelectedItem().toString();
            String spesialis = cmbSpesialis.getSelectedItem().toString();
            String password = txtPassword.getText();
            
            Connection conn =(Connection)klinik.koneksi.koneksi.getDB();
            String querry ="UPDATE petugas SET nama_petugas='"+nm+"', jenis_kelamin='"+jk+"', no_hp='"+hp+"', profesi='"+spesialis+"', password='"+password+"' WHERE kd_petugas='"+kd+"'";
            com.mysql.jdbc.PreparedStatement prepare = (PreparedStatement)conn.prepareStatement(querry);
            prepare.execute();
            JOptionPane.showMessageDialog(this, "Berhasil mengubah data Petugas", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            btnTambah.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Gagal Mengubah Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    private void hapus(){
        int s_row = tableDokter.getSelectedRow();
        if(s_row==-1){
            return;
        }
        String dataDelete = (String) tableDokter.getValueAt(s_row,0);
        try{
            Connection conn = (Connection)klinik.koneksi.koneksi.getDB();
            String querry = "DELETE FROM petugas WHERE kd_petugas=?";
            java.sql.PreparedStatement stm = (PreparedStatement)conn.prepareStatement(querry);
            stm.setString(1,dataDelete);
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(this, "Berhasil menghapus data petugas", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            btnTambah.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Tidak bisa menghapus data", "Hapus Data", JOptionPane.WARNING_MESSAGE);
        } finally {
            loadData();
        }
    }
    
    private void klik() {
        btnTambah.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        String getKode = tableDokter.getValueAt(tableDokter.getSelectedRow(), 0).toString();
        String getNama = tableDokter.getValueAt(tableDokter.getSelectedRow(), 1).toString();
        String getJk = tableDokter.getValueAt(tableDokter.getSelectedRow(), 2).toString();
        String getHp = tableDokter.getValueAt(tableDokter.getSelectedRow(), 3).toString();
        String getSpesialis = tableDokter.getValueAt(tableDokter.getSelectedRow(), 4).toString();
        String getPass = tableDokter.getValueAt(tableDokter.getSelectedRow(), 5).toString();

        
        txtKdDokter.setText(getKode);
        txtNmDokter.setText(getNama);
        txtHpDokter.setText(getHp);
        cmbJenisKelamin.getModel().setSelectedItem(getJk);
        cmbSpesialis.getModel().setSelectedItem(getSpesialis);
        txtPassword.setText(getPass);
    }
    
public void cetak() {
       try{
            String NamaFile = "src/klinik/laporan/laporan_dokter.jasper";     
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, null, klinik.koneksi.koneksi.getDB());
            JasperViewer.viewReport(JPrint);
        }catch(Exception ex){
            System.out.println(ex);
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKdDokter = new javax.swing.JTextField();
        txtNmDokter = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtHpDokter = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKosong = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbSpesialis = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        cmbJenisKelamin = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDokter = new javax.swing.JTable();
        btnLaporan = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Dokter");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Petugas Pemeriksaan"));

        jLabel1.setText("Kode Petugas");

        jLabel2.setText("Nama Petugas");

        btnTambah.setText("Tambah");
        btnTambah.setPreferredSize(new java.awt.Dimension(97, 48));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jLabel5.setText("Nomor Handphone");

        btnUbah.setText("Ubah");
        btnUbah.setPreferredSize(new java.awt.Dimension(97, 48));
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.setPreferredSize(new java.awt.Dimension(97, 48));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKosong.setText("Kosongkan");
        btnKosong.setPreferredSize(new java.awt.Dimension(97, 48));
        btnKosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKosongActionPerformed(evt);
            }
        });

        jLabel6.setText("Profesi");

        cmbSpesialis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dokter Umum", "Bidan", "Administrasi", " " }));

        jLabel3.setText("Jenis Kelamin");

        cmbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));

        jLabel4.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPassword)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKosong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSpesialis, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(txtNmDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(txtKdDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(txtHpDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel3)
                                .addComponent(cmbJenisKelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKdDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNmDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHpDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSpesialis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKosong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Petugas"));

        tableDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Petugas", "Nama Petugas", "No Handphone Petugas"
            }
        ));
        tableDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDokterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDokter);

        btnLaporan.setText("Cetak Laporan");
        btnLaporan.setPreferredSize(new java.awt.Dimension(97, 48));
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if(txtKdDokter.getText().equals("")|| txtNmDokter.getText().equals("")||txtHpDokter.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Data tidak boleh ada yang kosong","Pemberitahuan",JOptionPane.WARNING_MESSAGE);
        }else{
            simpan();
        }
        loadData();
        autoNumber();
        txtNmDokter.setText("");
        txtHpDokter.setText("");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if(txtKdDokter.getText().equals("")|| txtNmDokter.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Data tidak boleh ada yang kosong", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }else{
            ubah();
        }
        loadData();
        kosong();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try {
            hapus();
            loadData();
            kosong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKosongActionPerformed
        kosong();
    }//GEN-LAST:event_btnKosongActionPerformed

    private void tableDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDokterMouseClicked
        klik();
    }//GEN-LAST:event_tableDokterMouseClicked

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
      cetak();
    }//GEN-LAST:event_btnLaporanActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKosong;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbJenisKelamin;
    private javax.swing.JComboBox<String> cmbSpesialis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDokter;
    private javax.swing.JTextField txtHpDokter;
    private javax.swing.JTextField txtKdDokter;
    private javax.swing.JTextField txtNmDokter;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}

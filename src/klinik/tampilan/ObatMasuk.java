/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik.tampilan;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ObatMasuk extends javax.swing.JInternalFrame {

    
    DefaultTableModel tabel = new DefaultTableModel();
    LocalDateTime localDate = LocalDateTime.now();
    DefaultTableModel tabel2 = new DefaultTableModel();
    
     DateTimeFormatter dateformatter
            = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    public ObatMasuk() {
        initComponents();
        autoNumber();
        loadData();
        txtKdObat.setEnabled(false);
        kosong();
    }
    
    private void daftarObat(){
       tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();
        try{
            java.sql.Connection conn = (Connection)klinik.koneksi.koneksi.getDB();
            java.sql.Statement stm = conn.createStatement();
            String querry_bukatable="SELECT kd_obat, nama_obat, harga_obat, keterangan from obat";
            java.sql.ResultSet rs = stm.executeQuery(querry_bukatable);
            tableDialogObat.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println("Errot load daftar obat : " + e);
        }
    }
    public void kosong() {
        txtNmObat.setText("");
        txtStokObat.setText("");
        txtHargaObat.setText("");
        txtKetObat.setText("");
        btnTambah.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        autoNumber();
    }
    
    private void autoNumber() {
        try {

            java.sql.Connection line_konek = (com.mysql.jdbc.Connection)klinik.koneksi.koneksi.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "SELECT MAX(RIGHT(kd_obat,4)) AS nomor FROM obat";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                txtKdObat.setText("OBT0001");
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
                txtKdObat.setText("OBT" + nomor);
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
            String querry = "SELECT kd_obat, nama_obat, stok_masuk, harga_obat, keterangan FROM obat_masuk";
            java.sql.ResultSet rs = stm.executeQuery(querry);
            tableObat.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println("Error Load Data : " + e);
        }
    }
    
    private void simpan(){
        String date = dateformatter.format(localDate).toString();
        String kd = txtKdObat.getText();
        String nm = txtNmObat.getText();
        String stok = txtStokObat.getText();
        String harga = txtHargaObat.getText();
        String ket = txtKetObat.getText();
        String supplier = txtSupplier.getText();
       try{
           Connection conn =(Connection)klinik.koneksi.koneksi.getDB();
           java.sql.Statement stm = conn.createStatement();
           String querry ="INSERT INTO obat_masuk VALUES ('"+kd+"','"+date+"','"+supplier+"','"+nm+"', '"+stok+"', '"+harga+"','"+ket+"')";
           stm.executeUpdate(querry);
           JOptionPane.showMessageDialog(this, "Berhasil Menambahkan obat baru", "Sukses", JOptionPane.INFORMATION_MESSAGE);
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Gagal Memasukkan Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
       }
    }
    
    private void ubah(){
        try{
            String kd = txtKdObat.getText();
            String nm = txtNmObat.getText();
            String stok = txtStokObat.getText();
            String harga = txtHargaObat.getText();
            String ket = txtKetObat.getText();
            
            Connection conn =(Connection)klinik.koneksi.koneksi.getDB();
            String querry ="UPDATE obat_masuk SET nama_obat='"+nm+"', stok_obat='"+stok+"', harga_obat='"+harga+"', keterangan='"+ket+"' WHERE kd_obat='"+kd+"'";
            com.mysql.jdbc.PreparedStatement prepare = (PreparedStatement)conn.prepareStatement(querry);
            prepare.execute();
            JOptionPane.showMessageDialog(this, "Berhasil mengubah data obat", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            btnTambah.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Gagal Mengubah Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    private void hapus(){
        int s_row = tableObat.getSelectedRow();
        if(s_row==-1){
            return;
        }
        String dataDelete = (String) tableObat.getValueAt(s_row,0);
        try{
            Connection conn = (Connection)klinik.koneksi.koneksi.getDB();
            String querry = "DELETE FROM obat_masuk WHERE kd_obat=?";
            java.sql.PreparedStatement stm = (PreparedStatement)conn.prepareStatement(querry);
            stm.setString(1,dataDelete);
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(this, "Berhasil menghapus data obat", "Sukses", JOptionPane.INFORMATION_MESSAGE);
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
        String getKode = tableObat.getValueAt(tableObat.getSelectedRow(), 0).toString();
        String getNama = tableObat.getValueAt(tableObat.getSelectedRow(), 1).toString();
        String getStok = tableObat.getValueAt(tableObat.getSelectedRow(), 2).toString();
        String getHarga = tableObat.getValueAt(tableObat.getSelectedRow(), 3).toString();
        String getKet = tableObat.getValueAt(tableObat.getSelectedRow(), 4).toString();

        txtKdObat.setText(getKode);
        txtNmObat.setText(getNama);
        txtStokObat.setText(getStok);
        txtHargaObat.setText(getHarga);
        txtKetObat.setText(getKet);
    }
    
public void cetak() {
       try{
            String NamaFile = "src/klinik/laporan/laporan_obat.jasper";     
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

        dialogObat = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDialogObat = new javax.swing.JTable();
        btnDialogKembaliObat = new javax.swing.JButton();
        btnDialogMasukanObat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKdObat = new javax.swing.JTextField();
        txtNmObat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtKetObat = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKosong = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtStokObat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHargaObat = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCariObat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableObat = new javax.swing.JTable();
        btnLaporan = new javax.swing.JButton();

        dialogObat.setSize(new java.awt.Dimension(450, 300));

        tableDialogObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tableDialogObat);

        btnDialogKembaliObat.setText("Kembali");
        btnDialogKembaliObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDialogKembaliObatActionPerformed(evt);
            }
        });

        btnDialogMasukanObat.setText("Masukan");
        btnDialogMasukanObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDialogMasukanObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogObatLayout = new javax.swing.GroupLayout(dialogObat.getContentPane());
        dialogObat.getContentPane().setLayout(dialogObatLayout);
        dialogObatLayout.setHorizontalGroup(
            dialogObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogObatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(dialogObatLayout.createSequentialGroup()
                        .addComponent(btnDialogKembaliObat, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDialogMasukanObat, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dialogObatLayout.setVerticalGroup(
            dialogObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogObatLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDialogMasukanObat, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnDialogKembaliObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Obat");
        setPreferredSize(new java.awt.Dimension(1000, 616));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Obat Masuk"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Kode Obat");

        jLabel2.setText("Nama Obat");

        btnTambah.setText("Tambah");
        btnTambah.setPreferredSize(new java.awt.Dimension(97, 48));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jLabel5.setText("Keterangan Obat");

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

        jLabel3.setText("Harga Obat (Rp)");

        jLabel4.setText("Stok Obat Masuk");

        jLabel6.setText("Nama Supplier");

        btnCariObat.setText("Update Obat");
        btnCariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKosong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(txtNmObat)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(txtKetObat)
                                .addComponent(jLabel3)
                                .addComponent(txtStokObat)
                                .addComponent(jLabel4)
                                .addComponent(txtHargaObat)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtKdObat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnCariObat)))
                            .addComponent(jLabel6)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKdObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNmObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStokObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHargaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKetObat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKosong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Obat"));

        tableObat.setModel(new javax.swing.table.DefaultTableModel(
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
        tableObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableObatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableObat);

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
                    .addComponent(btnLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if(txtKdObat.getText().equals("")|| txtSupplier.getText().equals("")|| txtNmObat.getText().equals("")|| txtStokObat.getText().equals("") || txtHargaObat.getText().equals("")||txtKetObat.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Data tidak boleh ada yang kosong","Pemberitahuan",JOptionPane.WARNING_MESSAGE);
        }else{
            simpan();
        }
        loadData();
        autoNumber();
        kosong();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if(txtKdObat.getText().equals("")|| txtNmObat.getText().equals("") || txtStokObat.getText().equals("") || txtHargaObat.getText().equals("") || txtKetObat.getText().equals("")){
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

    private void tableObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableObatMouseClicked
        klik();
    }//GEN-LAST:event_tableObatMouseClicked

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        cetak();
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnCariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariObatActionPerformed
        dialogObat.setLocationRelativeTo(null);
        daftarObat();
        dialogObat.setVisible(true);
    }//GEN-LAST:event_btnCariObatActionPerformed

    private void btnDialogKembaliObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDialogKembaliObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDialogKembaliObatActionPerformed

    private void btnDialogMasukanObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDialogMasukanObatActionPerformed
        String getKd = tableDialogObat.getValueAt(tableDialogObat.getSelectedRow(), 0).toString();
        String getNm = tableDialogObat.getValueAt(tableDialogObat.getSelectedRow(), 1).toString();
        String getHarga = tableDialogObat.getValueAt(tableDialogObat.getSelectedRow(), 2).toString();
        String getKeterangan = tableDialogObat.getValueAt(tableDialogObat.getSelectedRow(), 3).toString();


        txtKdObat.setText(getKd);
        txtNmObat.setText(getNm);
        txtHargaObat.setText(getHarga);
        txtKetObat.setText(getKeterangan);

        dialogObat.setVisible(false);
    }//GEN-LAST:event_btnDialogMasukanObatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariObat;
    private javax.swing.JButton btnDialogKembaliObat;
    private javax.swing.JButton btnDialogMasukanObat;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKosong;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JDialog dialogObat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tableDialogObat;
    private javax.swing.JTable tableObat;
    private javax.swing.JTextField txtHargaObat;
    private javax.swing.JTextField txtKdObat;
    private javax.swing.JTextField txtKetObat;
    private javax.swing.JTextField txtNmObat;
    private javax.swing.JTextField txtStokObat;
    private javax.swing.JTextField txtSupplier;
    // End of variables declaration//GEN-END:variables
}

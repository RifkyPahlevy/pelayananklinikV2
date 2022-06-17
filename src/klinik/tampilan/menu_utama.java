
package klinik.tampilan;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class menu_utama extends javax.swing.JFrame {


    int waktumulai = 0;
    int detik, menit, jam, hari, tanggal, bulan, tahun;
    public menu_utama() {
        initComponents();
       new Thread(){
           @Override
           public void run(){              
               GregorianCalendar date = new GregorianCalendar();
               String namabulan[] = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
               String[] hari_hari = new String[] { "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu" };
               hari = date.get(Calendar.DAY_OF_WEEK) - 1;
               tanggal = date.get(Calendar.DAY_OF_MONTH);
               bulan = date.get(Calendar.MONTH);
               tahun = date.get(Calendar.YEAR);
               txtDate.setText(hari_hari[hari] +", "+tanggal + " " + namabulan[bulan]+ " " + tahun);
               System.out.println("Tanggal sekarang: "+hari+" "+namabulan[bulan]+" "+tahun);
               while(waktumulai == 0){
                   Calendar kalender = new GregorianCalendar();
                   int jam = kalender.get(Calendar.HOUR);
                   int menit = kalender.get(Calendar.MINUTE);
                   int detik = kalender.get(Calendar.SECOND);
                   int AM_PM = kalender.get(Calendar.AM_PM);
                   String siang_malam ="";
                   if(AM_PM == 1){
                       siang_malam="PM"; 
                   }else{
                       siang_malam = "AM";   
                   }
                   String waktu = jam + ":" + menit + ":" + detik + " " + siang_malam;
                   txtJam.setText(waktu);               
                }  
           }
       }.start();
        setExtendedState(menu_utama.MAXIMIZED_BOTH);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelKode = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        btnObat1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnDokter = new javax.swing.JButton();
        btnKunjungan = new javax.swing.JButton();
        btnTindakan = new javax.swing.JButton();
        btnObat = new javax.swing.JButton();
        btnMedis = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtJam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        btnPasien1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktop.setBackground(new java.awt.Color(0, 153, 153));
        desktop.setAutoscrolls(true);
        desktop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selamat Datang");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("di Sistem Informasi ");
        jLabel1.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pelayanan Klinik Bidan HJ.Evy");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelKode.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        labelKode.setForeground(new java.awt.Color(255, 255, 255));

        labelNama.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        labelNama.setForeground(new java.awt.Color(255, 255, 255));

        labelStatus.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        labelStatus.setForeground(new java.awt.Color(255, 255, 255));

        btnObat1.setText("Obat Masuk");
        btnObat1.setMaximumSize(new java.awt.Dimension(129, 41));
        btnObat1.setMinimumSize(new java.awt.Dimension(129, 41));
        btnObat1.setPreferredSize(new java.awt.Dimension(129, 41));
        btnObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(desktopLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(labelKode, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(desktopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnObat1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addComponent(btnObat1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 330, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(8, 8, 8))
        );
        desktop.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(labelKode, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(labelNama, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(labelStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(btnObat1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnDokter.setText("Petugas");
        btnDokter.setMaximumSize(new java.awt.Dimension(129, 41));
        btnDokter.setMinimumSize(new java.awt.Dimension(129, 41));
        btnDokter.setPreferredSize(new java.awt.Dimension(129, 41));
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });

        btnKunjungan.setText("Kunjungan Pasien");
        btnKunjungan.setMaximumSize(new java.awt.Dimension(129, 41));
        btnKunjungan.setMinimumSize(new java.awt.Dimension(129, 41));
        btnKunjungan.setPreferredSize(new java.awt.Dimension(129, 41));
        btnKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKunjunganActionPerformed(evt);
            }
        });

        btnTindakan.setText("Tindakan");
        btnTindakan.setMaximumSize(new java.awt.Dimension(129, 41));
        btnTindakan.setMinimumSize(new java.awt.Dimension(129, 41));
        btnTindakan.setPreferredSize(new java.awt.Dimension(129, 41));
        btnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakanActionPerformed(evt);
            }
        });

        btnObat.setText("Obat");
        btnObat.setMaximumSize(new java.awt.Dimension(129, 41));
        btnObat.setMinimumSize(new java.awt.Dimension(129, 41));
        btnObat.setPreferredSize(new java.awt.Dimension(129, 41));
        btnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObatActionPerformed(evt);
            }
        });

        btnMedis.setText("Rekam Medis ");
        btnMedis.setMaximumSize(new java.awt.Dimension(129, 41));
        btnMedis.setMinimumSize(new java.awt.Dimension(129, 41));
        btnMedis.setPreferredSize(new java.awt.Dimension(129, 41));
        btnMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedisActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Menu");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Master");

        txtDate.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDate.setText("Senin, 24 Juli 2020");

        txtJam.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        txtJam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtJam.setText("12:02:32");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Transaksi");

        btnKeluar.setText("Keluar");
        btnKeluar.setMaximumSize(new java.awt.Dimension(129, 41));
        btnKeluar.setMinimumSize(new java.awt.Dimension(129, 41));
        btnKeluar.setPreferredSize(new java.awt.Dimension(129, 41));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnPasien1.setText("Pasien");
        btnPasien1.setMaximumSize(new java.awt.Dimension(129, 41));
        btnPasien1.setMinimumSize(new java.awt.Dimension(129, 41));
        btnPasien1.setPreferredSize(new java.awt.Dimension(129, 41));
        btnPasien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasien1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtJam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnKunjungan, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTindakan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDokter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnObat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJam)
                .addGap(40, 40, 40)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnObat, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKunjungan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        new klinik.login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        Petugas form_dokter = new Petugas(labelKode.getText());
        desktop.add(form_dokter);
        form_dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void btnKunjunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKunjunganActionPerformed
        kunjungan form_kunjungan = new kunjungan();
        desktop.add(form_kunjungan);
        form_kunjungan.setVisible(true);
    }//GEN-LAST:event_btnKunjunganActionPerformed

    private void btnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanActionPerformed
        tindakan form_tindakan = new tindakan();
        desktop.add(form_tindakan);
        form_tindakan.setVisible(true);
    }//GEN-LAST:event_btnTindakanActionPerformed

    private void btnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObatActionPerformed
        obat form_obat = new obat();
        desktop.add(form_obat);
        form_obat.setVisible(true);
    }//GEN-LAST:event_btnObatActionPerformed

    private void btnMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedisActionPerformed
        rekam form_rekam_medis = new rekam();
        desktop.add(form_rekam_medis);
        form_rekam_medis.setVisible(true);
    }//GEN-LAST:event_btnMedisActionPerformed

    private void btnPasien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasien1ActionPerformed
        // TODO add your handling code here:
        pasien form_pasien = new pasien();
        desktop.add(form_pasien);
        form_pasien.setVisible(true);
    }//GEN-LAST:event_btnPasien1ActionPerformed

    private void btnObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObat1ActionPerformed
        // TODO add your handling code here:
        ObatMasuk form_obat = new ObatMasuk();
        desktop.add(form_obat);
        form_obat.setVisible(true);
    }//GEN-LAST:event_btnObat1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDokter;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnKunjungan;
    private javax.swing.JButton btnMedis;
    private javax.swing.JButton btnObat;
    private javax.swing.JButton btnObat1;
    private javax.swing.JButton btnPasien1;
    private javax.swing.JButton btnTindakan;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel labelKode;
    public static javax.swing.JLabel labelNama;
    public static javax.swing.JLabel labelStatus;
    private javax.swing.JLabel txtDate;
    private javax.swing.JLabel txtJam;
    // End of variables declaration//GEN-END:variables
}

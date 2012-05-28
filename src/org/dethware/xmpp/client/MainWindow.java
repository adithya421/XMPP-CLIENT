/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dethware.xmpp.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import org.jivesoftware.smack.RosterEntry;

/**
 *
 * @author peterix
 */
public class MainWindow extends javax.swing.JFrame implements ClConnectionListener{

    ConversationFrame convFrame;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        ImageIcon II = new ImageIcon(getClass().getResource("im-jabber.png"));
        this.setIconImage(II.getImage());
        setTitle("XMPP Client");
        
        XMPPClient cl = XMPPClient.globalInstance();
        cl.conn_man.addClConListener(this);
        AppSettings settings = cl.settings;
        ContactList.setCellRenderer(new ContactListCellRenderer());
        ContactList.setModel(cl.contacts_model);
        ToolTipManager ttm = ToolTipManager.sharedInstance();
        ttm.registerComponent(ContactList);
        // this actually causes the whole chain of listeners to fire and sets
        // up the connection
        setStatusChooserValue(settings.getDesiredUserStatus());
        
        
        ContactList.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2 && !ContactList.isSelectionEmpty())
                {
                    Object selectedComponent = ContactList.getSelectionPath().getLastPathComponent();
                    if (selectedComponent instanceof Contact)
                    {
                        XMPPClient app = XMPPClient.globalInstance();
                        Contact entry = (Contact) selectedComponent;
                        Conversation conversation = entry.getConversation();
                        
                        showConversation(conversation, false);
                    }
                }
            }
        });
    }

    public void showConversation(Conversation conversation, boolean only_if_new)
    {
        if (convFrame == null)
        {
            convFrame = new ConversationFrame();
            convFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            convFrame.pack();

            convFrame.addWindowListener(
                new WindowAdapter()
                {
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        convFrame = null;
                    }
                }
                );
            convFrame.openConversation(conversation, false);
            convFrame.setVisible(true);
        }
        else
        {
            convFrame.openConversation(conversation, only_if_new);
            convFrame.setVisible(true);
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

        jToolBar2 = new javax.swing.JToolBar();
        openSettings = new javax.swing.JButton();
        openAdd = new javax.swing.JButton();
        expandButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ContactList = new org.dethware.xmpp.client.BetterJTree();
        statusChooser = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        connectionBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        openSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dethware/xmpp/client/configure.png"))); // NOI18N
        openSettings.setToolTipText("Open the settings window");
        openSettings.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        openSettings.setFocusable(false);
        openSettings.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        openSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSettingsActionPerformed(evt);
            }
        });
        jToolBar2.add(openSettings);
        openSettings.getAccessibleContext().setAccessibleName("Settings");

        openAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dethware/xmpp/client/list-add-user.png"))); // NOI18N
        openAdd.setToolTipText("Add a new contact");
        openAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        openAdd.setFocusable(false);
        openAdd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar2.add(openAdd);
        openAdd.getAccessibleContext().setAccessibleName("Add");

        expandButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dethware/xmpp/client/layer-visible-on.png"))); // NOI18N
        expandButton.setToolTipText("Expand all groups");
        expandButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        expandButton.setFocusable(false);
        expandButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        expandButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dethware/xmpp/client/layer-visible-off.png"))); // NOI18N
        expandButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        expandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(expandButton);

        ContactList.setRootVisible(false);
        jScrollPane2.setViewportView(ContactList);

        statusChooser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Free for chat", "Away", "Do not disturb", "Extended away", "Offline" }));
        statusChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusChooserActionPerformed(evt);
            }
        });

        jLabel1.setText("Status:");

        connectionBox.setEditable(false);
        connectionBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        connectionBox.setText("OFFLINE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusChooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(statusChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSettingsActionPerformed
        SettingsDialog s = new SettingsDialog(this);
        s.setVisible(true);
    }//GEN-LAST:event_openSettingsActionPerformed
//Available, Free for chat, Away, Do not disturb, Extended away, Offline
    private UserStatus getStatusChooserValue()
    {
        int index = statusChooser.getSelectedIndex();
        switch(index)
        {
            case 0:
                return UserStatus.Available;
            case 1:
                return UserStatus.FreeForChat;
            case 2:
                return UserStatus.Away;
            case 3:
                return UserStatus.DoNotDisturb;
            case 4:
                return UserStatus.ExtendedAway;
            default: // 5
                return UserStatus.Offline;
        }
    }
    private void setStatusChooserValue(UserStatus desiredUserStatus) {
        int index = 0;
        switch(desiredUserStatus)
        {
            case Available:
                index = 0;
                break;
            case FreeForChat:
                index = 1;
                break;
            case Away:
                index = 2;
                break;
            case DoNotDisturb:
                index = 3;
                break;
            case ExtendedAway:
                index = 4;
                break;
            case Offline:
                index = 5;
                break;    
        }
        statusChooser.setSelectedIndex(index);
    }
    private String getStatusChooserString()
    {
        return (String) statusChooser.getSelectedItem();
    }
    
    private void statusChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusChooserActionPerformed
        AppSettings as = AppSettings.globalInstance();
        UserStatus us = getStatusChooserValue();
        as.setDesiredUserStatus(us);
        as.setStatusString(getStatusChooserString());
        as.NotifyListeners();
    }//GEN-LAST:event_statusChooserActionPerformed

    private void expandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandButtonActionPerformed
        for (int i = 0; i < ContactList.getRowCount(); i++)
        {
            ContactList.expandRow(i);
        }
    }//GEN-LAST:event_expandButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.dethware.xmpp.client.BetterJTree ContactList;
    private javax.swing.JTextField connectionBox;
    private javax.swing.JButton expandButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton openAdd;
    private javax.swing.JButton openSettings;
    private javax.swing.JComboBox statusChooser;
    // End of variables declaration//GEN-END:variables

    @Override
    public void connectionChanged(UserStatus status, String statusString)
    {
        setTitle("XMPP Client (" + status.toString() + ")");
        
        if(status == UserStatus.Offline)
            connectionBox.setText("OFFLINE");
        else
            connectionBox.setText("ONLINE");
    }
}

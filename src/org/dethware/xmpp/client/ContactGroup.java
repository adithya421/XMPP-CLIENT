/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dethware.xmpp.client;

import java.util.ArrayList;

/**
 *
 * @author peterix
 */
public class ContactGroup {

    ContactManager cm;
    String name;
    public ContactGroup(ContactManager parent, String name) {
        cm = parent;
        this.name = name;
    }
    
    public ArrayList <Contact> contacts = new ArrayList<>();
    public int getNum()
    {
        return contacts.size();
    }
    public boolean contains( Contact c )
    {
        return contacts.contains(c);
    }
    public boolean contains( String JID )
    {
        Contact  c = cm.getContact(JID);
        return contacts.contains(c);
    }
    public Contact getContact( int index )
    {
        return contacts.get(index);
    }
    public boolean add( Contact c )
    {
        if(contacts.contains(c))
        {
            return false;
        }
        contacts.add(c);
        return true;
    }
    public String getName()
    {
        return name;
    }

    int getIndexOf(Object child) {
        return contacts.indexOf(child);
    }
}

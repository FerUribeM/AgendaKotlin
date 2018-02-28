package com.ferbajoo.agendakotlin.databases

import android.content.ContentValues
import android.content.Context
import com.ferbajoo.agendakotlin.models.Contacts

/**
 * Created by
 *          feuribe on 23/02/2018.
 */
class Functions {

    fun saveContact(name: String, phone: String, age: String, gener: String, context: Context): Long {
        val database = Database(context).writableDatabase
        val values = ContentValues()
        values.put(Database.TableContacts().NAME, name)
        values.put(Database.TableContacts().PHONE, phone)
        values.put(Database.TableContacts().AGE, age)
        values.put(Database.TableContacts().GENER, gener)
        val id = database.insert(Database.TableContacts().TABLE, null, values)
        database.close()
        return id
    }

    fun updateContact(id_contact: Long?,name: String, phone: String, age: String, gener: String, context: Context): Int {
        val database = Database(context).writableDatabase
        val values = ContentValues()
        values.put(Database.TableContacts().NAME, name)
        values.put(Database.TableContacts().PHONE, phone)
        values.put(Database.TableContacts().AGE, age)
        values.put(Database.TableContacts().GENER, gener)
        val count = database.update(Database.TableContacts().TABLE,values,Database.TableContacts().ID+"=?", arrayOf(id_contact.toString()))
        database.close()
        return count
    }

    fun getAllContacts(context: Context): ArrayList<Contacts> {
        val contacts: ArrayList<Contacts> = ArrayList()
        val database = Database(context).readableDatabase

        val query = String.format("SELECT * FROM %s", Database.TableContacts().TABLE)

        val cursor = database.rawQuery(query, null)

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val contact = Contacts()
                contact.id = cursor.getLong(cursor.getColumnIndex(Database.TableContacts().ID))
                contact.name = cursor.getString(cursor.getColumnIndex(Database.TableContacts().NAME))
                contact.phone = cursor.getString(cursor.getColumnIndex(Database.TableContacts().PHONE))
                contact.age = cursor.getInt(cursor.getColumnIndex(Database.TableContacts().AGE))
                contact.gender = cursor.getString(cursor.getColumnIndex(Database.TableContacts().GENER))
                contacts.add(contact)
            } while (cursor.moveToNext())
            cursor.close()
        }
        database.close()

        return contacts
    }

    fun getContact(context: Context, id_contact: Long): Contacts {
        val database = Database(context).readableDatabase
        val contact = Contacts()

        val query = String.format("SELECT * FROM %s WHERE %s = '%s'", Database.TableContacts().TABLE, Database.TableContacts().ID, id_contact)

        val cursor = database.rawQuery(query, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                contact.id = cursor.getLong(cursor.getColumnIndex(Database.TableContacts().ID))
                contact.name = cursor.getString(cursor.getColumnIndex(Database.TableContacts().NAME))
                contact.phone = cursor.getString(cursor.getColumnIndex(Database.TableContacts().PHONE))
                contact.age = cursor.getInt(cursor.getColumnIndex(Database.TableContacts().AGE))
                contact.gender = cursor.getString(cursor.getColumnIndex(Database.TableContacts().GENER))
            } while (cursor.moveToNext())
            cursor.close()
        }
        database.close()
        return contact
    }

    fun deleteContact(context: Context, id: Long?) : Int{
        val database = Database(context).writableDatabase
        val rows = database.delete(Database.TableContacts().TABLE,Database.TableContacts().ID+"=?", arrayOf(id.toString()))
        database.close()
        return rows
    }

}
package com.eyyuperdogan.learnsqllite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //musicians adında tablo oluşturduk
            val myDatabase=this.openOrCreateDatabase("Musicians",Context.MODE_PRIVATE,null)


            //tablomuzda name ve age kolonlarını oluşturduk
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(ıd INTEGER PRIMARY KEY,name VARCHAR,age INT)")
           //james ve 50 değerlerini girdik
          /*  myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('james',50)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('anne maria',28)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('eyyüp',23)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('alex',44)")
*/
            //seçilen tablo
             val cursor=myDatabase.rawQuery("SELECT*FROM musicians",null)
            // val cursor=myDatabase.rawQuery("SELECT*FROM musicians WHERE  ıd=2",null)
            //val cursor=myDatabase.rawQuery("SELECT*FROM musicians WHERE name LIKE '%x' ",null)


            //güncelleme
            myDatabase.execSQL("UPDATE musicians SET name='maria' WHERE name ='anne maria' ")

            //silme
            myDatabase.execSQL("DELETE FROM musicians WHERE name ='james'")

            //index leri alıyoruz,
            val nameıx=cursor.getColumnIndex("name")
            val ageıx=cursor.getColumnIndex("age")
            val ıdIx=cursor.getColumnIndex("ıd")


            while (cursor.moveToNext()){
                println("Name :"+cursor.getString(nameıx))
                println("Age :"+cursor.getInt(ageıx))
                println("ıd :"+cursor.getInt(ıdIx))

            }
     cursor.close()
        }
        catch (e:java.lang.Exception){
        e.printStackTrace()
        }
    }
}
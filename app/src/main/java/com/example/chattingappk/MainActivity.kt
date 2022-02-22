package com.example.chattingappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var name_recycler_view: RecyclerView
    private lateinit var usersList: ArrayList<Users>
    private lateinit var adapter: UsersAdapter
    private lateinit var auth:FirebaseAuth
    private  lateinit var  dbref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()
        dbref=FirebaseDatabase.getInstance().getReference()

        usersList= ArrayList()
        adapter = UsersAdapter(this, usersList)

        name_recycler_view=findViewById(R.id.name_recycler_view_id)
        name_recycler_view.layoutManager=LinearLayoutManager(this)
        name_recycler_view.adapter=adapter

        dbref.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

               usersList.clear()
                for (post_snapshot in snapshot.children)
                {
                    val current_users=post_snapshot.getValue(Users::class.java)
                    if(auth.currentUser?.uid!= current_users?.uid)
                    {
                        usersList.add(current_users!!)
                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.logout)
        {
                auth.signOut()
            val intent =Intent(this@MainActivity, LoginActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }

        return true
    }

}
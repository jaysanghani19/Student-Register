package com.example.studentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregister.R.id.buttonSave
import com.example.studentregister.database.MainActivityViewModelProvieder
import com.example.studentregister.database.Student
import com.example.studentregister.database.StudentDataBase

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave : Button
    private lateinit var buttonClear : Button


    private lateinit var viewModel: MainActivityViewModel


    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RecycleViewAdapter

    private lateinit var selectedStudent: Student
    private var isStudentSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonClear = findViewById(R.id.buttonClear)
        buttonSave = findViewById(R.id.buttonSave)
        recyclerview = findViewById(R.id.recyclerViewStudent)

        val dao = StudentDataBase.getInstance(application).studentDao()
        val viewModelFactory = MainActivityViewModelProvieder(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        buttonSave.setOnClickListener {
            if (isStudentSelected == false){
            saveData()
            clearEditText()}
            else {
                updateData()
            }
        }

        buttonClear.setOnClickListener {
            if (isStudentSelected == false){
                clearEditText()
            }
            else{
                deleteData()
            }
        }

        setRecycleView()
    }

    private fun saveData(){
        viewModel.insertData(
            Student(0,
            editTextName.text.toString()
            ,editTextEmail.text.toString()
            )
        )
    }

    private fun updateData(){
        viewModel.updateData(
            Student(
                selectedStudent.id,
                editTextName.text.toString(),
                editTextEmail.text.toString()
            )
        )
//        selectedStudent = null
        buttonSave.text = "Save"
        buttonClear.text = "Clear"
        isStudentSelected = false
        editTextName.setText("")
        editTextEmail.setText("")

    }

    private fun deleteData(){
        viewModel.deleteData(
            selectedStudent
        )
//        selectedStudent = null
        buttonSave.text = "Save"
        buttonClear.text = "Clear"
        isStudentSelected = false
        editTextName.setText("")
        editTextEmail.setText("")

    }

    private fun setRecycleView()
    {
        recyclerview.layoutManager=LinearLayoutManager(this)
        adapter = RecycleViewAdapter{
            selected: Student -> listItemSelected(selected)
        }
        recyclerview.adapter = adapter
        setDataOnRecyclerView()
    }

    private fun setDataOnRecyclerView()
    {
        viewModel.students.observe(this , {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

    }


    private fun clearEditText()
    {
        editTextEmail.setText("")
        editTextName.setText("")
    }
    
    fun listItemSelected(student: Student)
    {
//        Toast.makeText(this, "Name of Student is ${student.name}", Toast.LENGTH_SHORT).show()

        selectedStudent = student
        buttonSave.text = "Update"
        buttonClear.text = "Delete"
        isStudentSelected = true
        editTextName.setText(student.name)
        editTextEmail.setText(student.email)
    }

}
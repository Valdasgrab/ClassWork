package lt.vgrabauskas.classwork

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class Employees : AppCompatActivity() {


    lateinit var adapter: CustomAdapter
    lateinit var employeeListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        employeeListView = findViewById(R.id.employeeListView)
        val employees = mutableListOf<Employee>()

        generateListOfEmployees(employees)

        getAdapter(employees)

        employeeListView.adapter = adapter

    }

    private fun getAdapter(employees: MutableList<Employee>) {
        adapter = CustomAdapter(this)
        adapter.add(employees)
    }

    private fun generateListOfEmployees(employees: MutableList<Employee>) {
        for (employee in 1..10) {
            employees.add(
                Employee(
                    employee,
                    "text01%04x".format(employee),
                    "text02%06x".format(employee),
                    "text02%06x".format(employee)
                )
            )
        }
    }
}
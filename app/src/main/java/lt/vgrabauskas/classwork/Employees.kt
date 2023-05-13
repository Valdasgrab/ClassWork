package lt.vgrabauskas.classwork

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class Employees : AppCompatActivity() {

    lateinit var openSecondActivityButton: Button
    lateinit var adapter: CustomAdapter
    lateinit var employeeListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openSecondActivityButton = findViewById(R.id.openSecondActivityButton)
        employeeListView = findViewById(R.id.employeeListView)
        val employees = mutableListOf<Employee>()

        generateListOfEmployees(employees)

        getAdapter(employees)

        employeeListView.adapter = adapter




        setClickOpenEmployeeDetails()
        setClickOpenSecondActivity()
        updateAdapter(employees)
    }

    private fun getAdapter(employees: MutableList<Employee>) {
        adapter = CustomAdapter(this)
        adapter.add(employees)
    }

    private fun generateListOfEmployees(employees: MutableList<Employee>) {
        for (employee in 1..5) {
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
    private fun setClickOpenEmployeeDetails() {
        employeeListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Employee = adapterView.getItemAtPosition(position) as Employee

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, item.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, item.firstName)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, item.lastName)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT03, item.position)
            startActivity(itemIntent)
        }
    }
    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.vcs.androidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.vcs.androidtopics_item_text02"
        const val MAIN_ACTIVITY_ITEM_TEXT03 = "package lt.vcs.androidtopics_item_text02"
    }
    private fun setClickOpenSecondActivity() {
        openSecondActivityButton.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
            startActivityForResult.launch(Intent(this, SecondActivity::class.java))
        }
    }
    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val employee = Employee(
                        id = result.data
                            ?.getIntExtra(SecondActivity.SECOND_ACTIVITY_ITEM_ID, 0) ?: 0,
                        firstName = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        lastName = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT02) ?: "",
                        position = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT03) ?: ""


                    )
                    adapter.add(employee)
                }
            }
        }

    private fun updateAdapter(items: MutableList<Employee>) {
        adapter.add(items)
//        adapter.add(Employee(101, "text01", "text02", ""))
//        adapter.add(
//            Employee(102, "text01", "text02", "text02"),
//            Employee(103, "text01", "text02", "text02"),
//            Employee(104, "text01", "text02", "text02"),
//            Employee(105, "text01", "text02", "text02"),
//        )
    }
}
package com.nzh.travel

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.nzh.travel.databinding.ActivityRegisterBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class RegisterActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityRegisterBinding
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var email: String
    lateinit var dob: String
    lateinit var nationality: String
    lateinit var residence: String
    lateinit var phoneNo: String
    var gender: String="Female"

    var day = 0
    var month: Int = 0
    var year: Int = 0

    private var isValidation: Boolean = false
    val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewAcc.setOnClickListener {
            firstName = binding.edtFirstName.text.toString()
            lastName = binding.edtLastName.text.toString()
            email = binding.edtEmailAddress.text.toString()
            dob = binding.edtDOB.text.toString()
            nationality = binding.edtNationality.text.toString()
            residence = binding.edtCountryOfResidence.text.toString()
            phoneNo = binding.edtMobileNo.text.toString()

            isValidation =
                checkValidationData(firstName, lastName, email, dob, nationality, residence)

            if (isValidation)
                finish()
        }
        binding.edtDOB.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@RegisterActivity, this@RegisterActivity, year, month, day)
            datePickerDialog.show()
        }
        binding.btnFemale.setOnClickListener {
            gender="Female"
            //binding.btnMale.setBackgroundColor(Color.WHITE)
            binding.btnFemale.setTextColor(Color.WHITE)
            binding.btnMale.setTextColor(Color.BLACK)
        }
        binding.btnMale.setOnClickListener {
            gender="Male"
            //binding.btnFemale.setBackgroundColor(Color.WHITE)
            binding.btnMale.setTextColor(Color.WHITE)
            binding.btnFemale.setTextColor(Color.BLACK)
        }
    }

    private fun checkValidationData(
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        nationality: String,
        residence: String
    ): Boolean {
        if (firstName.isNullOrEmpty()) {
            binding.edtFirstName.setError("First Name Required")
            return false
        } else if (lastName.isNullOrEmpty()) {
            binding.edtLastName.setError("Last Name Required")
            return false
        }  else if (email.isNullOrEmpty()) {
            binding.edtEmailAddress.setError("Email Address Required")
            return false
        } else if (dob.isNullOrEmpty() || dob=="DD/MM/YYYY") {
            binding.edtDOB.setError("Date of Birth Required")
            return false
        } else if (nationality.isNullOrEmpty()) {
            binding.edtNationality.setError("Nationality Required")
            return false
        } else if (residence.isNullOrEmpty()) {
            binding.edtCountryOfResidence.setError("Country of Residence Required")
            return false
        }

        return true
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        binding.edtDOB.setText("$day/$month/$year")
    }

}
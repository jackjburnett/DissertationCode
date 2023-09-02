package com.jb.sensingapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jb.sensingapplication.databinding.ActivityMoodSelfReportBinding

class MoodSelfReport : AppCompatActivity() {
    private lateinit var binding: ActivityMoodSelfReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binds the xml elements, initialises binding, sets the view to the binding's root (xml view)
        binding = ActivityMoodSelfReportBinding.inflate(layoutInflater)
        val view = binding.root

        // sets the content view to the binding's root
        setContentView(view)

        // Creates a pop-up ready to show in case the form is incomplete
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Form error")
        alertDialogBuilder.setMessage("Please complete all questions")

        // Submit button listener
        binding.buttonSubmit.setOnClickListener {
            // If checkForm is true, the form is complete, if it is false it is incomplete
            if(checkForm()){
                // Initialises values of the current selections, then passes it to the MainActivity with the name 'result' as MainActivity is started.
                val intent = Intent(this, MainActivity::class.java)
                var result = checkSelections()
                result += java.sql.Timestamp(System.currentTimeMillis())
                intent.putExtra("result", result)
                startActivity(intent)
            }else{
                alertDialogBuilder.show()
            }
        }
    }

    // checks every radio group's checked id, -1 means no radio button in the group is checked.
    // if a group has a -1 checked id, then the function returns false (not(checkedid==-1))
    private fun checkForm(): Boolean{
        return !((binding.radioGroupQ1.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ2.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ3.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ4.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ5.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ6.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ7.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ8.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ9.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ10.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ11.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ12.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ13.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ14.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ15.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ16.getCheckedRadioButtonId()==-1)
                or (binding.radioGroupQ18.getCheckedRadioButtonId()==-1))
    }

    // Gets the id of every radio button, adding it to the answers var, as well as the check box results.
    // returns answers as a string
    private fun checkSelections(): String{
        var answers = ""
        answers+="q1: "+binding.radioGroupQ1.checkedRadioButtonId+"\n"
        answers+="q2: "+binding.radioGroupQ2.checkedRadioButtonId+"\n"
        answers+="q3: "+binding.radioGroupQ3.checkedRadioButtonId+"\n"
        answers+="q4: "+binding.radioGroupQ4.checkedRadioButtonId+"\n"
        answers+="q5: "+binding.radioGroupQ5.checkedRadioButtonId+"\n"
        answers+="q6: "+binding.radioGroupQ6.checkedRadioButtonId+"\n"
        answers+="q7: "+binding.radioGroupQ7.checkedRadioButtonId+"\n"
        answers+="q8: "+binding.radioGroupQ8.checkedRadioButtonId+"\n"
        answers+="q9: "+binding.radioGroupQ9.checkedRadioButtonId+"\n"
        answers+="q10: "+binding.radioGroupQ10.checkedRadioButtonId+"\n"
        answers+="q11: "+binding.radioGroupQ11.checkedRadioButtonId+"\n"
        answers+="q12: "+binding.radioGroupQ12.checkedRadioButtonId+"\n"
        answers+="q13: "+binding.radioGroupQ13.checkedRadioButtonId+"\n"
        answers+="q14: "+binding.radioGroupQ14.checkedRadioButtonId+"\n"
        answers+="q15: "+binding.radioGroupQ15.checkedRadioButtonId+"\n"
        answers+="q16: "+binding.radioGroupQ16.checkedRadioButtonId+"\n"
        answers+="q17: "+checkboxCheck()+"\n"
        answers+="q18: "+binding.radioGroupQ18.checkedRadioButtonId+"\n"
        return answers
    }

    // for every checkbox, it checks if it is checked and returns the id+
    // if 179 is checked (None) it only returns that checkbox id
    private fun checkboxCheck(): String {
        if(binding.checkQ179.isChecked()){
            return binding.checkQ179.id.toString()
        }else{
            var checks = ""
            if(binding.checkQ170.isChecked()){
                checks+=binding.checkQ170.id.toString()+", "
            }
            if(binding.checkQ171.isChecked()){
                checks+=binding.checkQ171.id.toString()+", "
            }
            if(binding.checkQ172.isChecked()){
                checks+=binding.checkQ172.id.toString()+", "
            }
            if(binding.checkQ173.isChecked()){
                checks+=binding.checkQ173.id.toString()+", "
            }
            if(binding.checkQ174.isChecked()){
                checks+=binding.checkQ174.id.toString()+", "
            }
            if(binding.checkQ175.isChecked()){
                checks+=binding.checkQ175.id.toString()+", "
            }
            if(binding.checkQ176.isChecked()){
                checks+=binding.checkQ176.id.toString()+", "
            }
            if(binding.checkQ177.isChecked()){
                checks+=binding.checkQ177.id.toString()+", "
            }
            if(binding.checkQ178.isChecked()){
                checks+=binding.checkQ178.id.toString()+", "
            }
            return checks
        }
    }
}
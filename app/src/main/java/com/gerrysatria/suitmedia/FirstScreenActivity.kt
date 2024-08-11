package com.gerrysatria.suitmedia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gerrysatria.core.name_data
import com.gerrysatria.suitmedia.databinding.ActivityFirstScreenBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            btnCheckPalidrome.setOnClickListener {
                val text = palidromeInput.text.toString().replace("\\s".toRegex(), "")
                if (text.isEmpty() || text.isBlank()){
                    showDialogMessage(this@FirstScreenActivity, "Please fill the palindrome field")
                } else {
                    if (isPalindrome(text)){
                        showDialogMessage( this@FirstScreenActivity, "is Palindrome")
                    } else {
                        showDialogMessage( this@FirstScreenActivity, "is not Palindrome")
                    }
                }
            }
            btnNext.setOnClickListener{
                val name = nameInput.text.toString()
                if (name.isNotEmpty() || name.isNotBlank()){
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    intent.putExtra(name_data, name)
                    startActivity(intent)
                } else {
                    showDialogMessage(this@FirstScreenActivity, "Name cannot be empty")
                }
            }
        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        return sentence.equals(sentence.reversed(), ignoreCase = true)
    }

    private fun showDialogMessage(context: Context, message: String){
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.oke)){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
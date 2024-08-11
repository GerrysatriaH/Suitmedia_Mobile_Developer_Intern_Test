package com.gerrysatria.suitmedia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.gerrysatria.core.choose_name_data
import com.gerrysatria.core.name_data
import com.gerrysatria.suitmedia.databinding.ActivitySecondScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "Second Screen"
            setDisplayHomeAsUpEnabled(true)
        }

        val name = intent.getStringExtra(name_data)
        binding.txtName.text = name

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val value = it.data?.getStringExtra(choose_name_data)
                binding.txtSelectedUser.text = value
            }
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            getResult.launch(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
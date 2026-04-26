package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.databinding.FragmentLoginBinding
import com.example.habittracker.viewmodel.LoginViewModel

class
LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan layout fragment_login.xml
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val usernameInput = view.findViewById<EditText>(R.id.txtInputUsn)
        //val passwordInput = view.findViewById<EditText>(R.id.txtInputPwd)
        //val loginButton = view.findViewById<Button>(R.id.btnLogin)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            val username = binding.txtInputUsername.text.toString()
            val password = binding.txtInputPassword.text.toString()

            viewModel.checkLogin(username, password)
            // Logi
        }
        viewModel.statusLoginLD.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // Jika sukses, baru navigasi
                val action = LoginFragmentDirections.actionLoginFragment4ToDashboardFragment()
                findNavController().navigate(action)
            } else {
                // Jika gagal, tampilkan toast
                Toast.makeText(requireContext(), "User atau Pass Salah ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
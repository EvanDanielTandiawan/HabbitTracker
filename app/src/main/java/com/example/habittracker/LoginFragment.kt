import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.habittracker.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan layout fragment_login.xml
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameInput = view.findViewById<EditText>(R.id.txtInputUsn)
        val passwordInput = view.findViewById<EditText>(R.id.txtInputPwd)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Logic
            if (username == "student" && password == "123") {
                // Aksi jika berhasil (misal: pindah ke Activity/Fragment lain)
                Toast.makeText(context, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
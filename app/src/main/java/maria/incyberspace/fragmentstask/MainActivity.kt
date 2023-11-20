package maria.incyberspace.fragmentstask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import maria.incyberspace.fragmentstask.abcd.FragmentA
import maria.incyberspace.fragmentstask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startFragmentA()
    }

    private fun startFragmentA() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FragmentA>(R.id.fragment_container_view)
        }
    }

    fun startFragment(f: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, f)
            addToBackStack(f.javaClass.simpleName)
        }
    }

    fun goBackToFragment(name: String, popBack: Int = 1) {
        supportFragmentManager.popBackStack(name, popBack)
    }

}
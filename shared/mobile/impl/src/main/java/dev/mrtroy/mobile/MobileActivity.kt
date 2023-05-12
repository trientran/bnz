package dev.mrtroy.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.mrtroy.mobile.impl.databinding.MobileActivityBinding

/**
 * An [AppCompatActivity] that serves as the entry point for the mobile version of the application.
 * This class is annotated with [AndroidEntryPoint] to enable dependency injection.
 * It inflates the layout using [MobileActivityBinding] and sets it as the content view.
 */
@AndroidEntryPoint
class MobileActivity : AppCompatActivity() {

    private var _binding: MobileActivityBinding? = null
    private val binding: MobileActivityBinding get() = _binding!!

    /**
     * Called when the activity is starting.
     * It inflates the layout using [MobileActivityBinding] and sets it as the content view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MobileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

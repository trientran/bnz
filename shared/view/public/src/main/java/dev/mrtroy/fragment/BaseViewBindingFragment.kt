package dev.mrtroy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base fragment class that inflates a view binding layout and provides access to its associated view hierarchy.
 *
 * @param B the type of view binding associated with the layout of the fragment
 * @property inflate a function that inflates the layout of the fragment into a view binding instance
 */
abstract class BaseViewBindingFragment<B : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> B
) : Fragment() {

    /**
     * The view binding instance associated with the layout of the fragment.
     */
    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    /**
     * Inflates the layout of the fragment into a view hierarchy and initializes the view binding instance.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Clears the view binding instance to avoid leaking memory.
     */
    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

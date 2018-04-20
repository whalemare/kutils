package ru.whalemare.kutils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
fun EditText.addBackspaceClickListener(function: () -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (count == 0) {
                function.invoke()
            }
        }

    })
}
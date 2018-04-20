package ru.whalemare.kutils
import android.content.res.Resources
import android.os.Build
import android.telephony.PhoneNumberUtils

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */

object PhoneNumberExt {

    fun formatNumberCompat(rawPhone: String?, countryIso: String = ""): String {
        if (rawPhone == null) return ""

        var countryName = countryIso
        if (countryName.isBlank()) {
            countryName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Resources.getSystem().configuration.locales[0].country
            } else {
                Resources.getSystem().configuration.locale.country
            }
        }

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            PhoneNumberUtils.formatNumber(rawPhone)
        } else {
            PhoneNumberUtils.formatNumber(rawPhone, countryName)
        }
    }

}

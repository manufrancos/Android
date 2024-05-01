package com.manui.myapplication.bindings

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.checkbox.MaterialCheckBox
import com.manui.myapplication.constants.Constants.DATE_SHOW
import com.manui.myapplication.constants.Constants.HOUR_SHOW
import com.manui.myapplication.rest.networkadapter.NetworkResponse
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isLoadingGone")
fun bindLoadingIsGone(view: View, networkResponse: NetworkResponse<Any, Any>?) {
    view.visibility = if (networkResponse is NetworkResponse.Loading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("hideEmptyList")
fun bindHideEmptyList(view: View, list: List<Any>?) {
    view.visibility = if (list.isNullOrEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isError")
fun bindIsError(view: View, response: NetworkResponse<Any, Any>) {
    view.visibility = if (response is NetworkResponse.NetworkError ||
        response is NetworkResponse.UnknownError
    ) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("htmText")
fun MaterialCheckBox.htmText(desc: String?) {
    if (desc == null) return
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(desc)
    }
}

@BindingAdapter("htmText")
fun TextView.htmText(desc: String?) {
    if (desc == null) return
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(desc)
    }
}

@BindingAdapter("textStrike")
fun TextView.textStrike(desc: String) {
    text = desc
    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("dateWithFormatText")
fun TextView.dateWithFormatText(date: Date?) {
    if (date == null) return

    val simpleDateFormat = SimpleDateFormat(DATE_SHOW)

    text = text.toString() + simpleDateFormat.format(date)
}

@BindingAdapter("dateWithFormat")
fun TextView.dateWithFormat(date: Date?) {
    if (date == null) return

    val simpleDateFormat = SimpleDateFormat(DATE_SHOW)

    text = simpleDateFormat.format(date)
}

@BindingAdapter("android:text")
fun EditText.setText(value: Float?) {
    if (value == null || (text.isNotEmpty() && value == java.lang.Float.valueOf(text.toString()))) return
    setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
fun EditText.getTextString(): Float? {
    return if (text.isEmpty()) null else java.lang.Float.valueOf(text.toString())
}

@BindingAdapter("android:text")
fun EditText.setTextInt(value: Int?) {
    if (value == null || (text.isNotEmpty() && value == Integer.valueOf(text.toString()))) return
    setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
fun EditText.getTextInt(): Int? {
    return if (text.isEmpty()) null else Integer.valueOf(text.toString())
}

@BindingAdapter("error")
fun EditText.error(errorString: String?) {
    error = errorString
}

@BindingAdapter("timeWithFormat")
fun TextView.timeWithFormat(date: Date?) {
    if (date == null) return

    val simpleDateFormat = SimpleDateFormat(HOUR_SHOW)

    text = simpleDateFormat.format(date)
}

@BindingAdapter("textCurrency")
fun TextView.textCurrency(coin: Float?) {
    val formatString = "%.2f €"

    text = formatString.format(coin ?: 0f)
}

@BindingAdapter("timeDiffReadable")
fun TextView.timeDiffReadable(dateInit: String?) {
    if (dateInit == null) {
        text = ""
        return
    }

    val currentTime = Date().time
    val dtInit = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateInit)

    if (dtInit == null) {
        text = ""
        return
    }

    val time = dtInit.time
    val diff = currentTime - time

    val days = TimeUnit.MILLISECONDS.toDays(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)

    val textFormatted = when {
        days > 1 -> "$days días"
        days == 1L -> "Ayer"
        hours > 0 -> "$hours h"
        minutes > 0 -> "$minutes min"
        else -> "Ahora"
    }

    text = textFormatted
}

@BindingAdapter("isBold")
fun TextView.isBold(isBold: Boolean) {
    val temp = if (isBold) Typeface.BOLD else Typeface.NORMAL

    setTypeface(null, temp)
}

@BindingAdapter("bgColorFromId")
fun View.viewBackGroundColorFromId(id: Int?) {
    if (id == null) {
        throw RuntimeException("Falta el drawable placeholder")
    } else {
        setBackgroundColor(id)
    }
}

@BindingAdapter("bgColorFromString")
fun View.viewBackGroundColorFromString(color: String) {
    if (color.isNotEmpty()) {
        val myColor: Int = Color.parseColor(color)
        setBackgroundColor(myColor)
    }
}

@BindingAdapter("imageFromId")
fun ImageView.bindImageFromId(id: Int?) {
    if (id == null) {
        throw RuntimeException("Falta el drawable placeholder")
    } else {
        setImageResource(id)
    }
}

@BindingAdapter("imageFromUri")
fun ImageView.bindImageFromUri(uri: Uri?) {
    if (uri == null) return

    setImageURI(uri)
}

@BindingAdapter("imageFromUri")
fun ImageView.bindImageFromUri(uri: String?) {
    if (uri == null) {
        throw RuntimeException("Falta el drawable placeholder")
    } else {
        setImageURI(Uri.parse(uri))
    }
}

@BindingAdapter("imageFromUrl")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("imageFromUrlOrNull")
fun ImageView.imageFromUrlOrNull(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        this.visibility = View.GONE
        return
    }

    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("imageRoundFromUrl")
fun ImageView.bindImageRoundFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("imageUrl", "placeHolder")
fun ImageView.bindImageFromUrlWithPlaceHolder(imageUrl: String?, placeholder: Drawable?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else if (placeholder != null) {
        setImageDrawable(placeholder)
    }
}

@BindingAdapter("viewExpandedAnimation")
fun viewExpandedAnimation(view: View, expanded: Boolean) {
    val height = view.height
    val measuredHeight = view.measuredHeight

    Log.d("BindingAdapters", "EspandedAnimation $height - $measuredHeight - $expanded")
}

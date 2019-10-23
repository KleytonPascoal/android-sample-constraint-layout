package com.pascoal.kleyton.android.sample.constraintlayout

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    private lateinit var contentLayoutView: ConstraintLayout
    private lateinit var primaryTextView: TextView
    private lateinit var secondaryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentLayoutView = findViewById(R.id.content_layout_view)
        primaryTextView = findViewById(R.id.primary_text_view)
        secondaryTextView = findViewById(R.id.secondary_text_view)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun showSecondaryTextViewAnimation() {

        TransitionManager.beginDelayedTransition(contentLayoutView)

        val set = ConstraintSet()
        set.clone(this.contentLayoutView)

        set.clear(primaryTextView.id, ConstraintSet.BOTTOM)
        set.connect(primaryTextView.id, ConstraintSet.BOTTOM, secondaryTextView.id, ConstraintSet.TOP)

        set.setVisibility(secondaryTextView.id, ConstraintSet.VISIBLE)

        set.applyTo(this.contentLayoutView)
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun hideSecondaryTextViewAnimation() {

        TransitionManager.beginDelayedTransition(contentLayoutView)

        val set = ConstraintSet()
        set.clone(this.contentLayoutView)

        set.clear(primaryTextView.id, ConstraintSet.BOTTOM)
        set.connect(primaryTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        set.setVisibility(secondaryTextView.id, ConstraintSet.GONE)

        set.applyTo(this.contentLayoutView)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun onCLickShowOrHideSecondaryView(view: View) {
        when (secondaryTextView.visibility) {
            View.VISIBLE -> hideSecondaryTextViewAnimation()
            else -> showSecondaryTextViewAnimation()
        }
    }
}

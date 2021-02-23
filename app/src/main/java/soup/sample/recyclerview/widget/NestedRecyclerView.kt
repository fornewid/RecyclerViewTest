package soup.sample.recyclerview.widget

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class NestedRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val itemTouchListener = object : RecyclerView.OnItemTouchListener {

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            if (e.action == MotionEvent.ACTION_DOWN) {
                rv.stopScroll()
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addOnItemTouchListener(itemTouchListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeOnItemTouchListener(itemTouchListener)
    }

    fun saveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()
    }

    fun restoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(state)
    }
}

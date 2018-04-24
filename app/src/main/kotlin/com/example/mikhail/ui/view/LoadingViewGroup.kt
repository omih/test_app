package com.example.mikhail.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.mikhail.R

class LoadingViewGroup : FrameLayout {

    private var mProgressView: View? = null
    private var mContentView: View? = null
    private var mStubView: View? = null
    private var mLoadingView: LoadingView? = null
    private var mLoadingId: Int = 0
    private var mProgressId: Int = 0
    private var mContentId: Int = 0
    private var mStubId: Int = 0

    private var mState: State = State.HIDE

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null && !isInEditMode) {
            var arr: TypedArray? = null
            try {
                arr = context.obtainStyledAttributes(attrs, R.styleable.LoadingViewGroup)
                mContentId = arr.getResourceId(R.styleable.LoadingViewGroup_loading_content, 0)
                mStubId = arr.getResourceId(R.styleable.LoadingViewGroup_loading_stub, 0)
                mProgressId = arr.getResourceId(R.styleable.LoadingViewGroup_loading_progress, 0)
                mLoadingId = arr.getResourceId(R.styleable.LoadingViewGroup_loading_view, 0)

                mState = State.of(arr.getInt(R.styleable.LoadingViewGroup_loading_state, mState.ordinal)) ?: mState
            } finally {
                arr?.recycle()
            }
        }
    }

    override fun addView(child: View?) {
        super.addView(child)
        checkViews()
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
        checkViews()
    }

    override fun addView(child: View?, width: Int, height: Int) {
        super.addView(child, width, height)
        checkViews()
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        checkViews()
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        checkViews()
    }

    private fun checkViews() {
        if (!isInEditMode && (mProgressView == null || mContentView == null || mStubView == null || mLoadingView == null)) {
            mProgressView = findViewById(mProgressId)
            mContentView = findViewById(mContentId)
            mStubView = findViewById(mStubId)
            val loadingView = findViewById<View>(mLoadingId)
            mLoadingView = if (loadingView is LoadingView) loadingView else null

            if (mProgressView == null || mContentView == null) {
                visibility = View.GONE
            } else {
                postInit()
            }
        }
    }

    private fun postInit() {
        mProgressView?.visibility = View.GONE
        mStubView?.visibility = View.GONE
        mContentView?.visibility = View.GONE

        when (mState) {
            State.LOADING -> {
                mProgressView?.visibility = View.VISIBLE
                mLoadingView?.startLoading()

                visibility = View.VISIBLE
            }
            State.CONTENT -> {
                mContentView?.visibility = View.VISIBLE

                visibility = View.VISIBLE
            }
            State.STUB -> {
                mStubView?.visibility = View.VISIBLE

                visibility = View.VISIBLE
            }
            State.HIDE -> {
                visibility = View.GONE
            }
        }
    }

    private fun reapplyState() {
        when (mState) {
            State.LOADING -> {
                applyViewsVisibility(mProgressView, mStubView, mContentView)
                visibility = View.VISIBLE
            }
            State.STUB -> {
                applyViewsVisibility(mStubView, mContentView, mProgressView)
                visibility = View.VISIBLE
            }
            State.CONTENT -> {
                applyViewsVisibility(mContentView, mProgressView, mStubView)
                visibility = View.VISIBLE
            }
            State.HIDE -> {
                applyViewsVisibility(null, mContentView, mProgressView, mStubView)
                visibility = View.GONE
            }
        }
    }

    private fun applyViewsVisibility(showView: View?, vararg hideViews: View?) {
        showView?.let { it.visibility = View.VISIBLE }
        for (view in hideViews) view?.visibility = View.GONE
    }

    fun showLoading() {
        mState = State.LOADING
        reapplyState()

        mLoadingView?.startLoading()
    }

    fun showContent() {
        mState = State.CONTENT
        reapplyState()

        mLoadingView?.stopLoading()
    }


    fun showStub() {
        mState = State.STUB
        reapplyState()

        mLoadingView?.stopLoading()
    }

    private enum class State {
        LOADING,
        CONTENT,
        STUB,
        HIDE;

        companion object {
            fun of(value: Int): State? = values().find { value == it.ordinal }
        }
    }

}
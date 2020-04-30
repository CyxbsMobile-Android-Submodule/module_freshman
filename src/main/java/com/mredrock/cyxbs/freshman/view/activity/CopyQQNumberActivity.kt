package com.mredrock.cyxbs.freshman.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.mark.EventBusLifecycleSubscriber
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.config.BUNDLE_DATA
import com.mredrock.cyxbs.freshman.config.BUNDLE_NAME
import com.mredrock.cyxbs.freshman.config.INTENT_DATA
import com.mredrock.cyxbs.freshman.config.INTENT_NAME
import com.mredrock.cyxbs.freshman.util.event.OnCopyQQNumberSuccessEvent
import com.mredrock.cyxbs.freshman.util.event.SuccessViewFinishEvent
import com.mredrock.cyxbs.freshman.view.fragment.CopyQQNumberFragment
import com.mredrock.cyxbs.freshman.view.fragment.CopyQQNumberSuccessFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Create by yuanbing
 * on 2019/8/4
 */
class CopyQQNumberActivity : BaseActivity(), EventBusLifecycleSubscriber {
    override val isFragmentActivity: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_copy_qq_number)

        initFragment()
    }

    private fun initFragment() {
        val fragment = CopyQQNumberFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, intent.getStringExtra(INTENT_NAME))
        bundle.putString(BUNDLE_DATA, intent.getStringExtra(INTENT_DATA))
        fragment.arguments = bundle
        replaceFragment(fragment)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCopyQQNumberSuccess(message: OnCopyQQNumberSuccessEvent) {
        replaceFragment(CopyQQNumberSuccessFragment())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFinishHint(event: SuccessViewFinishEvent) {
        finish()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_copy_qq_number, fragment)
                .commit()
    }
}
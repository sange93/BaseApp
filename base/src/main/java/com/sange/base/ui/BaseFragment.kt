package com.sange.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Fragment基类（公共通用型）
 *
 * @author ssq
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(), CoroutineScope by MainScope() {

    /**
     * ViewBinding 实例
     * This property is only valid between onCreateView and onDestroyView.
     */
    private var _binding: VB? = null
    /**
     * ViewBinding 实例
     */
    protected val mBinding get() = _binding!!

    /**
     * 提供ViewBinding对象
     */
    protected abstract fun providerVB(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean = false
    ): VB

    /**
     * 初始化界面
     */
    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = providerVB(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        cancel()// 取消协程
        super.onDestroyView()
        _binding = null
    }
}
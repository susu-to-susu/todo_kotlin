package susu.com.todo.view.animation

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.view.animation.AccelerateInterpolator
import susu.com.todo.R

/**
 * ------------------------------------------------------------------------------------
 * 右下のアイコン(FloatingActionButton)のアニメーション処理
 * アニメーションの部分はMainActivityから切り離した
 * Floatingアイコンの動作管理クラス
 * ------------------------------------------------------------------------------------
 */
class Floating (context: Activity){
    // 静的領域
    companion object {
        // 動作状態
        var state: FloatingActionState = FloatingActionState.NORMAL
        // Context
        lateinit var statefulActivity : Activity
    }

    init {
        statefulActivity = context
    }

    // アニメーション定数
    enum class FloatingActionState {
        NORMAL,
        ANIMATED
    }
    
    // 開く動作オブジェクト
    fun createOpenFloatingActionButton() : Animator {
        // [fab_open]にオブジェクト注入
        return AnimatorInflater.loadAnimator(statefulActivity, R.animator.fab_open).apply {
            // [fab_add]に対して埋め込み
            setTarget(statefulActivity.findViewById(R.id.fab_add))
            // 加速動作
            interpolator = AccelerateInterpolator()
            // アニメーション中のイベント受信
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    state = FloatingActionState.ANIMATED
                }
                override fun onAnimationCancel(animation: Animator) {
                    animation.end()
                    state = FloatingActionState.ANIMATED
                }
            })
        }
    }

    // 閉じる動作オブジェクト
    fun createCloseFloatingActionButton() : Animator {
        // [fab_open]にオブジェクト注入
        return AnimatorInflater.loadAnimator(statefulActivity, R.animator.fab_close).apply {
            // [fab_add]に対して埋め込み
            setTarget(statefulActivity.findViewById(R.id.fab_add))
            // 加速動作
            interpolator = AccelerateInterpolator()
            // アニメーション中のイベント受信
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    state = FloatingActionState.NORMAL
                }
                override fun onAnimationCancel(animation: Animator) {
                    animation.end()
                    state = FloatingActionState.NORMAL
                }
            })
        }
    }

}
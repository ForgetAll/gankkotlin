package com.xiasuhuei321.gank_kotlin.customview.weather

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import com.xiasuhuei321.gank_kotlin.context
import com.xiasuhuei321.gank_kotlin.extension.getScreenHeight
import java.util.*

/**
 * Created by xiasuhuei321 on 2017/9/5.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 *
 * desc:
 */
class Rain(start: PointF, end: PointF) : WeatherShape(start, end) {

    override var TAG = "Rain"

    // 用户可以设置
    var length = 20f
    var originLength = 20f
        set(value) {
            field = value
            length = value
        }

    var translateX = 0f
    var timeSpace = 16
    //    var originSpeed = speed
    var rainAlpha = 100
    var rainColor = Color.parseColor("#efefef")
//    override var time: Long = 5000 // 总共下落时间


    // 计算加速度
    override fun getAcceleration(): Float {
//        val acc = context.getScreenHeight() / (time * time).toFloat()
//        LogUtil.i(TAG, "acc = " + acc)
        // 恩，决定了，放弃加速，匀速走完
        return 0f
    }

    /**
     * 绘制过程在此完成
     */
    override fun drawWhenInUse(canvas: Canvas) {
        val distance = speed * lastTime
        start.y += distance
        end.y += distance
        canvas.drawLine(start.x, start.y, end.x, end.y, paint)
        // 很重要，持续时间增加
        lastTime += timeSpace
        // 如果已经超出屏幕，表示可以服用了，清空原先状态
        if (end.y >= context.getScreenHeight()) {
            clear()
        }
    }

    fun clear() {
        isInUse = false
        lastTime = 0
        start.y = -length
        end.y = 0f
    }

//    override fun initStyle(wtc: () -> Unit) {
//        super.initStyle(wtc)
//    }

    override fun wtc() {
        val random = Random()
        length = random.nextInt(5).toFloat() + originLength
        paint.apply {
            color = rainColor
        }
    }


    // 通过该方法来获取一个随机的初始化样式
//    override fun initStyle() {
//        val random = Random()
//        // 获取随机透明值
//        rainAlpha = random.nextInt(155) + 50
//        // 获得起点x偏移
//        translateX = random.nextInt(10).toFloat() + 5
//        // 获得长度
//        length = random.nextInt(5).toFloat() + originLength
//        // 获得宽度 5 ~ 8
//        width = random.nextInt(3) + 5f
//        if (!isRandom) {
//            start.x = translateX + originX
//            end.x = translateX + originX
//        } else {
//            // 如果是随机雨点，将x坐标随机范围扩大
//            val randomWidth = random.nextInt(context.getScreenWidth())
//            start.x = randomWidth + originX
//            end.x = randomWidth + originX
//        }
//        start.y = -length
//        end.y = 0f
//        paint.apply {
//            alpha = rainAlpha
//            strokeWidth = width
//            color = rainColor
//            isAntiAlias = true
//        }
//        super.initStyle {
//
//        }
//        val random = Random()
//        length = random.nextInt(5).toFloat() + originLength
//        paint.apply {
//            color = rainColor
//        }

//    }

    override fun randomSpeed(random: Random): Float {
        // 获取随机速度 0.02 ~ 0.06
        var randomSpeed = random.nextFloat() / 10
        if (randomSpeed - 0.05f > 0.01f) {
            randomSpeed -= 0.05f
        } else if (randomSpeed < 0.02f) {
            randomSpeed = 0.02f
        }

        return randomSpeed
    }


}
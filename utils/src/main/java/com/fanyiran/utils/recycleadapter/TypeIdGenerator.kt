package com.fanyiran.utils.recycleadapter

object TypeIdGenerator {
    val typeIdMap by lazy { HashMap<String, Int>() }
    fun getTypeId(type: String): Int {
        return typeIdMap.getOrPut(type, { System.currentTimeMillis().toInt() })
    }
}
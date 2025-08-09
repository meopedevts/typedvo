/*
 * Copyright 2025 meopedevts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meopedevts.typedvo.delegates

import br.com.sankhya.jape.vo.DynamicVO
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.createType

class DelegateNotNull<T>: ReadWriteProperty<DynamicVO, T> {
    override fun getValue(thisRef: DynamicVO, property: KProperty<*>): T {
        val nameProperty = property.name.uppercase()
        return when(property.returnType) {
            Short::class.createType(nullable = true) -> thisRef.asBigDecimalOrZero(nameProperty).toInt().toShort()
            Int::class.createType(nullable = true) -> thisRef.asBigDecimalOrZero(nameProperty).toInt()
            BigDecimal::class.createType(nullable = true) -> thisRef.asBigDecimalOrZero(nameProperty)
            else -> requireNotNull(thisRef.getProperty(nameProperty)) {
                "O campo ${nameProperty} não pode ser nulo"
            }
        } as T
    }

    override fun setValue(thisRef: DynamicVO, property: KProperty<*>, value: T) {
        val nameProperty = property.name.uppercase()
        val newValue = when(property.returnType) {
            Short::class.createType(nullable = true) -> (value as Short).toInt().toBigDecimal()
            Int::class.createType(nullable = true) -> (value as Int).toBigDecimal()
            BigDecimal::class.createType(nullable = true) -> value
            else -> when(value) {
                is Int -> value.toBigDecimal()
                is Short -> value.toInt().toBigDecimal()
                is Long -> value.toBigDecimal()
                is Double -> value.toBigDecimal()
                is Float -> value.toBigDecimal()
                is BigInteger -> value.toBigDecimal()
                else -> value
            }
        }
        thisRef.setProperty(nameProperty, newValue)
    }
}

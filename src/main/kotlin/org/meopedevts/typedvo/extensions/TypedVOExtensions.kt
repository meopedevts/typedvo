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
package org.meopedevts.typedvo.extensions

import org.meopedevts.typedvo.exceptions.TypedVOConversionException
import br.com.sankhya.jape.vo.DynamicVO
import br.com.sankhya.jape.vo.EntityVO

fun DynamicVO.asEntityVO(): EntityVO {
    return this as EntityVO
}

inline fun <reified T : DynamicVO> DynamicVO.asTypedVO(): T {
    return try {
        T::class.java.getConstructor(DynamicVO::class.java).newInstance(this)
    } catch (e: Exception) {
        throw TypedVOConversionException(T::class.java, e)
    }
}

fun EntityVO.asDynamicVO(): DynamicVO {
    return this as DynamicVO
}

inline fun <reified T: DynamicVO> EntityVO.asTypedVO(): T {
    return this.asDynamicVO().asTypedVO()
}

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
package org.meopedevts.typedvo.dao

import br.com.sankhya.jape.vo.DynamicVO
import br.com.sankhya.jape.wrapper.JapeWrapperImpl

abstract class BaseDAO<T : DynamicVO>(
    instanceName: String,
    private val wrapper: (DynamicVO) -> T
): JapeWrapperImpl(instanceName) {
    override fun find(where: String, vararg params: Any): List<T> {
        return super.find(where, *params).map { wrapper(it) }.toList()
    }

    override fun find(where: String): List<T> {
        return super.find(where).map { wrapper(it) }.toList()
    }

    override fun findOne(where: String, vararg params: Any): T? {
        return wrapper(super.findOne(where, params) ?: return null)
    }

    override fun findOne(where: String): T? {
        return wrapper(super.findOne(where) ?: return null)
    }

    override fun findByPK(vararg params: Any): T {
        return wrapper(super.findByPK(*params))
    }
}

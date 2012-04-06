/*
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
package com.byktol.jcr.qb.criteria.builder.impl;

import com.byktol.jcr.qb.criteria.Order;
import com.byktol.jcr.qb.criteria.Restrictions;

/**
 * This can class be considered the builder in the builder design pattern. There
 * are two ways of implementing this design pattern: first by making the
 * component classes build themselves, and second by creating a builder that
 * knows how to build every component classes. I've chosen the latter as a way
 * to keep the builder code closer together instead of the former, were I'd have
 * to change the source code of each and every component class. So, for example,
 * if you want to add a JCR-SQL query builder, you'll have to inherit this class
 * and implement the (abstract) builder methods.
 *
 * @author Victor Alvarez
 */
public abstract class AbstractBuilder {

  /**
   * Builds the query based on {@link Restrictions} and {@link Order}.
   *
   * @return the query built for the specific implementation
   */
  public abstract String buildQuery();

}

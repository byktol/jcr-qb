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
package com.byktol.jcr.qb.criteria.expressions;

import com.byktol.jcr.qb.criteria.Criterion;
import com.google.common.base.Strings;

/**
 * Verifies the existence of a property.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class ExistExpression
  implements Criterion
{

  /** The property whose existence is being verified. */
  private final String propertyName;

  /**
   * Constructor.
   *
   * @param propertyName
   *          The name of the property being verified.
   */
  public ExistExpression(final String propertyName)
  {
    if (Strings.isNullOrEmpty(propertyName))
    {
      throw new IllegalArgumentException("propertyName cannot be null");
    }

    this.propertyName = propertyName;
  }

  /**
   * @return the current name of the property
   */
  public final String getPropertyName()
  {
    return propertyName;
  }
}

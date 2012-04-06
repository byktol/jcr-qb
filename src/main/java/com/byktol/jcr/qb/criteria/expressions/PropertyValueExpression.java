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

/**
 * @since 1.0
 * @author Victor Alvarez
 */
public abstract class PropertyValueExpression
  implements Criterion
{

  /**
   * The name of the property being evaluated.
   */
  private String propertyName;

  /**
   * The value for the expression.
   */
  private String value;

  /**
   * Constructor.
   *
   * @param propertyName
   *          The name of the property being evaluated.
   * @param value
   *          The value for the expression.
   */
  public PropertyValueExpression(final String propertyName, final String value)
  {
    if (null == propertyName)
    {
      throw new IllegalArgumentException("propertyName cannot be null");
    }

    if (null == value)
    {
      throw new IllegalArgumentException("value cannot be null");
    }

    this.propertyName = propertyName;
    this.value = value;
  }

  /**
   * @return the value of the property being evaluated
   */
  public final String getValue()
  {
    return value;
  }

  /**
   * @return the property name being compared
   */
  public final String getPropertyName()
  {
    return propertyName;
  }
}

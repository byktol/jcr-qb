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
package com.byktol.jcr.qb.criteria;

/**
 * The direction the query results will be ordered.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public final class Order
{

  /**
   * Defines the direction for ordering the results.
   *
   * @since 1.0
   * @author Victor Alvarez
   */
  private enum OrderDirection
  {
    /** Ascending direction for ordering results. */
    ASCENDING,
    /** Descending direction for ordering results. */
    DESCENDING;

    @Override
    public String toString()
    {
      return super.toString().toLowerCase();
    }
  }

  /**
   * Not publicly instantiable.
   *
   * @param propName
   *          the property used for ordering
   * @param dir
   *          the direction of the ordering
   */
  private Order(final String propName, final OrderDirection dir)
  {
    this.propertyName = propName;
    this.direction = dir;
  }

  /** The direction for ordering the results. */
  private final OrderDirection direction;
  /** The name of the property used to order the results. */
  private final String propertyName;

  /**
   * Creates an ascending {@link Order} for the given property.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param propertyName
   *          the name of the property being used for ordering.
   * @return a new ascending order for the given property
   */
  public static Order asc(final String propertyName)
  {
    return new Order(propertyName, OrderDirection.ASCENDING);
  }

  /**
   * Creates a descending {@link Order} for the given property.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param propertyName
   *          the name of the property being used for ordering.
   * @return a new descending order for the given property
   */
  public static Order desc(final String propertyName)
  {
    return new Order(propertyName, OrderDirection.DESCENDING);
  }

  /**
   * @return the property used to set the ordering
   */
  public String getPropertyName()
  {
    return this.propertyName;
  }

  /**
   * @return the direction of the ordering (either ascending or descending)
   */
  public String getDirection()
  {
    return this.direction.toString();
  }

  @Override
  public String toString()
  {
    return String.format("%s %s", propertyName, direction);
  }
}
